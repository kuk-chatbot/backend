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

        <div class="form-group">
            <label for="name">name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember" required> 실습용 페이지이기 때문에, 중요 개인정보를 입력하지 않을 것을 동의합니다.
                <div class="valid-feedback">약관에 동의했습니다.</div>
                <div class="invalid-feedback">약관에 동의해주세요.</div>
            </label>
        </div>

    </form>
    <button id="btn-save" class="btn btn-primary">회원가입 완료</button>

</div>


<script src="/js/user.js "></script>
<%@ include file="../layout/footer.jsp" %>

