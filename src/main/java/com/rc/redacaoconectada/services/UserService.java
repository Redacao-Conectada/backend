package com.rc.redacaoconectada.services;

import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.dtos.UserInsertDTO;
import com.rc.redacaoconectada.entities.Role;
import com.rc.redacaoconectada.entities.Student;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.repositories.RoleRepository;
import com.rc.redacaoconectada.repositories.UserRepository;
import com.rc.redacaoconectada.services.exceptions.ResourceNotFoundException;
import com.rc.redacaoconectada.dtos.*;
import com.rc.redacaoconectada.repositories.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;


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

  @Transactional(readOnly = true)
  public Page<UserDTO> findAllPaged(PageRequest pagerequest){
    Page<User> user = repository.findAll(pagerequest);
    return user.map(UserDTO::new);
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id){
    Optional<User> obj = repository.findById(id);
    User user = obj.orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));
    return new UserDTO(user);
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

  public UserDTO updateUser(UserChangeDTO newUser) throws UsernameNotFoundException {

    User u = repository.findByEmail(newUser.getEmail());

    if (u == null) {
      log.error("method=loadUserByUsername, msg=user {} not found", newUser.getNewUserName());
      throw new UsernameNotFoundException("Email not found");
    }

    u.setImage(newUser.getImage());
    u.setName(newUser.getNewUserName());

    repository.save(u);

    return new UserDTO(u);
  }
}
