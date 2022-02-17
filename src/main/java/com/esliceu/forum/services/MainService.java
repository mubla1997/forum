package com.esliceu.forum.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface MainService {
    UserDetails findByUsername(String username);
}
