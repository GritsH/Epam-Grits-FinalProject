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
    <link rel="stylesheet" href="../../static/css/base.css">
    <link rel="stylesheet" href="../../static/css/admin/base.css">
    <link rel="stylesheet" href="../../static/css/admin/news-view.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <a class="header-logo__title" href="news-list.html">News Management</a>
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
                    <a class="link" href="news-list.html">News List</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link" href="news-edit.html">Add News</a>
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
        <form class="admin-body-content">
            <div class="admin-news-view">
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsTitle">
                        News Title
                    </label>
                    <p id="newsTitle" class="admin-news-view__text">
                        Lorem ipsum
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsDate">
                        News Date
                    </label>
                    <p id="newsDate" class="admin-news-view__text">
                        11/25/2005
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsBrief">
                        Brief
                    </label>
                    <p id="newsBrief" class="admin-news-view__text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore
                        magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat.
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsContent">
                        Content
                    </label>
                    <p id="newsContent" class="admin-news-view__text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore
                        magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in
                        reprehenderit in voluptate velit esse cillum dolore eu
                        fugiat nulla pariatur. Excepteur sint occaecat cupidatat
                        non proident, sunt in culpa qui officia deserunt mollit
                        anim id est laborum.
                    </p>
                </div>
            </div>
            <section class="admin-body-content__actions">
                <a href="news-edit.html">
                    <button type="button" class="admin-button">EDIT</button>
                </a>
                <button id="deleteButton" type="button" class="admin-button">DELETE</button>
            </section>
        </form>
    </section>
</main>
<script src="${pageContext.request.contextPath}/pages/static/js/admin/news-view.js"></script>
</body>
</html>
