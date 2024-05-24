package com.kuk.chatbot.api;

import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/auth/sign-up")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController save 호출");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/account")
    public ResponseDto<Integer> accountupdate(@RequestBody User user) {
        System.out.println("UserApiController account 호출");
        // 사용자 정보 업데이트
        userService.회원수정(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/auth/user")
    public Map<String, String> getUserInfo(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("username", principal.getName());
        return response;
    }
}
