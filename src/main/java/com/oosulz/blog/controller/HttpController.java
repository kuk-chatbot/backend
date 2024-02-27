package com.oosulz.blog.controller;

import com.oosulz.blog.repository.Member;
import org.springframework.web.bind.annotation.*;

// 사용자가 요청한 데이터로 응답
@RestController
public class HttpController {
    @GetMapping("/http/get")
    // 인터넷 브라우저 요청은 무조건 get 요청뿐
    public String getTest(Member m){
        return "get 요청" + m.getId() +m.getUsername();
    }
    //
    @PostMapping("/http/post") //text => text/plain / Json => application/json
    public String postTest(@RequestBody Member m){ //MessageConverter (스프링부트)
        return "post 요청"+ m.getId() +m.getUsername();
    }
    @PutMapping("/http/put")
    public  String putTest(@RequestBody Member m){
        return "put 요청"+ m.getId() +m.getUsername() +m.getPassword() + m.getEmail();
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
