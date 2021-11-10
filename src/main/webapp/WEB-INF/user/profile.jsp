<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>
        <fmt:message key="header.profile"/>
    </title>
    <script>
        function updateBalance() {
            let balance = parseFloat(document.getElementById("currentBalance").innerHTML);
            let addToBalance = parseFloat(document.getElementById("balanceToAdd").value);

            $.ajax({
                url: '/app/user/profile',
                type: 'put',
                data: "addToBalance: " + addToBalance,
                dataType: "json",
                success: function (data) {
                    document.getElementById("currentBalance").innerHTML
                        = (balance + addToBalance).toString();
                    console.log(data);
                }
            })
        }
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>

<div class="page-container">

    <h4><fmt:message key="header.profile"/>: ${user.firstName} ${user.lastName}</h4>
    <h4><fmt:message key="email"/>: ${user.email}</h4>    <br>

    <h4 style="display: inline"><fmt:message key="balance.amount"/>: </h4>
    <h4 style="color: green; display: inline" id="currentBalance"> ${user.balance}</h4>


    <div class="input-group mb-3">
        <div class="col-xs-2">
            <input type="number" class="form-control" placeholder="0.00" aria-label="balanceToAdd" id="balanceToAdd"
                   name="balanceToAdd">
        </div>
        <div class="input-group-append">
            <button class="btn btn-outline-primary" type="button" onclick="updateBalance()">Add</button>
        </div>
    </div>
    <br>
    <a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/app/user/orders/addOrder" role="button">
        <fmt:message key="order.new"/>
    </a>

</div>
</body>
</html>
