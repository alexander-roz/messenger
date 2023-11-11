package com.intellekta.message.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Entity
@Data
@Table (name = "users", schema = "message")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;
}
