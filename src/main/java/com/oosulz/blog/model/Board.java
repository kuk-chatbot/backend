package com.oosulz.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트 연결된 DB의 연결된 전략 따라감
    private int id; //시퀀스,auto_increment

    @Column(nullable = false, length = 100)
    private String title; //게시물 제목

    @Lob // 섬머노트 <html> 디자인
    private String content;

    @ColumnDefault("0")
    private String count; // 조회수

    @CreationTimestamp //자동입력
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.EAGER) //보드 다 : user 1 (1건 밖에 없으니 바로 조인)
    @JoinColumn(name="userId")
    private User user;
    // DB는 오브젝트 저장 X
    // FK, 자바는 오브젝트를 저장 할 수 있다.
    // ORM 쓰면 DB도 오브젝트 저장 가능
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    // mappedBy = 연관관계 주인 아니다 = 난 FK 아니에여 / DB컬럼 만들지 마라
    //
    private List<Reply> reply;


}