<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>
        <fmt:message key="tariff.all"/>
    </title>

    <script>
        function deleteRequest(id) {
            $.ajax({
                url: '/tariffs/delete/' + id,
                type: 'DELETE',
                success: function (data) {
                    console.log(data);
                    document.location.reload();
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/admin/header.jsp"/>

<div class="page-container">

    <div class="d-flex align-items-center justify-content-between">
        <div class="d-inline">
            <h1><fmt:message key="tariff.all"/></h1>
        </div>
        <div class="d-inline ">
            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/admin/tariffs/addTariff" role="button">
                <fmt:message key="tariff.add"/>
            </a>
        </div>
    </div>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="product"/></th>
            <th scope="col"><fmt:message key="tariff"/></th>
            <th scope="col"><fmt:message key="price"/>
            </th>
            <th scope="col"><fmt:message key="currency"/></th>
            <th scope="col"><fmt:message key="delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffs}">
            <tr>
                <td>1</td>
                <td>${tariff.product.name}</td>
                <td>${tariff.name}</td>
                <td>${tariff.price}</td>
                <td>UAH</td>
                <td><a class="btn-outline-danger text-decoration-none"
                       onclick="deleteRequest(this.id)">
                    <fmt:message key="delete"/>
                </a></td>

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
