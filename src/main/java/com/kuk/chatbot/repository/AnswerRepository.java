package com.kuk.chatbot.repository;

import com.kuk.chatbot.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value="SELECT * FROM Answer WHERE user.id = :id", nativeQuery=true)
    List<Answer> findByUserId(@Param("id") int id);
}
