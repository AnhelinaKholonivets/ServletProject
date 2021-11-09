<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
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
        <c:forEach var="order" items="${orders}">
        <tr>
                <td>1</td>
                <td>${order.tariff.product.name}</td>
                <td>${order.tariff.name}</td>
                <td>${order.tariff.price}</td>
                <td>UAH</td>
                <td>${order.dateTime}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--    <nav aria-label="Page navigation">--%>
    <%--        <ul class="pagination justify-content-center">--%>
    <%--            <li th:if="${tariffs.totalPages > 0}"--%>
    <%--                th:each="pageNumber : ${pageNumbers}"--%>
    <%--                th:class="${pageNumber==tariffs.number + 1} ? 'page-item active' : 'page-item'">--%>
    <%--                <a class="page-link" th:href="@{/tariffs(size=${tariffs.size}, page=${pageNumber},--%>
    <%--                    sortField=${#request.getParameter('sortField')},--%>
    <%--                    sortDir=${#request.getParameter('sortDir')})}"--%>
    <%--                   th:text="${pageNumber}"></a></li>--%>
    <%--        </ul>--%>
    <%--    </nav>--%>

</div>
</body>
</html>
