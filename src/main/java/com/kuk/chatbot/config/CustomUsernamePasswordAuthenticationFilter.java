package com.kuk.chatbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if ("application/json".equals(request.getContentType())) {
            try {
                Map<String, String> requestBody = objectMapper.readValue(request.getInputStream(), Map.class);
                String username = requestBody.get("username");
                String password = requestBody.get("password");

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return super.attemptAuthentication(request, response);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        // 성공적인 인증 후 JSON 응답
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", "여기에 JWT 토큰을 추가하세요"); // JWT 토큰 생성 후 추가
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        // 실패한 인증 후 JSON 응답
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("errorType", "Bad Request");
        responseBody.put("msg", "에러 발생");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
