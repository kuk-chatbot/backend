package com.kuk.chatbot.api;

import com.kuk.chatbot.config.auth.PrincipalDetail;
import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.QuestionService;
import com.kuk.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class imageApiController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping("/motherboard/upload")
    public ResponseDto<Integer> handleFileUpload(@RequestParam("image") MultipartFile file,
                                                 @RequestParam("modelName") String modelName,
                                                 @RequestParam("cause") String cause,
                                                 @AuthenticationPrincipal PrincipalDetail principal) {
        try {
            // 현재 인증된 사용자 가져오기
            User currentUser = principal.getUser();
            if (currentUser == null) {
                return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), 0);
            }

            int questionId = questionService.saveQuestion(file, modelName, cause, currentUser.getId());

            // 파이썬 스크립트를 실행
            ProcessBuilder pb = new ProcessBuilder("C:/Users/User/Desktop/capstone/mother_yolov7/yolov7/venv/Scripts/python.exe",
                    "C:/Users/User/Desktop/capstone/mother_yolov7/yolov7/detect.py",
                    "--question-id", String.valueOf(questionId),
                    "--weights", "C:/Users/User/Desktop/capstone/mother_yolov7/yolov7/best.pt",
                    "--conf-thres", "0.25",
                    "--img-size", "1024");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // 프로세스의 출력을 읽어 로그에 출력
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // 로그에 출력하거나 필요한 곳에 사용
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return new ResponseDto<>(HttpStatus.OK.value(), 1);
            } else {
                return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0);
        }
    }
}

