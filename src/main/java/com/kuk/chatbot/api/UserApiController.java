package com.kuk.chatbot.api;

import com.kuk.chatbot.config.auth.PrincipalDetail;
import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.dto.UserDto;
import com.kuk.chatbot.dto.UserEnterpriseDto;
import com.kuk.chatbot.model.RoleType;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @CrossOrigin(origins = "http://kuk.solution:3000")
    @PostMapping("/auth/sign-up")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController save 호출");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @CrossOrigin(origins = "http://kuk.solution:3000")
    @GetMapping("/auth/user")
    public ResponseEntity<RoleType> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        User currentUser = principal.getUser();
        return ResponseEntity.ok(currentUser.getRole());
    }

}
