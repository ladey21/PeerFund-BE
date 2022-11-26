package com.peerfund.peerfundapp.services.implementation;

import com.peerfund.peerfundapp.dto.RequestDTO;
import com.peerfund.peerfundapp.entities.ContributionGroup;
import com.peerfund.peerfundapp.entities.Enums.RequestStatus;
import com.peerfund.peerfundapp.entities.Request;
import com.peerfund.peerfundapp.entities.User;
import com.peerfund.peerfundapp.exceptions.PeerFundException;
import com.peerfund.peerfundapp.repositories.ContributionGroupRepository;
import com.peerfund.peerfundapp.repositories.RequestRepository;
import com.peerfund.peerfundapp.repositories.UserRepository;
import com.peerfund.peerfundapp.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final ContributionGroupRepository groupRepository;

    public RequestServiceImpl(UserRepository userRepository, RequestRepository requestRepository, ContributionGroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public String makeRequest(Long userId, Long groupId) {
        ContributionGroup group = groupRepository.findContributionGroupById(groupId);
        User user = userRepository.findUserById(userId);
        boolean requestExist = group.getRequests().stream().anyMatch(request -> request.getMember().getId().equals(userId));
        if(requestExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"request created already");
        } else{
            Request request = new Request(user, LocalDateTime.now(),group, RequestStatus.PENDING);
            requestRepository.save(request);
        }
        return "created";
    }

    @Override
    public List<Request> getAllRequestsToGroup(Long groupId) {
        return findGroup(groupId).getRequests();
    }

    @Override
    public List<Request> AllRequestsMadeByUser(Long userId) {
        return userRepository.findUserById(userId).getRequests();
    }

    @Override
    public Request viewRequest(Long requestId) {
        return requestRepository.findRequestById(requestId);
    }


    @Override
    public void approveRequest(Long userId, Long requestId, Long groupId) {
        Request request = requestRepository.findRequestByIdAndContributionGroup_Id(requestId,groupId);
        request.setRequestStatus(RequestStatus.APPROVED);
        requestRepository.save(request);
        ContributionGroup group = findGroup(groupId);
        group.getMembers().add(userRepository.findUserById(userId));
        groupRepository.save(group);
    }

    @Override
    public void declineRequest(Long userId, Long requestId, Long groupId) {
        Request request = requestRepository.findRequestByIdAndContributionGroup_Id(requestId,groupId);
        request.setRequestStatus(RequestStatus.DECLINED);
        requestRepository.save(request);
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }
}
