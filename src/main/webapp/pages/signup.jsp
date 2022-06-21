<%--
  Created by IntelliJ IDEA.
  User: grits
  Date: 6/14/2022
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.pagecontent"/>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="${path}/pages/static/css/base.css">
    <link rel="stylesheet" href="${path}/pages/static/css/auth.css">
    <link rel="stylesheet" href="${path}/pages/static/css/signup.css">
    <link href="https://fonts.cdnfonts.com/css/montserrat" rel="stylesheet">
</head>
<body>
<header class="header">
    <div class="header-logo">
        <img class="header-logo__icon" src="${path}/pages/static/img/news.svg"
             alt="News icon">
        <a class="header-logo__title" href="news.html">News</a>
    </div>
    <div class="header-auth">
        <a class="header-auth__item" href="${path}/controller?command=go_to_login_page">Log in</a>
        <a class="header-auth__item" href="${path}/controller?command=go_to_signup_page">Sign Up</a>
    </div>
</header>
<main class="main">
    <section class="card">
        <form class="card-form" action="${path}/controller" method="post">
            <input type="hidden" name="command" value="signup">
            <div class="card-form__item">
                <label class="card__label" for="emailInput">Email:</label>
                <input id="emailInput" class="card__input" type="email" name="email"
                       value="${user_data_ses['email_ses']}">
                <p id="emailError" class="input-error">Email is invalid</p>
            </div>
            <div class="card-form__item">
                <label class="card__label"
                       for="password1Input">Password:</label>
                <input id="password1Input" class="card__input" type="password" name="pass"
                       value="${user_data_ses['password_ses']}">
                <p id="password1Error" class="input-error">Password is too short</p>
            </div>
            <div class="card-form__item">
                <label class="card__label" for="password2Input">Confirm
                    password:</label>
                <input id="password2Input" class="card__input" type="password" name="repeat_password"
                       value="${user_data_ses['repeat_password_ses']}">
                <p id="password2Error" class="input-error">Passwords don't match</p>
            </div>
            <input class="card__submit" type="submit" value="Sign up" name="submitBtn">
        </form>
        <div class="card-auth">
            <p class="card-auth__text">Already have an account?</p>
            <a class="card-auth__link" href="${path}/controller?command=go_to_login_page">Log in</a>
        </div>
    </section>
</main>
<script type="module" src="${path}/pages/static/js/auth/signup.js"></script>
</body>
</html>
