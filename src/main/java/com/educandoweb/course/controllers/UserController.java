package com.educandoweb.course.controllers;


import com.educandoweb.course.dto.UserPutRequest;
import com.educandoweb.course.dto.UserRequest;
import com.educandoweb.course.dto.UserResponse;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserResponse(user));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserRequest obj){
        User user = service.insert(obj);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody UserPutRequest obj){
        User user = service.update(obj);
        return ResponseEntity.ok().body(new UserResponse(user));
    }

}
