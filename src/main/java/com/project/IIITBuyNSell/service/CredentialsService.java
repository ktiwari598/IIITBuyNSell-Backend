package com.project.IIITBuyNSell.service;

import com.project.IIITBuyNSell.dto.jwt.JwtRequest;
import com.project.IIITBuyNSell.model.Credentials;
import com.project.IIITBuyNSell.repositories.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Credentials getCredentials(String userName) {
        return credentialsRepository.findById(userName).orElse(new Credentials());
    }

    public void saveCredentials(JwtRequest jwtRequest) {
        String encodePassword = passwordEncoder.encode(jwtRequest.getPassword());
        Credentials credentials = Credentials.builder()
                .userName(jwtRequest.getUsername())
                .password(encodePassword)
                .build();
        credentialsRepository.save(credentials);
    }

}
