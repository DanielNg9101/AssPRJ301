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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>

        <%            AccountDTO currentUser = (AccountDTO) session.getAttribute("CURRENT_USER");
            if (currentUser == null) {
        %> 
        <p style="color: red;"> you must <a href="DispatchController?action=loginHandler">login</a> to view personal page </p>  
        <%
        } else {
            String name = currentUser.getFullname();
        %>


        <section>
            <%--<h3>Welcome <%= name%> come back </h3>--%>
            <ct:welcome name="<%= name %>"/>
            <h3><a href="DispatchController?action=logout">Logout</a></h3>
            <h3> <a href="DispatchController?action=viewCart">View Cart</a> </h3>
        </section>

        <section>
            <!--load all orders of user at here-->
            <%
                String email = (String) session.getAttribute("EMAIL");
                ArrayList<OrderDTO> list = (ArrayList<OrderDTO>) request.getAttribute("ORDERS");

                String[] status = {"", "processing", "completed", "canceled"};

                String category = request.getParameter("category");

                if (list != null && !list.isEmpty()) {
                    for (OrderDTO dto : list) {
                        int orderID = dto.getOrderID();
            %>
            <table class="order">
                <tr>
                    <td>Order ID</td>
                    <td>Order Date</td>
                    <td>Ship Date</td>
                    <td>Order's status</td>
                    <td>Action</td>
                </tr>
                <tr>
                    <td><%= orderID%></td>
                    <td><%= dto.getOrderDate()%></td>
                    <td><%= dto.getShipDate()%></td>
                    <td><%= status[dto.getStatus()]%>
                        <br/> <%
                            if (dto.getStatus() == 1) {
                        %> 
                        <a href="DispatchController?action=cancelOrder&orderID=<%= orderID%>&category=<%= category%>" > Cancel Order</a>
                        <% } else if (dto.getStatus() == 3) {
                        %>
                        <a href="DispatchController?action=orderAgain&orderID=<%= orderID%>&category=<%= category%>" >Order Again</a>
                        <%}%>
                    </td>
                    <td> <a href="orderDetail.jsp?orderid=<%= dto.getOrderID()%>">Detail</a> </td>
                </tr>
            </table>

            <%
                }
            } else {
            %>
            <p>You don't have any order</p>
            <%
                }
            %>
        </section>
        <%
            }
        %>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
