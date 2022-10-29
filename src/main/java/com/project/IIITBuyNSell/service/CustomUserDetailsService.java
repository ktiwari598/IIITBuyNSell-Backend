package com.project.IIITBuyNSell.service;

import com.project.IIITBuyNSell.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@ComponentScan
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialsService credentialsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsService.getCredentials(username);
        return new User(credentials.getUserName(), credentials.getPassword(), new ArrayList<>());
    }
}
