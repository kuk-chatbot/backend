package com.kuk.chatbot.service;

import com.kuk.chatbot.model.Answer;
import com.kuk.chatbot.model.RoleType;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.repository.AnswerRepository;
import com.kuk.chatbot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;

import java.util.List;

public class SummaryService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Answer> 결과요약(int id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("결과 불러오기 실패: 사용자를 찾을 수 없습니다.");
                });
        RoleType role = user.getRole();
        return answerRepository.findByUserId(id);
    }

}
