<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/14/2022
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/news.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/news-article.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>

<c:choose>
    <c:when test="${current_role eq 'ADMIN'}">
        <jsp:forward page="/controller?command=go_to_news_list_page"/>
    </c:when>
    <c:otherwise>
        <header class="header">
            <div class="header-logo">
                <img class="header-logo__icon" src="${pageContext.request.contextPath}/pages/static/img/news.svg"
                     alt="News icon">
                <a class="header-logo__title" href="">News</a>
            </div>
            <div class="header-auth">
                <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
                <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
            </div>
        </header>
        <main class="main">

            <section class="display-options">
                <div class="sort">
                    <label for="sortSelect" class="sort__label">
                        Date:
                    </label>
                    <select id="sortSelect" class="sort__select">
                        <option value="asc">Ascending ↑</option>
                        <option value="desc">Descending ↓</option>
                    </select></div>
            </section>
            <section class="articles-container">
                <article class="news-article">
                    <a class="news-article__title" href="news-details.html">Lorem ipsum</a>
                    <p class="news-article__text"> Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in
                        voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa
                        qui officia deserunt mollit anim id est laborum.</p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${pageContext.request.contextPath}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text">12.06.2022</p>
                    </div>
                </article>
                <article class="news-article">
                    <a class="news-article__title" href="news-details.html">Lorem ipsum</a>
                    <p class="news-article__text">Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Orci dapibus ultrices in iaculis
                        nunc. Quis lectus nulla at volutpat. Egestas pretium aenean
                        pharetra magna ac. Sed velit dignissim sodales ut eu sem.
                        Suscipit tellus mauris a diam maecenas. Vel pretium lectus quam
                        id leo in vitae turpis. Arcu cursus euismod quis viverra.
                        Tincidunt praesent semper feugiat nibh sed pulvinar proin. Sit
                        amet consectetur adipiscing elit. Orci eu lobortis elementum
                        nibh tellus molestie nunc. In vitae turpis massa sed elementum
                        tempus egestas sed. Sit amet venenatis urna cursus. Odio tempor
                        orci dapibus ultrices in.</p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${pageContext.request.contextPath}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text">10.06.2022</p>
                    </div>
                </article>
                <article class="news-article">
                    <a class="news-article__title" href="news-details.html">Lorem ipsum</a>
                    <p class="news-article__text"> Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in
                        voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa
                        qui officia deserunt mollit anim id est laborum.</p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${pageContext.request.contextPath}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text">11.06.2022</p>
                    </div>
                </article>
                <article class="news-article">
                    <a class="news-article__title" href="news-details.html">Lorem ipsum</a>
                    <p class="news-article__text"> Lorem ipsum dolor sit amet,
                        consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in
                        voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa
                        qui officia deserunt mollit anim id est laborum.</p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${pageContext.request.contextPath}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text">09.06.2022</p>
                    </div>
                </article>
            </section>
            <section class="pagination">
                <a class="pagination__item pagination__item_active" href="">1</a>
                <a class="pagination__item" href="">2</a>
                <a class="pagination__item" href="">3</a>
            </section>
        </main>
    </c:otherwise>
</c:choose>

</body>
</html>
