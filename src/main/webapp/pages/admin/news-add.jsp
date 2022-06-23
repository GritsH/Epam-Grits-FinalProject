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
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="link.add_news" var="add_news"/>
<fmt:message key="link.news_list" var="news_list"/>
<fmt:message key="title.news_title" var="news_title"/>
<fmt:message key="title.summary" var="news_summary"/>
<fmt:message key="title.added_at" var="added_at"/>
<fmt:message key="title.content" var="news_content"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="button.cancel" var="cancel"/>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News Management: News Edit</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/admin/news-edit.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <a class="header-logo__title" href="${path}/controller?command=go_to_news_list_page">News Management</a>
    <section class="locale-links">
<%--        <a class="link" href="">English</a>--%>
<%--        <a class="link" href="">Russian</a>--%>
    </section>
</header>
<main class="main">
    <aside class="side-menu">
        <div class="side-menu-news">
            <h2 class="side-menu-news__title">News</h2>
            <ul class="side-menu-actions-list">
                <li class="side-menu-actions-list__item">
                    <a class="link" href="${path}/controller?command=go_to_news_list_page">${news_list}</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link link_active" href="${path}/controller?command=go_to_add_news_page">${add_news}</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="${path}/controller?command=go_to_news_list_page">News</a>
            <p class="admin-body-nav__item">>></p>
            <a class="admin-body-nav__item" href="${path}/controller?command=go_to_add_news_page">${add_news}</a>
        </section>
        <form class="admin-body-content" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="add_news">
            <div class="admin-news-view">
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsTitle">
                        ${news_title}
                    </label>
                    <input id="newsTitle" class="admin-news-view__input"
                           type="text" maxlength="100" required name="news_title"
                           value="${news_data_ses['news_title_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsDate">
                        ${added_at}
                    </label>
                    <input id="newsDate" class="admin-news-view__input"
                           type="date" maxlength="10" required name="news_added_at"
                           value="${news_data_ses['news_added_at_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsBrief">
                        ${news_summary}
                    </label>
                    <input type="text" id="newsBrief" class="admin-news-view__textarea"
                           maxlength="500" required name="news_summary" value="${news_data_ses['news_summary_ses']}">
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsContent">
                        ${news_content}
                    </label>
                    <input type="text" id="newsContent" maxlength="2048" required
                           class="admin-news-view__textarea" name="news_content"
                           value="${news_data_ses['news_content_ses']}">
                </div>
                <input type="hidden" name="news_author" value="${news_data_ses['news_author_ses'] = 'admin@gmail.com'}">
            </div>
            <section class="admin-body-content__actions">
                <input type="submit" class="admin-button" value="${save}">
                <a href="${path}/controller?command=go_to_news_list_page">
                    <button type="button" class="admin-button">${cancel}</button>
                </a>
            </section>
        </form>
    </section>
</main>
<script src="${path}/pages/static/js/admin/news-edit.js"></script>
</body>
</html>