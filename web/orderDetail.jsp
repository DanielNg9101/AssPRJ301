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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="styles/mycss.css"/>


    </head>
    <body>

        <header>
            <%@include file="header.jsp" %>
        </header>
        <%
            if (currentUser == null) {
        %> 
        <p style="color: red;"> you must <a href="login.jsp">login</a> to view personal page </p>  
        <%
        } else {
            String name = currentUser.getFullname();
        %>
        <section>
            <%--<h3>Welcome <%= name%> come back </h3>--%>
            <ct:welcome name="<%= name %>"/>
            <h3><a href="DispatchController?action=logout">Logout</a></h3>
        </section>
        <section>
            <!--load all orders of user at here-->
            <%
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid);
                    OrderDAO dao = OrderDAO.getInstance();
                    dao.getOrderDetail(orderID);
                    ArrayList<OrderDetailDTO> list = dao.getOrderDetailList();
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetailDTO dto : list) {
            %>
            <table class="order">
                <tr>
                    <td>Order ID</td>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Image</td>
                    <td>Quantity</td>
                </tr>
                <tr>
                    <td><%= dto.getOrderDetailID()%></td>
                    <td><%= dto.getPlantID()%></td>
                    <td><%= dto.getPlantName()%></td>
                    <td> <img src="<%= dto.getImgPath()%>" class="plantimg" />
                        <br/>
                        <%= dto.getPrice()%>
                    </td>
                    <td> <%= dto.getQuantity()%> </td>
                    <% money = money + dto.getPrice() * dto.getQuantity(); %>
                </tr>
            </table>

            <%
                } // end for
            %> 
            <h3> Total money: <%= money%> </h3>
            <%
            } else {
            %> 
            <p>You don't have any order</p>
            <%
                    }
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
