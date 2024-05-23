package com.kuk.chatbot.service;

import com.kuk.chatbot.dto.AnswerSummaryDto;
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
                        (Integer) result[9]
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
                    (Integer) result[9]
            );
            summaries.add(dto);
        }
        return summaries;

        /*
        if(user.getRole().equals(RoleType.PERSONAL))    answers = answerRepository.findByUserId(user.getId());
        else    answers = answerRepository.findAll();


        List<Answer> answers;
        List<String> result = new ArrayList<>();

        String name = user.getName();

        for(int i=0;i<answers.size();i++){
            Answer answer = answers.get(i);
            Question question = answer.getQuestion();

            String cause = question.getCause();
            String modelname = question.getModelName();

            String temp = "{ \"name\": " + "\"" + name + ", \"cause\": " + cause + ", \"model\": " + modelname;

            int cpuFanNoScrews = answer.getCpuFanNoScrews();
            temp += ", \"cpuFanNoScrews\": " + Integer.toString(cpuFanNoScrews);
            int cpuFanPortDetached = answer.getCpuFanPortDetached();
            temp += ", \"cpuFanPortDetached\": " + Integer.toString(cpuFanPortDetached);
            int cpuFanScrewsLoose = answer.getCpuFanScrewsLoose();
            temp += ", \"cpuFanScrewsLoose\": " + Integer.toString(cpuFanScrewsLoose);
            int incorrectScrews = answer.getIncorrectScrews();
            temp += ", \"incorrectScrews\": " + Integer.toString(incorrectScrews);
            int looseScrews = answer.getLooseScrews();
            temp += ", \"looseScrews\": " + Integer.toString(looseScrews);
            int noScrews = answer.getNoScrews();
            temp += ", \"noScrews\": " + Integer.toString(noScrews);
            int scratch = answer.getScratch();
            temp += ", \"scratch\": " + Integer.toString(scratch);


            result.add(temp);

        }
        return result;

         */
    }

}
