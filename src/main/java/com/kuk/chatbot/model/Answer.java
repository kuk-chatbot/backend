package com.kuk.chatbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Lob
    @Column(nullable = false)
    private byte[] resultImage;

    private Integer cpuFanNoScrews;
    private Integer cpuFanScrewsLoose;
    private Integer cpuFanScrews;
    private Integer cpuFan;
    private Integer cpuFanPort;
    private Integer cpuFanPortDetached;
    private Integer incorrectScrews;
    private Integer looseScrews;
    private Integer noScrews;
    private Integer scratch;
    private Integer screws;
}
