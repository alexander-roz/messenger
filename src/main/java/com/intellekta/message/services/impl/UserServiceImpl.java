package com.intellekta.message.services.impl;

import com.intellekta.message.dto.Request;
import com.intellekta.message.model.entities.UserEntity;
import com.intellekta.message.model.repositories.UserEntityRepository;
import com.intellekta.message.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    public UserServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Integer addUser(UserEntity user) {
        userEntityRepository.save(user);
        return user.getId();
    }

    @Override
    public Request deleteUser(UserEntity user) {
        if (userEntityRepository.existsById(user.getId())) {
            userEntityRepository.delete(user);
            return new Request(true);
        } else {
            return new Request(false, "User was not found");
        }
    }

    @Override
    public Request deleteAllUsers() {
        userEntityRepository.deleteAll();
        if (userEntityRepository.findAll().isEmpty()) {
            return new Request(true);
        } else {
            return new Request(false, "Users were not deleted");
        }
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntity getUserByID(int id) {
        return userEntityRepository.findUserEntityById(id);
    }

    @Override
    public UserEntity findUserByName(String name){
        UserEntity userEntity = null;
        for(UserEntity user:userEntityRepository.findAll()){
            if(user.getName().equalsIgnoreCase(name)){
                userEntity = user;
            }
        }
        return userEntity;
    }

    @Override
    public boolean checkTheUser(String name){
        boolean found = false;
        for (UserEntity user:userEntityRepository.findAll()){
            if (user.getName().equalsIgnoreCase(name)){
                found = true;
            }
        }
        return found;
    }

}
