package com.kuk.chatbot.repository;

import com.kuk.chatbot.model.Reply;
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
