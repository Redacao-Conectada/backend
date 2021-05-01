package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.*;
import com.rc.redacaoconectada.services.ChangeRoleService;
import com.rc.redacaoconectada.services.CommentService;
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

  @Autowired
  private CommentService commentService;

  @Autowired
  private ChangeRoleService changeRoleService;

  @PostMapping
  public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO user) {
    UserDTO dto = service.insert(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PostMapping("/comment/{id}")
  public ResponseEntity<EssayCommentDTO> insertComment(@Valid @RequestBody EssayCommentInsertDTO comment) {
    EssayCommentDTO dto = commentService.insertComment(comment);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(comment.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PostMapping("/changeRole/{id}")
  public ResponseEntity<UserChangeRoleDto> requestChangeRole(@Valid @RequestBody UserChangeRoleInsertDTO request) {
    UserChangeRoleDto dto = changeRoleService.requestChangeRole(request);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(request.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }
}
