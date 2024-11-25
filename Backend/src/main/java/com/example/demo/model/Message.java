package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;



@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "sent_at")
    private Date sentAt;

    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "status")
    private String messageStatus;
}
