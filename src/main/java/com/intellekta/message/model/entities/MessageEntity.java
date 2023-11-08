package com.intellekta.message.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@Table(name = "messages", schema = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageID;

    @Column(name = "message_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id", name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "user_name")
    private UserEntity user;
}
