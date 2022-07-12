<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News details</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/news-article.css">
    <link rel="stylesheet" href="${path}/pages/static/css/news-details.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>

<header class="header">
    <div class="header-logo">
        <img class="header-logo__icon" src="${path}/pages/static/img/news.svg"
             alt="News icon">
        <a class="header-logo__title" href="${path}/controller?command=go_to_news_page">News</a>
    </div>
    <div class="header-auth">
        <c:choose>
            <c:when test="${current_role eq 'UNKNOWN'}">
                <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
                <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
            </c:when>
            <c:otherwise>
                <a class="header-auth__item" href="${path}/controller?command=logout">Log out</a>
                <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<main class="main">
    <c:forEach var="news" items="${all_news_ses}">
        <c:choose>
            <c:when test="${news.id eq news_id_ses}">
                <article class="news-article" style="max-height: 100%">
                    <h2 class="news-article__title">${news.title}</h2>
                    <p class="news-article__text">${news.content}</p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${path}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text">${news.addedAt}</p>
                    </div>
                </article>
            </c:when>
        </c:choose>

    </c:forEach>
</main>
</body>
</html>
