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

        <script type="text/javascript">
            $(document).ready(function () {
                $('.addToCartLink').click(function (e) {
                    e.preventDefault();
                    // console.log(e.target.search)
                    const reconform = confirm("Add to cart?");
                    if (reconform) {
                        const plantID = e.target.search.split("=")[1]
                        console.log(plantID)
                        axios.get('addToCart?plantID=' + plantID)
                    }
                });
            });
        </script> 

    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${not empty requestScope.WARNING}">
            <font style="color: red;"> ${requestScope.WARNING} </font>
        </c:if>
        <section>
            <%
                session.setAttribute("lastUrl", "DispatchController?action=index");

            %> 

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
                                <a href="DispatchController?action=viewPlant&plantID=${plant.id}">
                                    ${plant.id}
                                </a> 
                            </td>
                            <td> Product Name: ${plant.name} </td>
                            <td> Price: ${plant.price} </td>
                            <td> Status: ${ status[plant.status]} </td>
                            <td> Category: ${plant.catename} </td>
                            <td> 
                                <a href="addToCart?plantID=${plant.id}"
                                   class="addToCartLink">
                                    add to cart
                                </a> 
                            </td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>

