<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="order.new"/></title>
    <style>
    </style>
    <script>
        function addOrders() {
            let tariffsIds = [];
            $("input:checkbox[name=id]:checked").each(function () {
                tariffsIds.push($(this).val());
            });

            $.ajax({
                url: '/app/user/orders/addOrder',
                type: 'post',
                data: JSON.stringify(tariffsIds),
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    alert("Done");
                },
                error: function (data) {
                    let balanceError = document.getElementById('balanceError');
                    balanceError.style.height = '100px';
                  //  balanceError.innerHTML = data.responseJSON.message;
                }
            });
        }
    </script>

</head>
<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>
<div class="page-container">
    <div class="d-flex align-items-center justify-content-between">
        <div class="d-inline">
            <h1><fmt:message key="tariff.all"/></h1>
        </div>
        <div class="d-inline">
            <!--            <h2>Your balance: 0.00 UAH</h2>-->
        </div>
    </div>

    <div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="product"/></th>
                <th scope="col"><fmt:message key="tariff"/></th>
                <th scope="col"><fmt:message key="price"/></th>
                <th scope="col"><fmt:message key="currency"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tariff" items="${tariffs}">
                <tr>
                    <td>
                        <span class="form-check">
                        <label for="id"></label>
                        <input class="form-check-input" type="checkbox" value="${tariff.id}" id="id" name="id">
                        </span>
                    </td>
                    <td>1</td>
                    <td>${tariff.product.name}</td>
                    <td>${tariff.name}</td>
                    <td>${tariff.price}</td>
                    <td>UAH</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
        </ul>
    </nav>

    <br>
    <div class=text-center>
        <button class="btn btn-outline-primary" type="button" onclick="addOrders()"><fmt:message
                key="order.new"/></button>
    </div>

    <div id="balanceError" style="background-color: red; height: 0;">
    </div>

</div>
</body>
</html>
