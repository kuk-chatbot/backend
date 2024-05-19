package com.oosulz.blog.config;

import com.oosulz.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration // IoC 빈등록
@EnableWebSecurity // 시큐리티 필터가 등록
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PrincipalDetailService principalDetailService;



    @Bean
    BCryptPasswordEncoder encode() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(a -> {
            a.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d+"+"/dummy/\\d+")).permitAll()
                    .requestMatchers("/users/**", "/board/**").authenticated()
                    .anyRequest().permitAll();
        });
        // 스프링 시큐리티가 해당 주소로 요청 오는
        // 로그인을 가로채서 대신 로그인
        http.formLogin(
                f -> {
                    f.loginPage("/auth/loginForm").loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/").failureUrl("/auth/loginForm");
                });

        //인증 주소 설정
        return http.build();
    }
}