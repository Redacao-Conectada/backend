package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    public User authenticated() {
        LOG.info("method=authenticated, msg=search user authenticated");
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
        } catch (Exception e) {
            LOG.error("method=authenticated, msg=user authenticated not found");
            throw new UnauthorizedUserException("Invalid User");
        }
    }

}
