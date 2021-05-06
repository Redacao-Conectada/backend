package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.dtos.UserInsertDTO;
import com.rc.redacaoconectada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

  @GetMapping("/essays")
  public ResponseEntity<Page<UserDTO>> findUserEssays(@RequestParam(value = "userName", defaultValue = "") String userName,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "essayPerPage", defaultValue = "12") Integer essayPerPage,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(value = "orderBy", defaultValue = "id") String id) {

    PageRequest pageRequest = PageRequest.of(page, essayPerPage, Sort.Direction.valueOf(direction), id);
    Page<UserDTO> userEssayDTO = service.findUserEssays(userName.trim(), pageRequest);

    return ResponseEntity.ok().body(userEssayDTO);
  }
}
