package com.intellekta.message.model.repositories;

import com.intellekta.message.model.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findMessageEntitiesByUser_Name (String name);
    MessageEntity findMessageEntitiesByMessageID(int id);
}
