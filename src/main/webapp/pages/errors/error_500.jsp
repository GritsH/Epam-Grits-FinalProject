<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>500</title>
</head>
<body>
<form action="${path}/controller">
    <h1>500</h1>
    <input type="hidden" name="command" value="/controller?command=go_to_news_page">
    Something went wrong, please go to main feed
    <button type="submit">go back</button>
</form>
</body>
</html>
