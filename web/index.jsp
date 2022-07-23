<%-- 
    Document   : index
    Created on : Jan 29, 2022, 7:39:57 PM
    Author     : dacng
--%>

<%@page import="dacnt.plant.PlantDAO"%>
<%@page import="dacnt.plant.PlantDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

        <script src="scripts/addToCart.js"></script>

    </head>
    <body>

        <c:choose>
            <c:when test="${not empty sessionScope.USER and sessionScope.USER.role eq 1}">
                <c:import url="header_loginedAdmin.jsp"/>
                <h1>Plants</h1>

                <form action="search" method="POST" class="formsearch" >
                    <input type="text" name="txtSearch" 
                           value="${param.txtSearch}" />
                    <select name="searchby">
                        <option value="byname" 
                                ${param.searchby.equals("byname") 
                                  ? "selected" : "" }>
                            by name
                        </option>
                        <option value="bycate"
                                ${param.searchby.equals("bycate") 
                                  ? "selected" : "" }>
                            by category
                        </option>
                    </select>
                    <button type="submit">search</button>
                </form>
                <h2><a href="viewAddPlant">Add plant</a> </h2>
            </c:when>
            <c:otherwise>
                <c:import url="header.jsp"/>
            </c:otherwise>
        </c:choose>


        <c:if test="${not empty requestScope.WARNING}">
            <font style="color: red;"> ${requestScope.WARNING} </font>
        </c:if>
        <section>
            <c:set var="PLANTS" value="${requestScope.PLANTS}"/>
            <c:if test="${not empty PLANTS}">
                <c:set var="status" value="${fn:split('out of stock, available', ',')}" />
                <c:forEach var="plant" items="${PLANTS}">
                    <table class="product">
                        <tr>
                            <td> 
                                <img src="${plant.imgPath}" class="plantimg" /> 
                            </td>
                            <td> Product ID: 
                                <a href="viewPlant?plantID=${plant.id}">
                                    ${plant.id}
                                </a> 
                            </td>
                            <td> Product Name: ${plant.name} </td>
                            <td> Price: ${plant.price} </td>
                            <td> Status: ${ status[plant.status]} </td>
                            <td> Category: ${plant.catename} </td>
                            <td> 
                                <c:choose>
                                    <c:when test="${not empty sessionScope.USER and sessionScope.USER.role eq 1}">
                                        <a href="renderEditPlant?plantID=${plant.id}"
                                           onclick="return editPlant();">
                                            edit
                                        </a> 
                                    </c:when>
                                    <c:otherwise>
                                        <a href="addToCart?plantID=${plant.id}"
                                           class="addToCartLink">
                                            add to cart
                                        </a> 
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>

