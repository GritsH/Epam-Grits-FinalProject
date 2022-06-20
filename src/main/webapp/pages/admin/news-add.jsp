<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/20/2022
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News Management: News Edit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/admin/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/static/css/admin/news-edit.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <a class="header-logo__title" href="news-list.jsp">News Management</a>
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
                    <a class="link" href="news-list.jsp">News List</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link link_active" href="">Add News</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="news-list.jsp">News</a>
            <p class="admin-body-nav__item">>></p>
            <p class="admin-body-nav__item">Add News</p>
        </section>
        <form class="admin-body-content" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="add_news">
            <div class="admin-news-view">
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsTitle">
                        News Title
                    </label>
                    <input id="newsTitle" class="admin-news-view__input"
                           type="text" maxlength="100" required name="news_title"
                           value="${news_data_ses['news_title_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsDate">
                        News Date
                    </label>
                    <input id="newsDate" class="admin-news-view__input"
                           type="date" maxlength="10" required name="news_added_at"
                           value="${news_data_ses['news_added_at_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsBrief">
                        Brief
                    </label>
                    <input type="text" id="newsBrief" class="admin-news-view__textarea"
                           maxlength="500" required name="news_summary" value="${news_data_ses['news_summary_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsContent">
                        Content
                    </label>
                    <input type="text" id="newsContent" maxlength="2048" required
                           class="admin-news-view__textarea" name="news_content"
                           value="${news_data_ses['news_content_ses']}">
                </div>
                <input type="hidden" name="news_author" value="${news_data_ses['news_author_ses'] = 'admin@gmail.com'}">
            </div>
            <section class="admin-body-content__actions">
                <input type="submit" class="admin-button" value="SAVE">
                <a href="${path}/controller?command=go_to_news_list_page">
                    <button type="button" class="admin-button">CANCEL</button>
                </a>
            </section>
        </form>
    </section>
</main>
<script src="${pageContext.request.contextPath}/pages/static/js/admin/news-edit.js"></script>
</body>
</html>