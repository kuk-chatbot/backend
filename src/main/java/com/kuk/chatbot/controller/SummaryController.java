package com.kuk.chatbot.controller;

import com.kuk.chatbot.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary")
    public String summaryForm(Model model){
        model.addAttribute("summaries", summaryService.결과요약());
        return "summary";
    }
}
