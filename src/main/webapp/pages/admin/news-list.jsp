<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/17/2022
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News Management: List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/admin/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/admin/news-list.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <a class="header-logo__title" href="">News Management</a>
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
                    <a class="link link_active" href="">News List</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link" href="../templates/admin/news-edit.html">Add News</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="">News</a>
            <p class="admin-body-nav__item">>></p>
            <p class="admin-body-nav__item">News List</p>
        </section>
        <form class="admin-body-content">
            <section class="admin-news-container">
                <article class="admin-news">
                    <div class="admin-news__header">
                        <label class="admin-news__title">News title</label>
                        <label class="admin-news__date">11/25/2005</label>
                    </div>
                    <p class="admin-news__text">
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
                    <section class="admin-news-actions">
                        <a class="admin-news-actions__link" href="../templates/admin/news-view.html">view</a>
                        <a class="admin-news-actions__link" href="../templates/admin/news-edit.html">edit</a>
                        <input class="admin-news-actions__checkbox"
                               type="checkbox">
                    </section>
                </article>
                <article class="admin-news">
                    <div class="admin-news__header">
                        <label class="admin-news__title">News title</label>
                        <p class="admin-news__date">11/25/2005</p>
                    </div>
                    <p class="admin-news__text">
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
                    <section class="admin-news-actions">
                        <a class="admin-news-actions__link" href="../templates/admin/news-view.html">view</a>
                        <a class="admin-news-actions__link" href="../templates/admin/news-edit.html">edit</a>
                        <input class="admin-news-actions__checkbox"
                               type="checkbox">
                    </section>
                </article>
                <article class="admin-news">
                    <div class="admin-news__header">
                        <label class="admin-news__title">News title</label>
                        <p class="admin-news__date">11/25/2005</p>
                    </div>
                    <p class="admin-news__text">
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
                    <section class="admin-news-actions">
                        <a class="admin-news-actions__link" href="../templates/admin/news-view.html">view</a>
                        <a class="admin-news-actions__link" href="../templates/admin/news-edit.html">edit</a>
                        <input class="admin-news-actions__checkbox"
                               type="checkbox">
                    </section>
                </article>
            </section>
            <button id="deleteButton" type="button" class="admin-button">DELETE</button>
        </form>
    </section>
</main>
<script src="../static/js/admin/news-view.js"></script>
</body>
</html>
