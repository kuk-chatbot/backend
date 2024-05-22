package com.kuk.chatbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image;

    @CreationTimestamp
    private Timestamp createDate;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private String cause;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer answer;

}
