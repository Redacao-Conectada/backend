package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.dtos.UserInsertDTO;
import com.rc.redacaoconectada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping
  public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO user) {
    UserDTO dto = service.insert(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }
}
