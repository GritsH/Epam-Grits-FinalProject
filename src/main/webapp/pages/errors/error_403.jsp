<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>403</title>
</head>
<body>
<h1>403. You don't have permission to visit this page</h1>
<a href="${path}/index.jsp">Go back to main page</a>
</body>
</html>
