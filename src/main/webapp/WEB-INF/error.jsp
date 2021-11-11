<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>
        <fmt:message key="header.home"/>
    </title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="page-container">
    <div class="text-center">
        <img src="/images/cat.gif" class="rounded" alt="cat" width="500">
    </div>

</div>
</body>
</html>
