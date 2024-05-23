<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp" %>
<div class="container">
    <h2>SUMMARY</h2>
        <p>이름   증상   모델명   CPU팬 나사빠짐   CPU팬포트분리   CPU팬 나사느슨함   나사이상함   나사느슨함   나사빠짐   긁?</p>
        <c:forEach var="summary" items="${summaries}">
            <p>${summary.name} ${summary.cause} ${summary.modelName} ${summary.cpuFanNoScrews} ${summary.cpuFanPortDetached} ${summary.cpuFanScrewsLoose} ${summary.incorrectScrews} ${summary.looseScrews} ${summary.noScrews} ${summary.scratch}
                <button type="button" onclick="location.href='summary/${summary.id}'">이미지 확인</button>
            </p>
        </c:forEach>
</div>

<%@ include file="layout/footer.jsp" %>