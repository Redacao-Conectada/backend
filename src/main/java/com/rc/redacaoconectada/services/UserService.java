package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.*;
import com.rc.redacaoconectada.entities.*;
import com.rc.redacaoconectada.repositories.*;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private EssayRepository essayRepository;

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private ChangeRoleRequestRepository changeRoleRequestRepository;

  @Transactional
  public UserDTO insert(UserInsertDTO user) {
    log.info("method=insert, msg=insert user {}", user.getEmail());
    Student student = new Student();
    copyDtoToEntity(user, student);
    student.setPassword(passwordEncoder.encode(user.getPassword()));
    student = repository.save(student);
    return new UserDTO(student);
  }


  private void copyDtoToEntity(UserInsertDTO user, Student student) {
    student.setCpf(user.getCpf());
    student.setName(user.getName());
    student.setEmail(user.getEmail());
    student.setBirthdate(user.getBirthdate());
    student.setGraduation(user.getGraduation());
    student.setSchoolName(user.getSchoolName());
    student.setState(user.getState());
    student.setCity(user.getCity());

    student.getRoles().clear();
    Role role = roleRepository.getOne(1L);
    student.getRoles().add(role);
  }



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
