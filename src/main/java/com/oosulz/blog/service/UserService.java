package com.oosulz.blog.service;

import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.repository.UserRepository;
import jakarta.persistence.TableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;



    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
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
    public void 회원수정(User user){
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고. 영속화된 User object 수정
        //select를 해서 user 오브젝트를 db로부터 가져오면 == 영속화
        // 영속화된 오브젝트를 변경하면 알아서 db에 update문 날려줌
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());


        // 회원 수정 함수 종료시 = service 종료 == 트랜잭션 종료 = 커밋자동
        // 영속화된 persistance 객체의 변화 감지시 더티체킹이 되어 변화된 것들에 대해 update문 자동화 날려줌
    }

    /*
    @Transactional(readOnly = true) //Select 시 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
    public Optional<User> 로그인(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

    }
     */
}
