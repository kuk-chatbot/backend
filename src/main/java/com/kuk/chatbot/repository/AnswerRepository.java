package com.kuk.chatbot.repository;

import com.kuk.chatbot.dto.AnswerSummaryDto;
import com.kuk.chatbot.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value="select C.name, B.cause, B.modelName, A.cpuFanNoScrews, A.cpuFanPortDetached, A.cpuFanScrewsLoose, A.incorrectScrews, A.looseScrews, A.noScrews, A.scratch, B.id " +
            "from " +
            "(select id, name from user) as C, " +
            "(select id, userId, cause, modelName from question) as B, " +
            "(select questionId, cpuFanNoScrews, cpuFanPortDetached, cpuFanScrewsLoose, incorrectScrews, looseScrews, noScrews, scratch from answer) as A " +
            "where C.id = B.userId and B.id = A.questionId and B.userId = :id", nativeQuery=true)
    List<Object[]> loadSummaryByUserId(@Param("id") int id);

    @Query(value="select C.name, B.cause, B.modelName, A.cpuFanNoScrews, A.cpuFanPortDetached, A.cpuFanScrewsLoose, A.incorrectScrews, A.looseScrews, A.noScrews, A.scratch, B.id " +
            "from " +
            "(select id, name from user) as C, " +
            "(select id, userId, cause, modelName from question) as B, " +
            "(select questionId, cpuFanNoScrews, cpuFanPortDetached, cpuFanScrewsLoose, incorrectScrews, looseScrews, noScrews, scratch from answer) as A " +
            "where C.id = B.userId and B.id = A.questionId", nativeQuery=true)
    List<Object[]> loadSummaryAll();

    @Query("SELECT SUM(a.cpuFan), SUM(a.cpuFanNoScrews), SUM(a.cpuFanPort), SUM(a.cpuFanPortDetached), SUM(a.cpuFanScrews), SUM(a.cpuFanScrewsLoose), SUM(a.incorrectScrews), SUM(a.looseScrews), SUM(a.noScrews), SUM(a.scratch), SUM(a.screws) FROM Answer a")
    List<Object[]> loadAllFaultsSummary();
}
