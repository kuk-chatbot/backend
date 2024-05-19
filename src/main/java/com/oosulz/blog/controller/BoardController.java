package com.oosulz.blog.controller;

import com.oosulz.blog.config.auth.PrincipalDetail;
import com.oosulz.blog.model.Board;
import com.oosulz.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.StreamSupport;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model,@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) { //컨트롤러에서 세션을 어떻게 찾냐?
        // /WEB-INF/views"/"{index.jsp}
        model.addAttribute("boards",boardService.글목록(pageable));
        return "index"; //일반 컨트롤러라서 viewResolver 작동 / index 페이지에 model의 정보 들고감 / model jsp에선 request 역할 /
    }
    @GetMapping("/board/saveForm")
    public String saveForm() { //컨트롤러에서 세션을 어떻게 찾냐?
        // /WEB-INF/views"/"{index.jsp}
        return "board/saveForm";
    }
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id,Model model) { //컨트롤러에서 세션을 어떻게 찾냐?
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/updateForm";
    }
    @GetMapping("board/{id}")
    public String findById(@PathVariable int id,Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/detail";
    }
}
