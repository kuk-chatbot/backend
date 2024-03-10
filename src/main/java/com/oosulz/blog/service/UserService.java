package com.oosulz.blog.service;

import com.oosulz.blog.model.User;
import com.oosulz.blog.repository.UserRepository;
import jakarta.persistence.TableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void 회원가입(User user){
        userRepository.save(user);
    }
}
