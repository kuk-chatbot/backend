<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp" %>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="image" accept="image/*" required />
    <input type="text" name="modelName" placeholder="Model Name" required />
    <input type="text" name="cause" placeholder="Cause" required />
    <button type="submit">Upload</button>
</form>

<%@ include file="layout/footer.jsp" %>