package com.cwilliam.task.manager.controllers;


import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.services.oauth.AuthenticationService;
import com.cwilliam.task.manager.services.oauth.dto.OauthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    private ResponseEntity<OauthDto> token(@RequestBody User user){
        return ResponseEntity.ok().body(authenticationService.signin(user));
    }
}
