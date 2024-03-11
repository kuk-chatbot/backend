package com.oosulz.blog.api;

import com.oosulz.blog.dto.ResponseDto;
import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController save 호출");
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    //기본 로그인 (security 적용x)
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController login 호출");
        User principal = userService.로그인(user).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. username: " + user.getUsername())); //principal(접근주체)
        if (principal != null){
            session.setAttribute("principal",principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
