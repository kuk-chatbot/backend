package com.kuk.chatbot.api;

import com.kuk.chatbot.dto.AnswerSummaryDto;
import com.kuk.chatbot.service.SummaryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.kuk.chatbot.config.auth.PrincipalDetail;

import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@RestController
public class SummaryApiController {

    @Autowired
    private SummaryService summaryService;

    @CrossOrigin(origins = "http://kuk.solution:3000")
    @GetMapping("/motherboard/summary")
    public ResponseEntity<List<AnswerSummaryDto>> summaryForm(@AuthenticationPrincipal PrincipalDetail principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String role = principal.getUser().getRole().name();
        List<AnswerSummaryDto> summaries;

        if (role.equals("PERSONAL")) {
            summaries = summaryService.개인결과요약(principal.getUser().getId());
        } else if (role.equals("ENTERPRISE")) {
            summaries = summaryService.기업결과요약();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://kuk.solution:3000")
    @GetMapping("/motherboard/summary/{id}")
    public ResponseEntity<Void> showImageForm(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal, HttpServletResponse response) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        byte[] result = summaryService.이미지불러오기(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String imageBase64 = Base64.getEncoder().encodeToString(result);
        response.setContentType("text/plain");
        response.setHeader("Transfer-Encoding", "chunked");

        try (OutputStream os = response.getOutputStream()) {
            os.write(imageBase64.getBytes());
            os.flush();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}