package com.cwilliam.task.manager.services.oauth.impl;

import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.repositories.UserRepository;
import com.cwilliam.task.manager.services.oauth.AuthenticationService;
import com.cwilliam.task.manager.services.oauth.JwtService;
import com.cwilliam.task.manager.services.oauth.dto.OauthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public OauthDto signin(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        return OauthDto.builder().token(jwtService.generateToken(user)).build();

    }
}
