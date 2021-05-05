package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.*;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.services.ChangeRoleService;
import com.rc.redacaoconectada.services.CommentService;

import com.rc.redacaoconectada.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

  @PutMapping("/update")
  public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserChangeDTO newUser) {
    return new ResponseEntity<>(service.updateUser(newUser), HttpStatus.OK);
  }

  @DeleteMapping("/comment/{id}")
  public ResponseEntity<Void> deleteComment(@Valid @PathVariable("id") Long id){
    try{
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }
  
  @PostMapping("/comment/{id}")
  public ResponseEntity<EssayCommentDTO> insertComment(@Valid @RequestBody EssayCommentInsertDTO comment) {
    EssayCommentDTO dto = commentService.insertComment(comment);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(comment.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PostMapping("/changeRole/{id}")
  public ResponseEntity<UserChangeRoleDTO> requestChangeRole(@Valid @RequestBody UserChangeRoleInsertDTO request) {
    UserChangeRoleDTO dto = changeRoleService.requestChangeRole(request);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(request.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }
}
