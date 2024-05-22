package com.kuk.chatbot.service;

import com.kuk.chatbot.model.Question;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.repository.QuestionRepository;
import com.kuk.chatbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;  // @Autowired 추가

    public int saveQuestion(MultipartFile file, String modelName, String cause, int userId) throws IOException {
        Question question = new Question();
        question.setImage(file.getBytes());
        question.setModelName(modelName);
        question.setCause(cause);
        question.setCreateDate(new Timestamp(System.currentTimeMillis()));

        // 사용자 설정
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        question.setUser(user);

        questionRepository.save(question);
        return question.getId(); // 저장된 질문의 ID 반환
    }
}