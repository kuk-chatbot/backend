package com.kuk.chatbot.api;

import com.kuk.chatbot.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kuk.chatbot.service.SummaryService;

public class SummaryApiController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary")
    public ResponseDto<Object> summary(@RequestBody int id){
        System.out.println("히히");
        return new ResponseDto<Object>(HttpStatus.OK.value(), summaryService.결과요약(id));
    }

}