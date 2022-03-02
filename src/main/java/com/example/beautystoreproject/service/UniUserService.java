package com.example.beautystoreproject.service;

import com.example.beautystoreproject.entities.UniUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UniUserService extends UserDetailsService {
    UniUser getUserByEmail(String email);

}
