package com.rc.redacaoconectada.controllers;

import com.rc.redacaoconectada.dtos.ChangeRoleDTO;
import com.rc.redacaoconectada.dtos.ChangeRoleUserDTO;
import com.rc.redacaoconectada.dtos.UserDTO;
import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.services.ChangeRoleService;
import com.rc.redacaoconectada.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdmController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChangeRoleService changeRoleService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>>findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "usersPerPage", defaultValue = "12") Integer usersPerPage,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                     @RequestParam(value = "orderBy", defaultValue = "name") String name){

        PageRequest pageRequest = PageRequest.of(page, usersPerPage, Sort.Direction.valueOf(direction),name);
        Page<UserDTO> list = userService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/users/requests")
    public ResponseEntity<Page<ChangeRoleDTO>>findAllPagedChangeRoleRequest(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "requestsPerPage", defaultValue = "12") Integer requestsPerPage,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                                @RequestParam(value = "orderBy", defaultValue = "id") String id){

        PageRequest pageRequest = PageRequest.of(page, requestsPerPage, Sort.Direction.valueOf(direction), id);
        Page<ChangeRoleDTO> list = changeRoleService.findAllPagedChangeRoleRequest(pageRequest);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/users/requests/{id}")
    public ResponseEntity<ChangeRoleUserDTO>findRequestByID(@PathVariable("id") Long id){

        ChangeRoleUserDTO changeRoleDTO = changeRoleService.findRequestById(id);

        return ResponseEntity.ok().body(changeRoleDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        UserDTO userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);

    }

    @PutMapping(value = "/approve/{id}")
    public ResponseEntity<UserDTO> approveChangeRole(@PathVariable("id") Long id){
        UserDTO userDto = changeRoleService.approveChangeRole(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping(value = "/dennied/{id}")
    public ResponseEntity<UserDTO> denniedChangeRole(@PathVariable("id") Long id){
        UserDTO userDto = changeRoleService.denniedChangeRole(id);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(userDto);
    }

}
