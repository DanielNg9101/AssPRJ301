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
        <title>index</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%//PlantDTO PLANT = (PlantDTO) request.getAttribute("PLANT"); %>

            <jsp:useBean id="PLANT" 
                         class="dacnt.plant.PlantDTO" 
                         scope="request" 
                         type="dacnt.plant.PlantDTO"/>

            <%
//                PlantDTO PLANT = (PlantDTO) request.getAttribute("PLANT");
                String[] tmp = {"out of stock", "available"};
            %> 
            <table class="product">
                <tr>
                    <td> 
                        <img src="<jsp:getProperty name="PLANT" property="imgPath" />" 
                             class ="plantimg" /> 
                    </td>
                    <td> Product ID: 
                        <a href="DispatchController?action=viewPlant&plantID=<jsp:getProperty name="PLANT" property="id" />">
                            <jsp:getProperty name="PLANT" property="id" />
                        </a> 
                    </td>
                    <td> Product Name: 
                        <jsp:getProperty name="PLANT" property="name" /> 
                    </td>
                    <td> Price: 
                        <jsp:getProperty name="PLANT" property="price" />
                    </td>
                    <td> Status: 
                        <jsp:getProperty name="PLANT" property="status" />
                    </td>
                    <td> Category: 
                        <jsp:getProperty name="PLANT" property="catename" /> 
                    </td>
                    <%
                        if (currentUser == null || currentUser.getRole() == 0) {
                    %>
                    <td> <a href="DispatchController?action=addToCart&plantID=<%= PLANT.getId()%>">add to cart</a> </td>
                    <% } else {%>

                    <td> <a href="AdminController?action=editPlant&plantID=<%= PLANT.getId()%>">Edit</a> </td>

                    <%}%>
                </tr>
            </table>

            <%
                session.setAttribute("lastUrl",
                        "DispatchController?action=viewPlant&plantID=" + PLANT.getId());
            %>

        </section>


        <!--Using EL-->
        <section>

            <table class="product">
                <tr>
                    <td> <img src="${PLANT.imgPath}" class ="plantimg" /> </td>
                    <td> Product ID: 
                        <a href="DispatchController?action=viewPlant&plantID=${PLANT.id}">
                            ${PLANT.id}
                        </a> 
                    </td>
                    <td> Product Name: ${PLANT.name} </td>
                    <td> Price: ${PLANT.price} </td>
                    <td> Status: ${PLANT.status} </td>
                    <td> Category: ${PLANT.catename} </td>
                    <%
                        if (currentUser == null || currentUser.getRole() == 0) {
                    %>
                    <td> 
                        <a href="DispatchController?action=addToCart&plantID=<%= PLANT.getId()%>">
                            add to cart
                        </a> 
                    </td>
                    <% } else {%>

                    <td> 
                        <a href="AdminController?action=editPlant&plantID=<%= PLANT.getId()%>">
                            Edit
                        </a> 
                    </td>

                    <%}%>
                </tr>
            </table>

            <%
                session.setAttribute("lastUrl",
                        "DispatchController?action=viewPlant&plantID=" + PLANT.getId());
            %>

        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
