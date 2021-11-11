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
        <fmt:message key="order.all"/>
    </title>
</head>
<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>

<div class="page-container">

    <h1><fmt:message key="order.all"/></h1>


    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="product"/></th>
            <th scope="col"><fmt:message key="tariff"/></th>
            <th scope="col"><fmt:message key="price"/></th>
            <th scope="col"><fmt:message key="currency"/></th>
            <th scope="col"><fmt:message key="datetime"/></th>
        </tr>
        </thead>
        <tbody>

        <c:set var="count" value="0" scope="page"/>
        <c:forEach var="order" items="${orders}">
            <tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
                <td><c:out value="${count}"/></td>
                <td>${order.tariff.product.name}</td>
                <td>${order.tariff.name}</td>
                <td>${order.tariff.price}</td>
                <td>UAH</td>
                <td>${order.dateTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
        </ul>
    </nav>

</div>
</body>
</html>
