package com.kuk.chatbot.controller;

import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Base64;

@Controller
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary")
    public String summaryForm(Model model){
        model.addAttribute("summaries", summaryService.결과요약());
        return "summary";
    }

    @GetMapping("/summary/{id}")
    public String showImageForm(@PathVariable int id, Model model){
        byte[] result = summaryService.이미지불러오기(id);
        String imageBase64 = Base64.getEncoder().encodeToString(result);
        model.addAttribute("image", imageBase64);
        return "summary_detail";
    }

}
