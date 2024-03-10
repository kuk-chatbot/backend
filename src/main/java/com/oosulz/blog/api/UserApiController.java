package com.oosulz.blog.api;

import com.oosulz.blog.dto.ResponseDto;
import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController save 호출");
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
