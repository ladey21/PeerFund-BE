package com.peerfund.peerfundapp.services;


import com.peerfund.peerfundapp.dto.RequestDTO;
import com.peerfund.peerfundapp.entities.Request;


import java.util.List;
import java.util.Set;

public interface RequestService {

    String makeRequest(Long userId,Long groupId);

    List<Request> getAllRequestsToGroup(Long groupId);

    List<Request> AllRequestsMadeByUser(Long userId);

    Request viewRequest(Long requestId);

    void approveRequest(Long userId,Long requestId,Long groupId);

    void declineRequest(Long userId,Long requestId,Long groupId);
}
