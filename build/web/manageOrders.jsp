<%-- 
    Document   : manageOrders
    Created on : Mar 21, 2022, 12:46:16 PM
    Author     : Daniel NG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>

        <h1>Orders</h1>
        <form action="manageOrders">
            <select name="userEmail" onchange="this.form.submit();">
                <option value="All User"
                        ${param.userEmail eq "All User" ? "selected" : ""}>
                    All Orders
                </option>
                <c:if test="${not empty requestScope.ACCOUNTS}">
                    <c:forEach var="account" items="${requestScope.ACCOUNTS}">
                        <c:if test="${account.role eq 0}">
                            <option value="${account.email}"
                                    ${param.userEmail eq account.email ? "selected" : ""}>
                                ${account.email}
                            </option>
                        </c:if>
                    </c:forEach>
                </c:if>
            </select>
            from 
            <input type="date" 
                   name="from" 
                   value="${requestScope.fromDate}" /> 
            to 
            <input type="date" 
                   name="to" 
                   value="${requestScope.toDate}" />
            <input type="hidden" value="searchOrdersByDate" name="action" />
            <button type="submit">search</button>
        </form>


        <section>
            <!--load all orders of user at here-->
            <c:set var="ORDERS" value="${requestScope.ORDERS}"/>
            <c:if test="${empty ORDERS}">
                <p>You don't have any order</p>
            </c:if>

            <c:if test="${not empty ORDERS}">
                <c:set var="status" value="${fn:split(' , processing, completed, canceled', ',')}" />

                <c:forEach var="order" items="${ORDERS}">
                    <table class="order">
                        <tr>
                            <td>Order ID</td>
                            <td>User ID</td>
                            <td>Order Date</td>
                            <td>Ship Date</td>
                            <td>Order's status</td>
                            <td>Action</td>
                        </tr>
                        <tr>
                            <td>${order.orderID}</td>
                            <td>${order.accID}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.shipDate}</td>
                            <td>
                                ${status[order.status]}<br/>
                                <c:choose>
                                    <c:when test="${order.status == 1}">
                                        <c:url var="cancelLink" value="changeOrderStatus">
                                            <c:param name="orderID" value="${order.orderID}" />
                                            <c:param name="category" value="${param.category}" />
                                            <c:param name="action" value="cancelOrder" />
                                            <c:param name="userEmail" value="${param.userEmail}" />
                                        </c:url>
                                        <a href="${cancelLink}" > 
                                            Cancel Order
                                        </a>
                                    </c:when>
                                    <c:when test="${order.status == 3}">
                                        <c:url var="againLink" value="changeOrderStatus">
                                            <c:param name="orderID" value="${order.orderID}" />
                                            <c:param name="category" value="${param.category}" />
                                            <c:param name="action" value="orderAgain" />
                                            <c:param name="userEmail" value="${param.userEmail}" />

                                        </c:url>
                                        <a href="${againLink}"  >
                                            Order Again
                                        </a>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td> 
                                <a href="viewOrderDetail?orderid=${order.orderID}">
                                    Detail
                                </a> 
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>
        </section>
        <c:import url="footer.jsp"/>
    </body>
</html>
