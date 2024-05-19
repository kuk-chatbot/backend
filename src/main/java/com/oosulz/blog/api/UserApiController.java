package com.oosulz.blog.api;

import com.oosulz.blog.config.auth.PrincipalDetail;
import com.oosulz.blog.dto.ResponseDto;
import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.service.BoardService;
import com.oosulz.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController save 호출");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    //기본 로그인 (security 적용x)
    /*
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){ //username, password, email
        System.out.println("UserApiController login 호출");
        User principal = userService.로그인(user).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. username: " + user.getUsername())); //principal(접근주체)
        if (principal != null){
            session.setAttribute("principal",principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    */

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController update 호출");
        userService.회원수정(user);
        // 트랜잭션이 종료 -> db에는 값적용
        // 세션값이 변경 되지 않기 때문에 직접 세션값을 변경해주어야함
        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
