<%-- 
    Document   : header_loginedUser.jsp
    Created on : Jan 29, 2022, 8:08:43 PM
    Author     : dacng
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ct" %>
<header>
    <nav>
        <ul>
            <li> <a href="search"> <img src="images/logo.jpg" id="logo" class="plantimg" /> </a> </li>
            <li> <a href="search">Home</a> </li>
            <li> <a href="changeProfile">change profile</a> </li>
            <li> <a href="viewOrders">all orders</a> </li>
            <li> <a href="viewOrders?category=completed">completed orders</a> </li>
            <li> <a href="viewOrders?category=canceled">canceled orders</a> </li>
            <li> <a href="viewOrders?category=processing">processing orders</a> </li>
            <li> 
                <form action="viewOrders" method="POST">
                    from 
                    <input type="date" 
                           name="from" 
                           value="${requestScope.fromDate}" /> 
                    to 
                    <input type="date" 
                           name="to" 
                           value="${requestScope.toDate}" />
                    <input type="hidden" value="searchOrdersByDate" name="action" />
                    <button type="submit">search</button>
                </form>

            </li>

        </ul>
    </nav>
</header>