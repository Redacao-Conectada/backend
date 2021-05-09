package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.*;
import com.rc.redacaoconectada.services.ChangeRoleService;
import com.rc.redacaoconectada.services.CommentService;

import com.rc.redacaoconectada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
    UserDTO userDto = service.findById(id);
    return ResponseEntity.ok().body(userDto);
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
  
  @PutMapping("/update")
  public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserChangeDTO newUser) {
    return new ResponseEntity<>(service.updateUser(newUser), HttpStatus.OK);
  }

  @DeleteMapping(value = "/comment/{id}")
  public ResponseEntity<Void> deleteComment(@Valid @PathVariable("id") Long id){
    try{
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }
  
  @PostMapping("/comment")
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

  @GetMapping("/changeRole")
  public ResponseEntity<Page<UserChangeRoleDTO>> findAllChangeRoleRequests(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                     @RequestParam(value = "changeRolePerPage", defaultValue = "12") Integer changeRolePerPage,
                                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                     @RequestParam(value = "orderBy", defaultValue = "id") String id) {
    PageRequest pageRequest = PageRequest.of(page, changeRolePerPage, Sort.Direction.valueOf(direction), id);
    Page<UserChangeRoleDTO> changeRoleDTO = changeRoleService.findAll(pageRequest);

    return ResponseEntity.ok().body(changeRoleDTO);
  }
}
