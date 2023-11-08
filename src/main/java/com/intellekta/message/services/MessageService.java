package com.intellekta.message.services;

import com.intellekta.message.dto.Request;
import com.intellekta.message.model.entities.MessageEntity;

import java.util.ArrayList;
import java.util.List;

public interface MessageService {
    Integer addMessage(MessageEntity message);
    Request deleteMessage(MessageEntity message);
    Request deleteAllMessages();
    List<MessageEntity> findAllMessages();
    MessageEntity getMessageByID(int id);
    List<MessageEntity> findMessagesByUserName(String name);
}
