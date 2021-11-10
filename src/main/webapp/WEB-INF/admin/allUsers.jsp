<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>
        <fmt:message key="tariff.all"/>
    </title>
    <script>
        function blockRequest(id) {
            $.ajax({
                url: '/app/admin/users/' + id,
                type: 'PUT',
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
            <h1><fmt:message key="user.all"/></h1>
        </div>
        <div class="d-inline ">
            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/app/admin/users/addUser" role="button">
                <fmt:message key="user.add"/>
            </a>
        </div>
    </div>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="user.firstName"/></th>
            <th scope="col"><fmt:message key="user.lastName"/></th>
            <th scope="col"><a><fmt:message key="email"/></a></th>
            <th scope="col"><a><fmt:message key="user.balance"/></a></th>
            <th scope="col"><fmt:message key="currency"/></th>
            <th scope="col"><fmt:message key="user.block"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>1</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.balance}</td>
                <td>UAH</td>
                <td>${user.blocked}</td>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
