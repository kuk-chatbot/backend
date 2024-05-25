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
    public List<AnswerSummaryDto> 개인결과요약(int userId) {
        List<Object[]> results = answerRepository.loadSummaryByUserId(userId);
        return Dto변환(results);
    }

    @Transactional
    public List<AnswerSummaryDto> 기업결과요약() {
        List<Object[]> results = answerRepository.loadSummaryAll();
        return Dto변환(results);
    }

    private List<AnswerSummaryDto> Dto변환(List<Object[]> results) {
        List<AnswerSummaryDto> summaries = new ArrayList<>();
        for (Object[] result : results) {
            AnswerSummaryDto dto = new AnswerSummaryDto(
                    (String) result[0],  // name
                    (String) result[1],   // cause
                    (String) result[2],   // modelName
                    (Integer) result[3],   // cpuFanNoScrews
                    (Integer) result[4],  // cpuFanPortDetached
                    (Integer) result[5],  // cpuFanScrewsLoose
                    (Integer) result[6],  // incorrectScrews
                    (Integer) result[7],  // looseScrews
                    (Integer) result[8],  // noScrews
                    (Integer) result[9],  // scratch
                    (Integer) result[10]  // id
            );
            summaries.add(dto);
        }
        return summaries;
    }

}
