package com.oosulz.blog.repository;

import com.oosulz.blog.dto.ReplySaveRequestDto;
import com.oosulz.blog.model.Board;
import com.oosulz.blog.model.Reply;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    @Modifying
    @Query(value="INSERT INTO reply(boardId, userId, content, createDate) VALUES (?1,?2,?3,now())" ,nativeQuery = true)
    int mSave(int boardId, int userId, String content);
}
