<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/17/2022
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News Management: List</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/news-list.css">
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
            <label class="side-menu-news__title">News</label>
            <ul class="side-menu-actions-list">
                <li class="side-menu-actions-list__item">
                    <a class="link link_active" href="${path}/controller?command=go_to_news_list_page">News List</a>
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
            <p class="admin-body-nav__item">News List</p>
        </section>
        <form class="admin-body-content" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="delete_news">
            <section class="admin-news-container">
                <c:forEach var="news" items="${all_news_ses}">
                    <article class="admin-news">
                        <div class="admin-news__header">
                            <label class="admin-news__title">${news.title}</label>
                            <label class="admin-news__date">${news.addedAt}
                            </label>
                        </div>
                        <p class="admin-news__text">
                                ${news.content}
                        </p>
                        <section class="admin-news-actions">
                            <a class="admin-news-actions__link"
                               href="${path}/controller?command=go_to_news_view_page&news_id=${news.id}">view</a>
                            <a class="admin-news-actions__link"
                               href="${path}/controller?command=go_to_edit_news_page&news_id_to_edit=${news.id}">edit</a>
                            <input class="admin-news-actions__checkbox"
                                   type="checkbox" name="checkbox_id" value="${news.id}">
                        </section>
                    </article>
                </c:forEach>
            </section>
            <input id="deleteButton" type="submit" class="admin-button" value="DELETE">
        </form>
    </section>
</main>
<script src="../static/js/admin/news-view.js"></script>
</body>
</html>
