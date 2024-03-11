<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">

    <form action="/action_page.php" class="was-validated">

        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter Username" name="username" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember" required> 실습용 페이지지만, 약관에 동의합니다.
                <div class="valid-feedback">약관에 동의했습니다.</div>
                <div class="invalid-feedback">약관에 동의해주세요.</div>
            </label>
        </div>

    </form>
    <button id="btn-login" class="btn btn-primary">로그인</button>
</div>
<script src="/blog/js/user.js "></script>
<%@ include file="../layout/footer.jsp" %>

