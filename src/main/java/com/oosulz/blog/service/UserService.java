package com.oosulz.blog.service;

import com.oosulz.blog.model.User;
import com.oosulz.blog.repository.UserRepository;
import jakarta.persistence.TableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void 회원가입(User user){
        userRepository.save(user);
    }
    @Transactional(readOnly = true) //Select 시 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
    public Optional<User> 로그인(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
