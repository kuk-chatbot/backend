package com.oosulz.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oosulz.blog.config.auth.PrincipalDetail;
import com.oosulz.blog.model.KakaoProfile;
import com.oosulz.blog.model.OAuthToken;
import com.oosulz.blog.model.User;
import com.oosulz.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.querydsl.binding.MultiValueBinding;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


//인증이 안된 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/** , /image/**
@Controller
public class UserController {

    @Value("${oosulz.key}")
    private String oosulzKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }



    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){

        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code, HttpSession session){
        // post 방식으로 key-value 타입 데이터를 요청 해야함.
        // a 태그 불가 -> a태그 get 방식만 가능
        RestTemplate rt = new RestTemplate(); //http 요청 편하게 가능 Retrofit2 / okhttp/ resttemplate

        // HttpHeaders 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "fc9a495f12d3bf5094703df4a9d10434");
        params.add("redirect_url", "https://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        // HttpEntity = HttpHeaders + HttpBody 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        // 파라미터로 HttpEntity를 받음
        // http 요청 포스트방식으로 / response 변수에 응답받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
                );



        // object에 담기 (objectmapper 사용)
        OAuthToken oAuthToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("카카오 엑세스 토큰 : " + oAuthToken.getAccess_token());

        //

        RestTemplate rt2 = new RestTemplate(); //http 요청 편하게 가능 Retrofit2 / okhttp/ resttemplate

        // HttpHeaders 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        // HttpEntity = HttpHeaders + HttpBody 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        // 파라미터로 HttpEntity를 받음
        // http 요청 포스트방식으로 / response 변수에 응답받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        KakaoProfile kakaoProfile = null;
        ObjectMapper objectMapper2 = new ObjectMapper();
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //User 오브젝트 : username, password, email
        String kakaoUsername = kakaoProfile.getKakao_account().getEmail() + kakaoProfile.getId();
        String kakaoEmail = kakaoProfile.getKakao_account().getEmail();
        // UUID 중복되지 않는 어떤 특정 값을 만들어 내는 알고리즘

        //UUID garbagePassword = UUID.randomUUID();

        User kakaoUser = User.builder()
                .username(kakaoUsername)
                .password(oosulzKey)
                .email(kakaoEmail)
                .build();

        // 가입자 여부 확인

        User originUser = userService.회원찾기(kakaoUser.getUsername());

        if (originUser.getUsername() == null){
            userService.회원가입(kakaoUser);
        }

        //로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),oosulzKey));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/";

    }
}
