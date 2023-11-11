package com.intellekta.message.controllers;

import com.intellekta.message.dto.MessageRequest;
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
    public ResponseEntity<Integer> addUser(@RequestBody UserEntity user) {
        UserEntity userEntity = null;
        if(userService.checkTheUser(user.getName())){
            userEntity = userService.findUserByName(user.getName());
        }
        else {
            userEntity = user;
        }
        userService.addUser(userEntity);
        return ResponseEntity.ok(userEntity.getId());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer id) {
        UserEntity user = userService.getUserByID(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/messages/")
    public ResponseEntity<Integer> addMessage(@RequestBody MessageRequest messageRequest) {
        String text = messageRequest.getText();
        System.out.println("requested text: " + text);
        String user = messageRequest.getUser();
        System.out.println("requested user: " + user);

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(text);
        messageEntity.setUser(userService.findUserByName(user));

        messageService.addMessage(messageEntity);

        return ResponseEntity.ok(messageEntity.getMessageID());
    }

    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<MessageEntity> getMessage(@PathVariable Integer id) {
        MessageEntity message = messageService.getMessageByID(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/messages/{name}")
    public ResponseEntity<List<MessageEntity>> getMessagesByUserName(@PathVariable String name) {
        List<MessageEntity> messages = messageService.findMessagesByUserName(name);
        if (!messages.isEmpty()) {
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
