package com.oosulz.blog.controller;

import com.oosulz.blog.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    // http://localhost:8000/blog/dummy/join(요청)
    // http의 body에 username,password,email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(String username, String password, String email){ //key=value(약속된 규칙)
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("email:"+email);

        return "회원가입이 완료되었습니다";
    }
    @PostMapping("/dummy/objectjoin")
    public String objectjoin(User user){ //key=value(약속된 규칙)
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());

        return "회원가입이 완료되었습니다";
    }
}
