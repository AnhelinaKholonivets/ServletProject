<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title></title>
    <style>
        .page-container {
            margin-left: auto;
            margin-right: auto;
            width: 70%;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css'/>" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <%--                    <ul sec:authorize="isAnonymous()"--%>
            <%--                        class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">--%>
            <%--                        <li><a href="/home" th:text="#{header.home}" class="nav-link px-2 text-white"></a></li>--%>
            <%--                    </ul>--%>

            <%--                    <ul sec:authorize="isAuthenticated()"--%>
            <ul
                class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/app/home" class="nav-link px-2 text-white">
                    <fmt:message key="header.home"/>
                </a></li>
                <li><a href="${pageContext.request.contextPath}/app/tariffs" class="nav-link px-2 text-white">
                    <fmt:message key="header.tariffs"/>
                </a></li>
<%--                <li sec:authorize="hasRole('ROLE_ADMIN')">--%>
                <li>
                    <a href="${pageContext.request.contextPath}/app/users" class="nav-link px-2 text-white">
                        <fmt:message key="header.users"/>
                    </a></li>
                <li><a href="${pageContext.request.contextPath}/app/orders" class="nav-link px-2 text-white">
                    <fmt:message key="header.orders"/>
                </a></li>
            </ul>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white"
                       href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:out value="${pageContext.request.locale.language.toUpperCase()}"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdown01">
                        <li><a class="dropdown-item" href="?lang=en_EN">EN</a></li>
                        <li><a class="dropdown-item" href="?lang=ua_UA">UA</a></li>
                    </ul>
                </li>
            </ul>

<%--            <div class="text-end">--%>
<%--                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">--%>
<%--                    <li><a sec:authorize="hasRole('ROLE_ADMIN')" href="#"--%>
<%--                           class="nav-link px-2 text-white">[[${#httpServletRequest.remoteUser}]]</a>--%>
<%--                    <li><a sec:authorize="hasRole('ROLE_USER')" href="/user/profile"--%>
<%--                           class="nav-link px-2 text-white">[[${#httpServletRequest.remoteUser}]]</a></li>--%>
<%--                    <li sec:authorize="isAnonymous()">--%>
<%--                        <a th:text="#{header.login}" class="btn btn-outline-light" href="/login" role="button"></a></li>--%>
<%--                    <li sec:authorize="isAuthenticated()">--%>
<%--                        <a th:text="#{header.logout}" class="btn btn-outline-light" href="/logout" role="button"></a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>

                <div class="text-end">
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/user/profile"
                               class="nav-link px-2 text-white"><c:out value="${pageContext.request.remoteUser}"/></a></li>
<%--                        <li sec:authorize="isAnonymous()">--%>
<%--                            <a th:text="#{header.login}" class="btn btn-outline-light" href="/login" role="button"></a></li>--%>
<%--                        <li sec:authorize="isAuthenticated()">--%>
<%--                            <a th:text="#{header.logout}" class="btn btn-outline-light" href="/logout" role="button"></a>--%>
<%--                        </li>--%>
                    </ul>
                </div>

        </div>
    </div>
</header>
<br>
</body>
</html>

