package com.peerfund.peerfundapp.services.implementation;

import com.peerfund.peerfundapp.dto.GroupDTO;
import com.peerfund.peerfundapp.entities.ContributionGroup;
import com.peerfund.peerfundapp.exceptions.PeerFundException;
import com.peerfund.peerfundapp.repositories.ContributionGroupRepository;
import com.peerfund.peerfundapp.repositories.UserRepository;
import com.peerfund.peerfundapp.services.ContributionGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionGroupImpl implements ContributionGroupService {

    private final ContributionGroupRepository groupRepository;
    private final UserRepository userRepository;

    public ContributionGroupImpl(ContributionGroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createGroup(Long userId, GroupDTO groupDTO) {
        boolean groupExist = groupRepository.existsContributionGroupByName(groupDTO.getName());
        if(groupExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"group exist already");
        } else{
            ContributionGroup group = new ContributionGroup();
            group.setName(groupDTO.getName());
            group.setPurpose_title(groupDTO.getPurpose());
            group.setSlots(groupDTO.getSlots());
            group.setDescription(groupDTO.getDescription());
            group.setGroup_amount(groupDTO.getAmount());
            group.setAdmin(userRepository.findUserById(userId));
            groupRepository.save(group);
        }
        return "created";
    }

    @Override
    public void updateGroup(Long userId, GroupDTO groupDTO, Long groupId) {
        ContributionGroup group = groupRepository.findContributionGroupByIdAndAdmin_Id(groupId,userId);
        group.setName(groupDTO.getName());
        group.setPurpose_title(group.getPurpose_title());
        group.setSlots(groupDTO.getSlots());
        group.setDescription(groupDTO.getDescription());
        group.setStartDate(groupDTO.getStartDate());
        group.setEndDate(groupDTO.getEndDate());
        group.setPaymentStartDate(groupDTO.getPaymentStartDate());
        group.setEndDate(groupDTO.getEndDate());
        group.setGroup_amount(groupDTO.getAmount());
        groupRepository.save(group);
    }

    @Override
    public List<ContributionGroup> allGroupsCreatedByUser(Long id) {
        return groupRepository.findContributionGroupsByAdmin_Id(id);
    }

    @Override
    public void joinGroup(Long groupId, Long userId) {
        ContributionGroup group = findGroup(groupId);
        group.getMembers().add(userRepository.findUserById(userId));
        groupRepository.save(group);
    }

    @Override
    public void exitGroup(Long groupId, Long userId) {
        ContributionGroup group = findGroup(groupId);
        group.getMembers().remove(userRepository.findUserById(userId));
        groupRepository.save(group);
    }

    @Override
    public void removeGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }

}
