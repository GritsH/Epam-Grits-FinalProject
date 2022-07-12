<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>404</title>
</head>
<body>
<form action="${path}/controller">
    <h1>404</h1>
    <input type="hidden" name="command" value="/controller?command=go_to_news_page">
    <button type="submit">Go to main page</button>
</form>
</body>
</html>
