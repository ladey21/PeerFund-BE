package com.peerfund.peerfundapp.controllers;

import com.peerfund.peerfundapp.entities.Request;
import com.peerfund.peerfundapp.entities.User;
import com.peerfund.peerfundapp.services.RequestService;
import com.peerfund.peerfundapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final RequestService requestService;
    private final UserService userService;

    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }



    @RolesAllowed("Member")
    @GetMapping("/requests")
    public ResponseEntity<List<Request>> allRequestsForUser(@AuthenticationPrincipal UserDetails currentUser){
        User user = userService.findByEmail(currentUser.getUsername());
        return ResponseEntity.ok(requestService.AllRequestsMadeByUser(user.getId()));
    }

    @RolesAllowed({"Member","Admin"})
    @GetMapping("/requests/{rid}")
    public ResponseEntity<Request> getRequestDetails(@PathVariable(name = "rid") Long id){
        return ResponseEntity.ok(requestService.viewRequest(id));
    }

}
