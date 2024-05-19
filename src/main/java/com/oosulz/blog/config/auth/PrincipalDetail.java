package com.oosulz.blog.config.auth;

import com.oosulz.blog.model.User;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다
@Getter
public class PrincipalDetail implements UserDetails {
    public PrincipalDetail(User user) {
        this.user = user;
    }
    private User user; //컴포지션
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 리턴 여부 (true 만료 x)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정 잠김 리턴 여부 (true 잠김 x)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 비밀번호 만료 여부 (true 만료 x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정 활성화 여부 (true 활성화 )
    @Override
    public boolean isEnabled() {
        return true;
    }
    // 계정 상태 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()-> {return "ROLE_"+user.getRole();});
        return collectors;
    }
}
