<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/20/2022
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News Management: News View</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/news-view.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <a class="header-logo__title" href="${path}/controller?command=go_to_news_list_page">News Management</a>
    <section class="locale-links">
        <a class="link" href="">English</a>
        <a class="link" href="">Russian</a>
    </section>
</header>
<main class="main">
    <aside class="side-menu">
        <div class="side-menu-news">
            <h2 class="side-menu-news__title">News</h2>
            <ul class="side-menu-actions-list">
                <li class="side-menu-actions-list__item">
                    <a class="link" href="${path}/controller?command=go_to_news_list_page">News List</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link" href="${path}/controller?command=go_to_add_news_page">Add News</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="${path}/controller?command=go_to_news_list_page">News</a>
            <p class="admin-body-nav__item">>></p>
            <p class="admin-body-nav__item">News View</p>
        </section>
        <form class="admin-body-content" method="post" action="${path}/controller">
            <input type="hidden" name="command" value="delete_news">
            <c:forEach var="news" items="${all_news_ses}">
                <c:choose>
                    <c:when test="${news.id eq news_id_ses}">
                        <input type="hidden" name="news_id_to_delete" value="${news.id}">
                        <div class="admin-news-view">
                            <div class="admin-news-view__fieldset">
                                <label class="admin-news-view__label" for="newsTitle">
                                    News Title
                                </label>
                                <p id="newsTitle" class="admin-news-view__text">
                                        ${news.title}
                                </p>
                            </div>
                            <div class="admin-news-view__fieldset">
                                <label class="admin-news-view__label" for="newsDate">
                                    News Date
                                </label>
                                <p id="newsDate" class="admin-news-view__text">
                                        ${news.addedAt}
                                </p>
                            </div>
                            <div class="admin-news-view__fieldset">
                                <label class="admin-news-view__label" for="newsBrief">
                                    Brief
                                </label>
                                <p id="newsBrief" class="admin-news-view__text">
                                        ${news.summary}
                                </p>
                            </div>
                            <div class="admin-news-view__fieldset">
                                <label class="admin-news-view__label" for="newsContent">
                                    Content
                                </label>
                                <p id="newsContent" class="admin-news-view__text">
                                        ${news.content}
                                </p>
                            </div>
                        </div>
                        <section class="admin-body-content__actions">
                            <a href="${path}/controller?command=go_to_edit_news_page&news_id_to_edit=${news.id}">
                                <button type="button" class="admin-button">EDIT</button>
                            </a>
                            <input id="deleteButton" type="submit" class="admin-button" value="DELETE">
<%--                            <a href="${path}/controller?command=delete_news&news_id_to_delete=${news.id}">--%>
<%--                                <button id="deleteButton" type="button" class="admin-button">DELETE</button>--%>
<%--                            </a>--%>
                        </section>
                    </c:when>
                </c:choose>
            </c:forEach>
        </form>
    </section>
</main>
<script src="${path}/pages/static/js/admin/news-view.js"></script>
</body>
</html>
