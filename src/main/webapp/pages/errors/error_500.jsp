<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>500</title>
</head>
<body>
<h1>500. We screwed up, please go to main page</h1>
<h2>Let's hope that will help</h2>
<a href="${path}/index.jsp">Go back to main page</a>
</body>
</html>
