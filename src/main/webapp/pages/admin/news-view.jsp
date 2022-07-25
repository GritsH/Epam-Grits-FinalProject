<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="news_detailed" value="${detailed_news_ses}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="link.logout" var="logout"/>
<fmt:message key="language.en" var="en"/>
<fmt:message key="language.ru" var="ru"/>
<fmt:message key="title.news" var="news"/>
<fmt:message key="link.add_news" var="add_news"/>
<fmt:message key="link.news_list" var="news_list"/>
<fmt:message key="button.edit" var="edit_button"/>
<fmt:message key="button.delete" var="delete_button"/>
<fmt:message key="title.news_title" var="news_title"/>
<fmt:message key="title.added_at" var="news_date"/>
<fmt:message key="title.summary" var="news_summary"/>
<fmt:message key="title.content" var="news_content"/>
<fmt:message key="link.view_news" var="view"/>

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
        <a class="link" href="${path}/controller?command=logout">${logout}</a>
        <a class="link" href="${path}/controller?command=change_language&language=EN">${en}</a>
        <a class="link" href="${path}/controller?command=change_language&language=RU">${ru}</a>
    </section>
</header>
<main class="main">
    <aside class="side-menu">
        <div class="side-menu-news">
            <h2 class="side-menu-news__title">${news}</h2>
            <ul class="side-menu-actions-list">
                <li class="side-menu-actions-list__item">
                    <a class="link" href="${path}/controller?command=go_to_news_list_page">${news_list}</a>
                </li>
                <li class="side-menu-actions-list__item">
                    <a class="link" href="${path}/controller?command=go_to_add_news_page">${add_news}</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="${path}/controller?command=go_to_news_list_page">${news}</a>
            <p class="admin-body-nav__item">>></p>
            <p class="admin-body-nav__item">${view}</p>
        </section>
        <form class="admin-body-content" method="post" action="${path}/controller">
            <input type="hidden" name="command" value="delete_news">
            <input type="hidden" name="news_id_to_delete" value="${news_detailed.id}">
            <div class="admin-news-view">
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsTitle">
                        ${news_title}
                    </label>
                    <p id="newsTitle" class="admin-news-view__text">
                        ${news_detailed.title}
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsDate">
                        ${news_date}
                    </label>
                    <p id="newsDate" class="admin-news-view__text">
                        ${news_detailed.addedAt}
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsBrief">
                        ${news_summary}
                    </label>
                    <p id="newsBrief" class="admin-news-view__text">
                        ${news_detailed.summary}
                    </p>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsContent">
                        ${news_content}
                    </label>
                    <p id="newsContent" class="admin-news-view__text">
                        ${news_detailed.content}
                    </p>
                </div>
            </div>
            <section class="admin-body-content__actions">
                <a href="${path}/controller?command=go_to_edit_news_page&news_id_to_edit=${news_detailed.id}">
                    <button type="button" class="admin-button">${edit_button}</button>
                </a>
                <input id="deleteButton" type="submit" class="admin-button" value="${delete_button}">
            </section>

        </form>
    </section>
</main>
<script src="${path}/pages/static/js/admin/news-view.js"></script>
</body>
</html>
