package com.kuk.chatbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트 연결된 DB의 연결된 전략 따라감
    private int id; //시퀀스,auto_increment

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne //댓글 여러개 => 글 한개
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne //댓글 여러개 => 사옹자 1명 가능
    @JoinColumn(name="userId")
    private User user;

    @ColumnDefault("0")
    private String count; // 조회수

    @CreationTimestamp //자동입력
    private Timestamp createDate;

    public void update(User user, Board board,String content){
        setUser(user);
        setBoard(board);
        setContent(content);
    }

}
