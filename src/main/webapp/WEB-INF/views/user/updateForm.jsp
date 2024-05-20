<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">

    <form action="/action_page.php" class="was-validated">
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Enter Username" name="username" readonly>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password"  class="form-control" id="password" placeholder="Enter password" name="password">
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" value="${principal.user.name}" class="form-control" id="name" placeholder="Enter Name" name="name">
        </div>

    </form>
    <button id="btn-update" class="btn btn-primary">회원수정 완료</button>

</div>


<script src="/js/user.js "></script>
<%@ include file="../layout/footer.jsp" %>

