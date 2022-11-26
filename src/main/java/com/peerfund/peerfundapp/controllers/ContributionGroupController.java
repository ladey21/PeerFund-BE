package com.peerfund.peerfundapp.controllers;

import com.peerfund.peerfundapp.dto.GroupDTO;
import com.peerfund.peerfundapp.entities.ContributionGroup;
import com.peerfund.peerfundapp.entities.User;
import com.peerfund.peerfundapp.services.ContributionGroupService;
import com.peerfund.peerfundapp.services.RequestService;
import com.peerfund.peerfundapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class ContributionGroupController {

    private final UserService userService;
    private final ContributionGroupService groupService;
    private final RequestService requestService;

    public ContributionGroupController(UserService userService, ContributionGroupService groupService, RequestService requestService) {
        this.userService = userService;
        this.groupService = groupService;
        this.requestService = requestService;
    }

    @PostMapping("/groups")
    public ResponseEntity<String> createGroup(@RequestBody GroupDTO groupDTO, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(groupService.createGroup(getCurrentUserId(currentUser),groupDTO));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<ContributionGroup>> getAllGroups(@AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(groupService.allGroupsCreatedByUser(getCurrentUserId(currentUser)));
    }


    @PostMapping("/groups/{gid}/requests")
    public ResponseEntity<String> createRequestToGroup(@PathVariable(name = "gid")Long idg, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(requestService.makeRequest(getCurrentUserId(currentUser),idg));
    }



    private Long getCurrentUserId(@AuthenticationPrincipal UserDetails currentUser){
        User user = userService.findByEmail(currentUser.getUsername());
        return user.getId();
    }

}
