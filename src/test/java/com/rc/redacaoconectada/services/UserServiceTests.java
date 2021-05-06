package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.factory.UserFactory;
import com.rc.redacaoconectada.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTests {

  @InjectMocks
  private UserService service;

  @Mock
  private UserRepository repository;

  private User existingUser;
  private String existingUserEmail;
  private String nonExistingUserEmail;
  private PageImpl<User> page;

  @BeforeEach
  void setUp() throws Exception {
    existingUser = UserFactory.createUser();
    existingUserEmail = "bob@gmail.com";
    nonExistingUserEmail = "haha.2332@gmail.com";
    page = new PageImpl<>(List.of(existingUser));

    when(repository.findUserByName(any(), any())).thenReturn(page);
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

  @Test
  void findAllPagedShouldReturnPage() {
    PageRequest request = PageRequest.of(0, 12);
    Page<UserDTO> page = service.findUserEssays("", request);

    Assertions.assertNotNull(page);
    Assertions.assertEquals(1, page.getTotalElements());
    verify(repository, times(1)).findUserByName("", request);
  }
}
