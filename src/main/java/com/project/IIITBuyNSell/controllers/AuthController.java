package com.project.IIITBuyNSell.controllers;

import com.project.IIITBuyNSell.dto.jwt.JwtRequest;
import com.project.IIITBuyNSell.dto.jwt.JwtResponse;
import com.project.IIITBuyNSell.dto.response.BaseResponse;
import com.project.IIITBuyNSell.exceptions.GlobalExceptionHandler;
import com.project.IIITBuyNSell.service.CredentialsService;
import com.project.IIITBuyNSell.service.CustomUserDetailsService;
import com.project.IIITBuyNSell.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) {
        //doing authentication
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new GlobalExceptionHandler.InvalidCredentialsException();
        }

        //authentication done
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody JwtRequest jwtRequest) {
        try {
            credentialsService.saveCredentials(jwtRequest);
        } catch (Exception ex) {
            log.error("Exception occured while saving user : {}", jwtRequest.getUsername(), ex);
            throw new GlobalExceptionHandler.UnknownException();
        }
        BaseResponse baseResponse = BaseResponse.builder().message("Success").build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
