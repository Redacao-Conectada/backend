package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("method=loadUserByUsername, msg=finding user {}", username);

    User user = repository.findByEmail(username);
    if (user == null) {
      log.error("method=loadUserByUsername, msg=user {} not found", username);
      throw new UsernameNotFoundException("Email not found");
    }
    return user;
  }
}
