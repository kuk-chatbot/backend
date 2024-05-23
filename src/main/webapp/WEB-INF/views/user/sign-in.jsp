<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">

    <form action="/auth/sign-in" method="post">

        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="Enter Username" name="username" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>


        <button id="btn-login" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=fc9a495f12d3bf5094703df4a9d10434&redirect_uri=http://localhost:8000/auth/kakao/callback"><img height="38px" src="/image/kakao_login_button.png"/> </a>
    </form>

</div>

<%@ include file="../layout/footer.jsp" %>

