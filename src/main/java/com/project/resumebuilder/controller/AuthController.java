package com.project.resumebuilder.controller;

import com.project.resumebuilder.dto.authreq.AuthenticationRequest;
import com.project.resumebuilder.dto.response.WebResponse;
import com.project.resumebuilder.entity.User;
import com.project.resumebuilder.service.MyUserDetailsService;
import com.project.resumebuilder.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userService;

    @PostMapping("/login")
    public ResponseEntity<WebResponse<String>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(jwtUtil.generateTokenWithRoles(userDetails))
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<WebResponse<String>> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data("User registered successfully")
                .build());
    }
}

