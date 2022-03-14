<%-- 
    Document   : header_loginedUser.jsp
    Created on : Jan 29, 2022, 8:08:43 PM
    Author     : dacng
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ct" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <nav>
            <ul>
                <li> <a href="DispatchController"> <img src="images/logo.jpg" id="logo" class="plantimg" /> </a> </li>
                <li> <a href="DispatchController">Home</a> </li>
                <li> <a href="DispatchController?action=changeProfileHandler">change profile</a> </li>
                <li> <a href="DispatchController?action=viewOrders&category=">all orders</a> </li>
                <li> <a href="DispatchController?action=viewOrders&category=completed">completed orders</a> </li>
                <li> <a href="DispatchController?action=viewOrders&category=canceled">canceled orders</a> </li>
                <li> <a href="DispatchController?action=viewOrders&category=processing">processing orders</a> </li>
                <li> 
                    <%
                        Date fromDate = (Date) request.getAttribute("fromDate");
                        Date toDate = (Date) request.getAttribute("toDate");
                        if (fromDate == null) {
                            fromDate = new Date(System.currentTimeMillis());
                        }
                    %>
                    <form action="DispatchController" method="POST">
                        from 
                        <input type="date" 
                               name="from" 
                               value="<%= fromDate%>" /> 
                        to 
                        <input type="date" 
                               name="to" 
                               value="<%= toDate == null ? "" : toDate%>" />
                        <input type="submit" value="Search Order" name="action" />
                    </form>

                </li>

            </ul>
        </nav>
    </body>
</html>
