<%-- 
    Document   : OrderDetail
    Created on : Jan 30, 2022, 7:58:47 AM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dacnt.order.OrderDetailDTO"%>
<%@page import="dacnt.order.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="styles/mycss.css"/>


    </head>
    <body>

        <%@include file="header.jsp" %>

        <c:if test="${empty sessionScope.USER}">
            <%
                request.setAttribute("ERROR",
                        "You must login to view order detail");
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            %>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
            <section>
                <ct:welcome name="${sessionScope.USER.fullname}"/>
                <h3><a href="logout">Logout</a></h3>
            </section>
            <section>
                <!--load all orders of user at here-->
                <c:set var="DETAILS" value="${requestScope.DETAILS}" />
                <c:if test="${empty DETAILS}">
                    <p>You don't have any order</p>
                </c:if>
                <c:if test="${not empty DETAILS}">
                    <c:forEach var="order" items="${DETAILS}">
                        <table class="order">
                            <tr>
                                <td>Order ID</td>
                                <td>Plant ID</td>
                                <td>Plant Name</td>
                                <td>Image</td>
                                <td>Quantity</td>
                            </tr>
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.plantID}</td>
                                <td>${order.plantName}</td>
                                <td> <img src="${order.imgPath}" class="plantimg" />
                                    <br/>
                                    ${order.price}
                                </td>
                                <td> ${order.quantity} </td>
                                <c:set var="money" 
                                       value="${money + order.price * order.quantity}"/>
                            </tr>
                        </table>
                    </c:forEach>
                    <h3> Total money: ${money} </h3>
                </c:if>
            </section>
        </c:if>
        <%@include file="footer.jsp" %>
    </body>
</html>
