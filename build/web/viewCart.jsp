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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

        <script>
            function reconfirm() {
                return confirm("Do you want to delete?");
            }
        </script>

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                if (currentUser != null) {
                    String name = currentUser.getFullname();

            %> 
            <%--<h3>Welcome <%= name%> come back </h3>--%>
            <ct:welcome name="<%= name %>"/>
            <h3><a href="DispatchController?action=logout">Logout</a></h3>
            <h3><a href="DispatchController?action=viewOrders&category=">Personal Page</a></h3>
            <%
                }
                String warning = (String) request.getAttribute("WARNING");
                System.out.println(warning);
            %> 
            <font style="color: red;"> <%= warning == null ? "" : warning%> </font>


            <table class="shopping">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        HashMap<PlantDTO, Integer> cart = (HashMap<PlantDTO, Integer>) session.getAttribute("CART");
                        if (cart != null) {
                            Set<PlantDTO> plants = cart.keySet();
                            for (PlantDTO plant : plants) {
                                int quantity = cart.get(plant);

                    %>
                <form action="DispatchController" method="POST">
                    <tr>
                        <td> 
                            <input type="hidden" name="plantID" value="<%= plant.getId()%>" />
                            <a href="DispatchController?action=viewPlant&plantID=<%= plant.getId()%>"><%= plant.getId()%></a>
                        </td>
                        <td> <img src="<%= plant.getImgPath()%>" class ="plantimg" /> </td>
                        <td>
                            <input type="number" value="<%= quantity%>" name="quantity" />
                        </td>
                        <td> <%= plant.getPrice()%></td>
                        <td>
                            <input type="submit" value="updateCart" name="action" />
                        </td>
                        <td>
                            <input type="submit" value="deleteCart" name="action" onclick="return reconfirm()" />
                        </td>
                    </tr>
                </form>
                <%
                    }
                    Integer total = (Integer) session.getAttribute("TOTAL");
                %>

                <tr><td colspan="2">Total Money: <%= total == null ? 0 : total%> </td></tr>
                <tr><td colspan="2">Order Date: <%= (new Date(System.currentTimeMillis()).toString())%> </td></tr>
                <tr><td colspan="2">Ship Date: N/A </td></tr>
                <%
                } else {
                %>
                <tr><td colspan="2" id="empty_cart">Your cart is empty</td></tr>
                <% }%>

                </tbody>
            </table>
        </section>


        <section>
            <form action="DispatchController" method="POST">
                <input type="submit" value="saveOrder" name="action" />
            </form>
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
