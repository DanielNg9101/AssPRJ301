<%-- 
    Document   : viewCart
    Created on : Feb 16, 2022, 7:22:25 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.plant.PlantDTO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

        <script>
            function reconfirm() {
                return confirm("Do you want to delete?");
            }
        </script>

    </head>
    <body>
        <%@include file="header.jsp" %>

        <c:set var="USER" value="${sessionScope.USER}" scope="session"/>

        <c:if test="${not empty USER}">
            <section>
                <%--<h3>Welcome <%= name%> come back </h3>--%>
                <ct:welcome name="${USER.fullname}"/>
                <h3><a href="logout">Logout</a></h3>
                <h3> 
                    <a href="viewOrders">
                        Personal Page
                    </a> 
                </h3>
            </section>
        </c:if>

        <section>

            <c:if test="${not empty requestScope.WARNING}">
                <font style="color: red;"> ${requestScope.WARNING} </font>
            </c:if>

            <table class="shopping">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="cart" value="${sessionScope.CART}" />
                    <c:if test="${not empty cart}">
                        <c:set var="plants" value="${cart.keySet()}"/>
                        <c:forEach var="plant" items="${plants}">
                            <tr>
                                <td> 
                                    <a href="DispatchController?action=viewPlant&plantID=${plant.id}">${plant.id}</a>
                                </td>
                                <td> <img src="${plant.imgPath}" class ="plantimg" /> </td>
                                <td>${plant.price}</td>

                                <td>
                                    <form action="updateCart" method="POST">
                                        <table border="0">
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="plantID" 
                                                           value="${plant.id}" />
                                                    <input type="number" value="${cart.get(plant)}" 
                                                           name="quantity" />
                                                </td>
                                                <td>
                                                    <a href="" 
                                                       onclick="this.closest('form').submit();
                                                               return false;">Update</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>

                                <td>
                                    <c:url var="deleteLink" value="deleteCart">
                                        <c:param name="plantID" value="${plant.id}"/>
                                    </c:url>
                                    <a href="${deleteLink}" 
                                       onclick="return reconfirm()" >
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2">
                                Total Money: ${sessionScope.TOTAL}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Order Date: <%= (new Date(System.currentTimeMillis()).toString())%>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                Ship Date: N/A
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h2> <a href="saveShoppingCart">Save</a> </h2>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${empty cart}">
                        <tr>
                            <td colspan="2" id="empty_cart">Your cart is empty</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </section>

        <%@include file="footer.jsp" %>
    </body>
</html>
