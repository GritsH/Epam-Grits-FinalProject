<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/13/2022
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>

<html>
<head>
    <script>
        function preventBack() {
            window.history.forward();
        }

        setTimeout("preventBack()", 0);
        window.onunload = function () {
            null
        };
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/auth.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <div class="header-logo">
        <img class="header-logo__icon" src="${path}/pages/static/img/news.svg"
             alt="News icon">
        <a class="header-logo__title" href="${path}/controller?command=go_to_news_page">News</a>
    </div>
    <div class="header-auth">
        <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
        <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
    </div>
</header>
<main class="main">
    <section class="card">
        <form class="card-form" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="login">
            <div class="card-form__item">
                <label class="card__label" for="emailInput">Email:</label>
                <input id="emailInput" class="card__input" type="email" name="email"
                       value="${user_data_ses['email_ses']}">
                <p id="emailError" class="input-error">Email is invalid</p>
            </div>
            <div class="card-form__item">
                <label class="card__label" for="password1Input">Password:</label>
                <input id="password1Input" class="card__input" type="password" name="pass"
                       value="${data_user_ses['password_ses']}">
                <p id="password1Error" class="input-error">Password is too short</p>
            </div>
            <p id="authError" class="input-error">Authentication failed. Please try again.</p>
            <input class="card__submit" type="submit" value="Log in">
        </form>
        <div class="card-auth">
            <p class="card-auth__text">Don't have an account?</p>
            <a class="card-auth__link" href="${path}/controller?command=go_to_signup_page">Sign up</a>
        </div>
    </section>
</main>
<script type="module" src="${path}/pages/static/js/auth/login.js"></script>

</body>
</html>
