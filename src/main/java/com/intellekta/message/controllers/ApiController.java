package com.intellekta.message.controllers;

import com.intellekta.message.model.entities.MessageEntity;
import com.intellekta.message.model.entities.UserEntity;
import com.intellekta.message.services.MessageService;
import com.intellekta.message.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    private final MessageService messageService;
    private final UserService userService;

    public ApiController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    @PostMapping(value = "/users/")
    public ResponseEntity<Integer> addUser(@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.addUser(user));
    }
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUserByID(id));
    }

    @PostMapping(value = "/messages/")
    public ResponseEntity<Integer> addMessage(@RequestBody MessageEntity message){
        return ResponseEntity.ok(messageService.addMessage(message));
    }
    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<MessageEntity> getMessage(@PathVariable Integer id){
        return ResponseEntity.ok(messageService.getMessageByID(id));
    }
    @GetMapping(value = "/messages/{name}")
    public ResponseEntity<List<MessageEntity>> getMessagesByUserName(@PathVariable String name){
        return ResponseEntity.ok(messageService.findMessagesByUserName(name));
    }
}
