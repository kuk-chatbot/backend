package com.oosulz.blog.repository;

import com.oosulz.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DataAccessObject
// 자동으로 Bean 등록 @Repository 생략가능
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
