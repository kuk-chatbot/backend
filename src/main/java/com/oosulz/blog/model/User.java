package com.oosulz.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert // null 인 필드는 insert X (role 삽입시 지장 X, but 권장 x)
//User 클래스가 MySQL 테이블 생성
//ORM -> object를 테이블로 매핑해줌
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트 연결된 DB의 연결된 전략 따라감
    private int id; //시퀀스,auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100) // 해쉬 넉넉하게
    // 추후 카카오 API 활용 예정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    //@ColumnDefault("user")   
    @Enumerated(EnumType.STRING)
    private RoleType role; // ADMIN,USER
    //Enum 이 낫긴함 => string의 범위 제한 / user , master 이런느낌

    @CreationTimestamp //자동입력
    private Timestamp createDate;


}
