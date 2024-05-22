package com.kuk.chatbot.api;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.service.QuestionService;
import com.kuk.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("image") MultipartFile file,
                                   @RequestParam("modelName") String modelName,
                                   @RequestParam("cause") String cause) {  // 매개변수 이름 변경
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            User user = userService.회원찾기(username);

            int questionId = questionService.saveQuestion(file, modelName, cause, user.getId());


            // 파이썬 스크립트를 실행
            ProcessBuilder pb = new ProcessBuilder("C:/Users/User/Desktop/capstone/mother_yolov7/yolov7/venv/Scripts/python.exe", "C:/Users/User/Desktop/capstone/mother_yolov7/yolov7/detect.py",
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
                return "File uploaded and Python script executed successfully";
            } else {
                return "Failed to execute Python script, exit code: " + exitCode;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Failed to upload file and execute Python script";
        }
    }
}
