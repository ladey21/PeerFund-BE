package com.peerfund.peerfundapp.controllers;



import com.peerfund.peerfundapp.dto.LoginDto;
import com.peerfund.peerfundapp.dto.UserDto;
import com.peerfund.peerfundapp.payload.JWTAuthResponse;
import com.peerfund.peerfundapp.security.JwtTokenProvider;
import com.peerfund.peerfundapp.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserDto userDto){
        userService.signUp(userDto);
        return new ResponseEntity<>("user signed up successfully", HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}
