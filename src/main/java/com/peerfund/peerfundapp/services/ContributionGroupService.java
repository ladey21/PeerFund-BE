package com.peerfund.peerfundapp.services;

import com.peerfund.peerfundapp.dto.GroupDTO;
import com.peerfund.peerfundapp.entities.ContributionGroup;

import java.util.List;


public interface ContributionGroupService {

    String createGroup(Long userId,GroupDTO groupDTO);

    void updateGroup(Long userId,GroupDTO groupDTO, Long groupId);

    List<ContributionGroup> allGroupsCreatedByUser(Long id);

    void joinGroup(Long groupId,Long userId);

    void exitGroup(Long groupId,Long userId);

    void removeGroup(Long groupId);
}
