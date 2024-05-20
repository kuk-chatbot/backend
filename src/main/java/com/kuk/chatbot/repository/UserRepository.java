package com.kuk.chatbot.repository;

import com.kuk.chatbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// DataAccessObject
// 자동으로 Bean 등록 @Repository 생략가능
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
