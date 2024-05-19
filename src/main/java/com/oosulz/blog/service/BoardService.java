package com.oosulz.blog.service;

import com.oosulz.blog.dto.ReplySaveRequestDto;
import com.oosulz.blog.model.Board;
import com.oosulz.blog.model.Reply;
import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.repository.BoardRepository;
import com.oosulz.blog.repository.ReplyRepository;
import com.oosulz.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ReadOnlyBufferException;
import java.util.List;

//@RequiredArgsConstructor => 생성자에 아규먼트가 필요한 애들을 전부 주입해줘
@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    /* @Autowired 와 동일
    public BoardService(BoardRepository bRepo,ReplyRepository rRepo){
        this.boardRepository = bRepo;
        this.replyRepository = rRepo;

    private final BoardRepository boardRepository;
     final은 꼭 초기화가 되어야함 / RequiredArgsConstructor로 초기화 쉽게 가능
     */

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;


    @Transactional
    public void 글쓰기(Board board, User user) { //title,content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) { //title,content
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void 글삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id,Board requestboard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
                }); //영속화 시키기
        board.setTitle(requestboard.getTitle());
        board.setContent(requestboard.getContent());
        //해당 함수로 종료시(service 종료시) 트렌젝션이 종료 -> 더티체킹 자동 업데이트(db flush)
    }
    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto){
        /* 네이티브 쿼리 적용 전
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
                }); //영속화 시키기

        User user = userRepository.findById(replySaveRequestDto.getUserId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("유저 찾기 실패: 아이디를 찾을 수 없습니다.");
                }); //영속화 시키기

        Reply reply = new Reply();
        reply.update(user,board,replySaveRequestDto.getContent());
        */
        replyRepository.mSave(replySaveRequestDto.getBoardId(),replySaveRequestDto.getUserId(),replySaveRequestDto.getContent());

    }
    @Transactional
    public void 댓글삭제(int id) {
        replyRepository.deleteById(id);
    }

}
