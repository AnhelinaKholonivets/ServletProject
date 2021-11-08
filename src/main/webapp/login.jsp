<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>
        <fmt:message key="header.login"/>
    </title>
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>

<div class="page-container">
    <main class="form-signin">
        <%--        <div th:if="${param.error}">--%>
        <%--            <p>--%>
        <%--                <fmt:message key="error.login"/>--%>
        <%--            </p>--%>
        <%--        </div>--%>
        <%--        <div th:if="${param.logout}">--%>
        <%--            <p>--%>
        <%--                <fmt:message key="user.out"/>--%>
        <%--            </p>--%>
        <%--        </div>--%>

        <form action="${pageContext.request.contextPath}/app/login" method="POST">
            <div class="mb-3">
                <label for="username" class="form-label">
                    <fmt:message key="email"/>
                </label>
                <input type="text" class="form-control" id="username" name="username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">
                    <fmt:message key="password"/>
                </label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <input class="w-100 btn btn-lg btn-primary" type="submit" value='<fmt:message key="header.login"/>'/>
        </form>
    </main>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

