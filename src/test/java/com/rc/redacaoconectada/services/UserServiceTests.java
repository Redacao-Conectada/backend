package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.factory.UserFactory;
import com.rc.redacaoconectada.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTests {

  @InjectMocks
  private UserService service;

  @Mock
  private UserRepository repository;

  private User existingUser;
  private String existingUserEmail;
  private String nonExistingUserEmail;

  @BeforeEach
  void setUp() throws Exception {
    existingUser = UserFactory.createUser();
    existingUserEmail = "bob@gmail.com";
    nonExistingUserEmail = "haha.2332@gmail.com";

    when(repository.findByEmail(existingUserEmail)).thenReturn(existingUser);
    doThrow(UsernameNotFoundException.class).when(repository).findByEmail(nonExistingUserEmail);
  }

  @Test
  void loadUserByUsernameShouldReturnAnyWhenEmailExists() {
    UserDetails user = service.loadUserByUsername(existingUserEmail);
    assertNotNull(user);
    assertEquals("bob@gmail.com", user.getUsername());
  }

  @Test
  void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenEmailDoesNotExists() {
    assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(nonExistingUserEmail));
  }
}
