<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="news_for_edit" value="${detailed_news_ses}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<fmt:message key="language.en" var="en"/>
<fmt:message key="language.ru" var="ru"/>
<fmt:message key="link.logout" var="logout"/>
<fmt:message key="title.news" var="news"/>
<fmt:message key="link.add_news" var="add_news"/>
<fmt:message key="link.news_edit" var="news_edit"/>
<fmt:message key="link.news_list" var="news_list"/>
<fmt:message key="title.news_title" var="news_title"/>
<fmt:message key="title.summary" var="news_summary"/>
<fmt:message key="title.added_at" var="added_at"/>
<fmt:message key="title.content" var="news_content"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="button.exit" var="exit"/>


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
    <a class="header-logo__title" href="news-list.jsp">News Management</a>
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
                    <a class="link link_active" href="${path}/controller?command=go_to_add_news_page">${add_news}</a>
                </li>
            </ul>
        </div>
    </aside>
    <section class="admin-body">
        <section class="admin-body-nav">
            <a class="admin-body-nav__item" href="${path}/controller?command=go_to_news_list_page">${news}</a>
            <p class="admin-body-nav__item">>></p>
            <a class="admin-body-nav__item"
               href="${path}/controller?command=go_to_edit_news_page&news_id=${news_for_edit.id}">${news_edit}</a>
        </section>
        <form class="admin-body-content" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="edit_news">
            <input type="hidden" name="news_id" value="${news_for_edit.id}">
            <input type="hidden" name="news_author" value="${news_for_edit.author}">
            <div class="admin-news-view">
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsTitle">
                        ${news_title}
                    </label>
                    <input id="newsTitle" class="admin-news-view__input"
                           type="text" maxlength="100" name="news_title"
                           value="${news_data_ses['news_title_ses'] = news_for_edit.title}" required>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsDate">
                        ${added_at}
                    </label>
                    <input id="newsDate" class="admin-news-view__input"
                           type="date" maxlength="10" name="news_added_at"
                           value="${news_data_ses['news_added_at_ses'] = news_for_edit.addedAt}" required>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsBrief">
                        ${news_summary}
                    </label>
                    <textarea id="newsBrief" class="admin-news-view__textarea"
                              maxlength="500" name="news_summary"
                              required>${news_data_ses['news_summary_ses'] = news_for_edit.summary}</textarea>
                </div>
                <div class="admin-news-view__fieldset">
                    <label class="admin-news-view__label" for="newsContent">
                        ${news_content}
                    </label>
                    <textarea id="newsContent" maxlength="2048" required
                              class="admin-news-view__textarea"
                              name="news_content">${news_data_ses['news_content_ses'] = news_for_edit.content}</textarea>
                </div>
            </div>
            <section class="admin-body-content__actions">
                <input type="submit" class="admin-button" value="${save}">
                <a href="${path}/controller?command=go_to_news_list_page">
                    <button type="button" class="admin-button">${exit}</button>
                </a>
            </section>

        </form>

    </section>
</main>
<script src="${path}/pages/static/js/admin/news-edit.js"></script>
</body>
</html>
