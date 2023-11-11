package com.intellekta.message.services;

import com.intellekta.message.dto.Request;
import com.intellekta.message.model.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    Integer addUser(UserEntity user);
    Request deleteUser(UserEntity user);
    Request deleteAllUsers();
    List<UserEntity> findAllUsers();
    UserEntity getUserByID(int id);

    UserEntity findUserByName(String name);

    boolean checkTheUser(String name);

//    boolean findUserByName(String name);
//
//    UserEntity getUserByName(String name);
}
