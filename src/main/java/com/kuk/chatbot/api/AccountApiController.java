package com.kuk.chatbot.api;

import com.kuk.chatbot.config.auth.PrincipalDetail;
import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.dto.UserDto;
import com.kuk.chatbot.dto.UserEnterpriseDto;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountApiController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://kuk.solution:3000")
    @GetMapping("/motherboard/account")
    public ResponseEntity<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        User currentUser = principal.getUser();

        UserDto userDTO = new UserDto(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getName(),
                currentUser.getRole(),
                currentUser.getUserlimit(),
                currentUser.getMemory(),
                currentUser.getCores(),
                currentUser.getSockets()
        );

        return ResponseEntity.ok(userDTO);
    }

    @CrossOrigin(origins = "http://kuk.solution:3000")
    @PutMapping("/motherboard/account")
    public ResponseDto<Integer> accountUpdate(@AuthenticationPrincipal PrincipalDetail principal, @RequestBody UserEnterpriseDto userEnterpriseDto) {
        System.out.println("UserApiController account 호출");

        // 현재 인증된 사용자 가져오기
        User currentUser = principal.getUser();
        if (currentUser == null) {
            return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), 0);
        }

        // 사용자 정보 업데이트 (비밀번호는 수정하지 않음)
        userService.회원수정(currentUser.getId(), userEnterpriseDto.getUserlimit(), userEnterpriseDto.getMemory(), userEnterpriseDto.getCores(), userEnterpriseDto.getSockets());

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
