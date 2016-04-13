package com.felix.raspi.security;

import com.felix.raspi.model.entity.Users;
import com.felix.raspi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by fsoewito on 2/26/2016.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Authenticating {}", username);
        Users users = userRepository.findByUsername(username);
        if (users == null) {
            LOGGER.error("Authenticating username {} return null", username);
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return users;
    }


}
