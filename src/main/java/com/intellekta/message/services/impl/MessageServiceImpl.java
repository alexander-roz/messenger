package com.intellekta.message.services.impl;

import com.intellekta.message.dto.Request;
import com.intellekta.message.model.entities.MessageEntity;
import com.intellekta.message.model.repositories.MessageEntityRepository;
import com.intellekta.message.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    MessageEntityRepository messageEntityRepository;

    public MessageServiceImpl(MessageEntityRepository messageEntityRepository) {
        this.messageEntityRepository = messageEntityRepository;
    }

    @Override
    public Integer addMessage(MessageEntity message) {
        messageEntityRepository.save(message);
        return message.getMessageID();
    }

    @Override
    public Request deleteMessage(MessageEntity message) {
        if(messageEntityRepository.existsById(message.getMessageID())){
            messageEntityRepository.delete(message);
            return new Request(true);
        }
        else{
            return new Request(false, "message was not found");
        }
    }

    @Override
    public Request deleteAllMessages() {
        messageEntityRepository.deleteAll();
        if(messageEntityRepository.findAll().size() == 0){
            return new Request(true);
        }
        else return new Request(false, "messages were not deleted");
    }

    @Override
    public List<MessageEntity> findAllMessages() {
        return messageEntityRepository.findAll();
    }

    @Override
    public MessageEntity getMessageByID(int id) {
        return messageEntityRepository.findMessageEntitiesByMessageID(id);
    }

    @Override
    public List<MessageEntity> findMessagesByUserName (String name){
      return messageEntityRepository.findMessageEntitiesByUser_Name(name);
    }

}
