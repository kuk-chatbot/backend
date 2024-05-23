package com.kuk.chatbot.api;

import com.kuk.chatbot.dto.AnswerSummaryDto;
import com.kuk.chatbot.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kuk.chatbot.service.SummaryService;

import java.util.List;

@RestController
public class SummaryApiController {

    @Autowired
    private SummaryService summaryService;

}