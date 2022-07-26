<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>404</title>
</head>
<body>
<h1>404. We don't have this page, and we are not sorry</h1>
<a href="${path}/index.jsp">Go back to main page</a>
</body>
</html>
