<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="sortType" value="none"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/news.css">
    <link rel="stylesheet" href="${path}/pages/static/css/news-article.css">
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
                <a class="header-logo__title" href="${path}/controller?command=go_to_news_page">News</a>
            </div>
            <div class="header-auth">
                <c:choose>
                    <c:when test="${current_role eq 'UNKNOWN'}">
                        <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
                        <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
                    </c:when>
                    <c:otherwise>
                        <a class="header-auth__item" href="${path}/controller?command=logout">Log out</a>
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
                    <form method="post" action="${path}/controller">
                        <input type="hidden" name="command" value="go_to_news_page">
                        <select id="sortSelect" class="sort__select" name="sort_type">
                            <option value="none" disabled selected></option>
                            <option value="asc">Ascending ↑</option>
                            <option value="desc">Descending ↓</option>
                        </select>
                        <button type="submit">sort</button>
                    </form>
                </div>
            </section>

            <section class="articles-container">
                <c:forEach var="news" items="${all_news_ses}">
                    <article class="news-article">
                        <a class="news-article__title"
                           href="${path}/controller?command=go_to_news_details_page&news_id=${news.id}">${news.title}
                        </a>
                        <p class="news-article__text">${news.content}
                        </p>
                        <div class="news-article-date">
                            <img class="news-article-date__img"
                                 src="${path}/pages/static/img/calendar.svg" alt="Date">
                            <p class="news-article-date__text">${news.addedAt}
                            </p>
                        </div>
                    </article>
                </c:forEach>

            </section>
        </main>
    </c:otherwise>
</c:choose>

</body>
</html>
