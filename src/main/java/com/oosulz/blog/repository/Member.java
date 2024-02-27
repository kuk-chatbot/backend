package com.oosulz.blog.repository;

import lombok.*;

@Data //getter + setter
@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 생성자
//@RequiredArgsConstructor //final 붙은 생성자
public class Member {

    private int id;
    private String username;
    private String password;
    private String email;

}
