package com.intellekta.message.dto;

import com.intellekta.message.model.entities.UserEntity;
import lombok.Data;

@Data
public class MessageRequest {
    String text;
    String user;
}
