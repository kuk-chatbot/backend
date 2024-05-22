package com.kuk.chatbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload"; // 업로드 폼을 제공하는 JSP 또는 HTML 페이지

    }
}