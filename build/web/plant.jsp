<%-- 
    Document   : plant
    Created on : Feb 18, 2022, 5:13:59 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.plant.PlantDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

        <script src="scripts/addToCart.js"></script>


    </head>
    <body>
        <c:choose>
            <c:when test="${not empty sessionScope.USER and sessionScope.USER.role eq 1}">
                <c:import url="header_loginedAdmin.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="header.jsp"/>
            </c:otherwise>
        </c:choose>

        <jsp:useBean id="PLANT" 
                     class="dacnt.plant.PlantDTO" 
                     scope="request" 
                     type="dacnt.plant.PlantDTO"/>
        <c:set var="status" value="${fn:split('out of stock, available', ',')}" />

        <!--Using EL-->
        <section>

            <table class="product">
                <tr>
                    <td> <img src="${PLANT.imgPath}" class ="plantimg" /> </td>
                    <td> Product ID: 
                        <a href="viewPlant?plantID=${PLANT.id}">
                            ${PLANT.id}
                        </a> 
                    </td>
                    <td> Product Name: ${PLANT.name} </td>
                    <td> Price: ${PLANT.price} </td>
                    <td> Status: ${ status[PLANT.status]} </td>
                    <td> Category: ${PLANT.catename} </td>

                    <td> 
                        <c:choose>
                            <c:when test="${not empty sessionScope.USER and sessionScope.USER.role eq 1}">
                                <a href="renderEditPlant?plantID=${PLANT.id}"
                                   onclick="return editPlant();">
                                    edit
                                </a> 
                            </c:when>
                            <c:otherwise>
                                <a href="addToCart?plantID=${PLANT.id}"
                                   class="addToCartLink">
                                    add to cart
                                </a> 
                            </c:otherwise>
                        </c:choose>

                </tr>
            </table>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
