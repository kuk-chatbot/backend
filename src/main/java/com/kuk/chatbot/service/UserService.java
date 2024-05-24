package com.kuk.chatbot.service;

import com.kuk.chatbot.model.User;
import com.kuk.chatbot.model.RoleType;
import com.kuk.chatbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;



    @Transactional
    public void 회원가입(User user){
        user.setUsername(user.getUsername());
        user.setName(user.getName());
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(user.getRole());
        user.setUserlimit(user.getUserlimit());
        user.setMemory(user.getMemory());
        user.setCores(user.getCores());
        user.setSockets(user.getSockets());
        userRepository.save(user);
    }

    @Transactional
    public User 회원찾기(String username){
        User user = userRepository.findByUsername(username).orElseGet(()->{
          return new User();
        });
        return user;
    }

    @Transactional
    public User 회원아이디찾기(int id){
        User user = userRepository.findById(id).orElseGet(()->{
            return new User();
        });
        return user;
    }

    @Transactional
    public void 회원수정(int userId, Integer userlimit, Integer memory, Integer cores, Integer sockets) {
        User persistence = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패"));

        persistence.setUserlimit(userlimit);
        persistence.setMemory(memory);
        persistence.setCores(cores);
        persistence.setSockets(sockets);
    }
}
