package com.kuk.chatbot.api;

import com.kuk.chatbot.config.auth.PrincipalDetail;
import com.kuk.chatbot.dto.ReplySaveRequestDto;
import com.kuk.chatbot.dto.ResponseDto;
import com.kuk.chatbot.service.BoardService;
import com.kuk.chatbot.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;



    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board,principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board){
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    // 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다
    // dto 사용 x 이유 / 단순한 데이터이기 때문에
    @PostMapping("/api/board/{boardid}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDeleteById(@PathVariable int replyId){
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
