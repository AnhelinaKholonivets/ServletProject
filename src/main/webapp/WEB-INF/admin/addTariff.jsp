<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <title>
        <fmt:message key="tariff.add"/>
    </title>
</head>
<body>
<jsp:include page="/WEB-INF/admin/header.jsp"/>
<div class="page-container">
    <h1 class="mx-auto">
        <fmt:message key="tariff.add"/>
    </h1>

    <form method="post" name="tariff" action="/app/admin/tariffs/addTariff">
        <div>
            <label for="product" class="form-label">
                <fmt:message key="product"/>
            </label>
            <select class="form-control custom-select custom-select-lg mb-3" id="product" name="product">
                <option selected><fmt:message key="choose"/></option>
                <c:forEach var="product" items="${products}">
                    <option value="${product.id}">
                    <fmt:message key="${product.name}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="tariff" class="form-label">
                <fmt:message key="tariff.name"/>
            </label>
            <input type="text" class="form-control" id="tariff" name="tariff"/>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">
                <fmt:message key="price"/>
            </label>
            <input type="number" class="form-control" id="price" name="price"/>
        </div>
        <input class="w-100 btn btn-lg btn-primary" type="submit"/>
    </form>

</div>

</body>
</html>
