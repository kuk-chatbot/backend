package com.kuk.chatbot.service;

import com.kuk.chatbot.dto.AnswerSummaryDto;
import com.kuk.chatbot.model.Answer;
import com.kuk.chatbot.model.RoleType;
import com.kuk.chatbot.model.User;
import com.kuk.chatbot.repository.AnswerRepository;
import com.kuk.chatbot.repository.QuestionRepository;
import com.kuk.chatbot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SummaryService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public byte[] 이미지불러오기(int id){
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid answer id: " + Integer.toString(id)));
        return answer.getResultImage();
    }

    @Transactional
    public List<AnswerSummaryDto> 결과요약(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (user.getRole().equals(RoleType.PERSONAL)){
            List<Object[]> results = answerRepository.loadSummaryByUserId(user.getId());
            List<AnswerSummaryDto> summaries = new ArrayList<>();
            for(Object[] result : results){
                AnswerSummaryDto dto = new AnswerSummaryDto(
                        (String) result[0],
                        (String) result[1],
                        (String) result[2],
                        (Integer) result[3],
                        (Integer) result[4],
                        (Integer) result[5],
                        (Integer) result[6],
                        (Integer) result[7],
                        (Integer) result[8],
                        (Integer) result[9],
                        (Integer) result[10]
                );
                summaries.add(dto);
            }
            return summaries;
        }
        List<Object[]> results = answerRepository.loadSummaryAll();
        List<AnswerSummaryDto> summaries = new ArrayList<>();
        for(Object[] result : results){
            AnswerSummaryDto dto = new AnswerSummaryDto(
                    (String) result[0],
                    (String) result[1],
                    (String) result[2],
                    (Integer) result[3],
                    (Integer) result[4],
                    (Integer) result[5],
                    (Integer) result[6],
                    (Integer) result[7],
                    (Integer) result[8],
                    (Integer) result[9],
                    (Integer) result[10]
            );
            summaries.add(dto);
        }
        return summaries;
    }

}
