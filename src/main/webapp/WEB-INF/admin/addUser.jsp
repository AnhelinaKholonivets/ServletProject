<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>
        <fmt:message key="user.add"/>
    </title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/header.jsp"/>
<div class="page-container">
    <h1 class="mx-auto">
        <fmt:message key="user.add"/>
    </h1>

    <form method="post" action="${pageContext.request.contextPath}/app/admin/users/addUser">
        <div class="mb-3">
            <label for="firstName" class="form-label"><fmt:message key="user.firstName"/></label>
            <input type="text" class="form-control" id="firstName" name="firstName"/>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label"><fmt:message key="user.lastName"/></label>
            <input type="text" class="form-control" id="lastName" name="lastName"/>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label"><fmt:message key="email"/></label>
            <input type="email" class="form-control" id="email" name="email"/>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><fmt:message key="password"/></label>
            <input type="password" class="form-control" id="password" name="password"/>
        </div>
        <div class="mb-3">
            <label  for="balance" class="form-label"><fmt:message key="user.balance"/></label>
            <input type="number" class="form-control" id="balance" name="balance"/>
        </div>
        <input class="w-100 btn btn-lg btn-primary" type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
