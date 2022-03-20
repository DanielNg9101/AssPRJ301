<%-- 
    Document   : plant
    Created on : Feb 18, 2022, 5:13:59 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.plant.PlantDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant</title>
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
        <jsp:useBean id="PLANT" 
                     class="dacnt.plant.PlantDTO" 
                     scope="request" 
                     type="dacnt.plant.PlantDTO"/>

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
                    <td> Status: ${PLANT.status} </td>
                    <td> Category: ${PLANT.catename} </td>

                    <td> 
                        <a href="addToCart?plantID=${PLANT.id}"
                           class="addToCartLink">
                            add to cart
                        </a> 
                    </td>
                    <%--
                                        <td> 
                                            <a href="AdminController?action=editPlant&plantID=<%= PLANT.getId()%>">
                                                Edit
                                            </a> 
                                        </td>
                    --%>
                </tr>
            </table>

            <%
                session.setAttribute("lastUrl",
                        "DispatchController?action=viewPlant&plantID=" + PLANT.getId());
            %>

        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
