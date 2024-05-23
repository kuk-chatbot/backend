<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp" %>

<div class="container">
    <h2>Image</h2>
    <img src="data:image/jpeg;base64,${image}" alt="Image"/>
</div>

<%@ include file="layout/footer.jsp" %>