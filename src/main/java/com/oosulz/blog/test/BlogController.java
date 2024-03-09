package com.oosulz.blog.test;

//import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// 어노테이션 인식 후
//클래스 file들 new 해서 (IOC) 스프링 컨테이너에 관리
@RestController
public class BlogController {
    @GetMapping("/hello")
    public String hello(){
        return "<h1>hello spring boot</h1>";
    }
}
