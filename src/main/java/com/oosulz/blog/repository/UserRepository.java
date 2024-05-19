package com.oosulz.blog.repository;

import com.oosulz.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// DataAccessObject
// 자동으로 Bean 등록 @Repository 생략가능
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
//JPA 네이밍전략(쿼리)
// SELECT * FROM user WHERE username = ? AND password = ?
// Optional<User> findByUsernameAndPassword(String username, String password);

//@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//User login(String username, String password);
