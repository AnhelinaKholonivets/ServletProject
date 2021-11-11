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
        <fmt:message key="tariff.all"/>
    </title>
</head>
<body>

<jsp:include page="/WEB-INF/user/header.jsp"/>

<div class="page-container">

    <h1><fmt:message key="tariff.all"/></h1>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="product"/></th>
            <th scope="col"><a><fmt:message key="tariff"/></a></th>
            <th scope="col"><a><fmt:message key="price"/></a></th>
            <th scope="col"><fmt:message key="currency"/></th>
        </tr>
        </thead>
        <tbody>

        <c:set var="count" value="0" scope="page"/>
        <c:forEach var="tariff" items="${tariffs}">
            <tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
                <td><c:out value="${count}"/></td>
                <td>${tariff.product.name}</td>
                <td>${tariff.name}</td>
                <td>${tariff.price}</td>
                <td>UAH</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
        </ul>
    </nav>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
