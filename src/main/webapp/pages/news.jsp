<%@ page import="java.util.List" %>
<%@ page import="by.grits.news.entities.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.grits.news.service.NewsService" %>
<%@ page import="by.grits.news.service.impl.NewsServiceImpl" %>
<%@ page import="by.grits.news.service.exception.ServiceException" %>
<%@ page import="by.grits.news.command.exception.CommandException" %><%--
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


<%
    NewsService newsService = NewsServiceImpl.getInstance();
    List<News> allNews;
    try {
        allNews = newsService.findAllNews();
    } catch (ServiceException e) {
        throw new CommandException(e);
    }
%>

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
                <c:choose>
                    <c:when test="${current_role eq 'UNKNOWN'}">
                        <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
                        <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
                    </c:when>
                    <c:otherwise>
                        <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log out</a>
                        <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
                    </c:otherwise>
                </c:choose>
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
                <%for(News news: allNews){%>
                <article class="news-article">
                    <a class="news-article__title" href="news-details.html"><%=news.getTitle()%></a>
                    <p class="news-article__text"><%=news.getContent()%></p>
                    <div class="news-article-date">
                        <img class="news-article-date__img"
                             src="${pageContext.request.contextPath}/pages/static/img/calendar.svg" alt="Date">
                        <p class="news-article-date__text"><%=news.getAddedAt()%></p>
                    </div>
                </article>
                <%}%>
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
