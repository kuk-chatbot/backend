package com.kuk.chatbot.api;

import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DashboardApiController {

    @GetMapping("/dashboard/account")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
