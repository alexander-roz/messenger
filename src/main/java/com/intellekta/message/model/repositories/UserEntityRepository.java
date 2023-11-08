package com.intellekta.message.model.repositories;

import com.intellekta.message.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityById(int id);
}
