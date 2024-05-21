package com.kuk.chatbot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuk.chatbot.config.auth.PrincipalDetail;
import com.kuk.chatbot.model.OAuthToken;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


//인증이 안된 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/** , /image/**
@Controller
public class UserController {

    @Value("${kuk.key}")
    private String kukKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @GetMapping("/auth/sign-up")
    public String signUp(){

        return "user/sign-up";
    }

    @GetMapping("/auth/sign-in")
    public String signIn(){

        return "user/sign-in";
    }








    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){

        return "user/updateForm";
    }

}
