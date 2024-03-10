package com.oosulz.blog.test;

import com.oosulz.blog.model.RoleType;
import com.oosulz.blog.model.User;
import com.oosulz.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyController {


    @Autowired //의존성 주입
    private UserRepository userRepository; //초기 null

    // http://localhost:8000/blog/dummy/join(요청)
    // http의 body에 username,password,email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { //key=value(약속된 규칙)
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        System.out.println("email:" + user.getEmail());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

    // id로 파라미터 값 전달 가능
    // http://localhost:8000/blog/dummy/user/3 (요청)
    // 없는 user를 찾으면 null 리턴 되는 것 방지 -> optional로 객체 감싸줄테니 개발자가 판단하기
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id :" + id));
        // 요청 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹 브라우저가 이해할 수 있는 데이터) -> Json
        // springboot messageconverter 라는 애가 응답시에 자동 작동
        // 만약 자바 오브젝트 리턴시, message converter가 jackson 라이브러리 호출
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
        return user;
    }

    //http://localhost:8000/blog/dummy/users/
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지당 2건의 데이터를 리턴받음
    @GetMapping("/dummy/user/")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }
    //@PutMapping("/dummy/user/{id}")
    //public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

    //}

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id :" + id);
        System.out.println("password :" + requestUser.getPassword());
        System.out.println("email :" + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        return null;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id:" + id;
    }
}
