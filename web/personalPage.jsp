<%-- 
    Document   : personalPage.jsp
    Created on : Jan 29, 2022, 8:15:27 PM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page import="dacnt.order.OrderDAO"%>
<%@page import="dacnt.order.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <%@include file="header_loginedUser.jsp" %>
        <c:set var="USER" value="${sessionScope.USER}" scope="session"/>
        <c:if test="${empty USER}">
            <%
                request.setAttribute("ERROR",
                        "You must login to view personal page");
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            %>
        </c:if>

        <c:if test="${not empty USER}">
            <section>
                <%--<h3>Welcome <%= name%> come back </h3>--%>
                <ct:welcome name="${USER.fullname}"/>
                <h3><a href="logout">Logout</a></h3>
                <h3> <a href="viewCart.jsp">View Cart</a> </h3>
            </section>

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
                                <td>Order Date</td>
                                <td>Ship Date</td>
                                <td>Order's status</td>
                                <td>Action</td>
                            </tr>
                            <tr>
                                <td>${order.orderID}</td>
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
        </c:if>
        <%@include file="footer.jsp" %>
    </body>
</html>
