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
            System.out.println("User was found. New user was not created");
        }
        else {
            userEntity = user;
        }
        userService.addUser(userEntity);
        return ResponseEntity.ok(userEntity.getId());
    }

    @PostMapping(value = "/messages/")
    public ResponseEntity<Integer> addMessage(@RequestBody MessageRequest messageRequest) {
        String text = messageRequest.getText();
        String user = messageRequest.getUser();

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(text);
        messageEntity.setUser(userService.findUserByName(user));

        messageService.addMessage(messageEntity);

        return ResponseEntity.ok(messageEntity.getMessageID());
    }

    @PostMapping(value = "/list/")
    public ResponseEntity<List<String>> getAllUserMessages(@RequestBody UserEntity user){
        System.out.println("Server got the username: " + user.getName());
        UserEntity userEntity = null;

        if(userService.checkTheUser(user.getName())){
            System.out.println("User checked and found");
            userEntity = userService.findUserByName(user.getName());
            System.out.println("Found user: " + userEntity.getName());
        }
        else {
            System.out.println("User was not found in repository");
        }

        assert userEntity != null;
        List<String> messages = messageService.findMessagesByUser(userEntity.getName());

        System.out.println("Sending the list: ");
        messages.forEach(System.out::println);

        return ResponseEntity.ok(messages);
    }
}
