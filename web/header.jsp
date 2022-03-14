<%-- 
    Document   : header
    Created on : Jan 29, 2022, 5:24:07 PM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ct" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li> <a href="DispatchController"> <img src="images/logo.jpg" id="logo" class="plantimg" /> </a> </li>
                    <li> <a href="DispatchController">Home</a> </li>
                        <%
                            AccountDTO currentUser = (AccountDTO) session.getAttribute("CURRENT_USER");
                            AccountDTO adminAcc = null;
                            if (currentUser == null) {
                                currentUser = (AccountDTO) session.getAttribute("ADMIN");
                            }
                            if (currentUser == null) {
                        %>
                    <li> <a href="DispatchController?action=registerHandler">Register</a> </li>
                    <li> <a href="DispatchController?action=loginHandler">Login</a> </li>
                        <%
                        } else {
                            if (currentUser.getRole() == 0) { // user account
                        %>
                    <li> <a href="DispatchController?action=viewOrders&category=">Personal Page</a> </li>
                        <%
                            } else { // menu of admin account
                                adminAcc = currentUser;
                        %>
                    <li> <a href="AdminController?action=viewAccounts">all accounts</a> </li>
                    <li> <a href="AdminController?action=addPlant">add plant</a> </li>
                    <li> <a href="AdminController?action=changeProfile&accID=<%= currentUser.getAccID()%>">change profile</a> </li>    
                        <%
                            }
                        %>
                        <%
                            }
                            if (currentUser

                            == null || currentUser.getRole () 
                            
                            == 0) {
                        %>

                    <li> <a href="DispatchController?action=viewCart">View Cart</a> </li>
                        <%}%>
                        <%
                            if (currentUser

                            
                            
                            != null) {
                        %>
                    <li> <a href="DispatchController?action=logout">Logout</a> </li>
                        <%}%>

                    <li> <form action="DispatchController" method="POST" class="formsearch" >
                            <input type="text" name="txtSearch" 
                                   value="<%= request.getParameter("txtSearch") == null
                                           ? "" : request.getParameter("txtSearch")%>" />
                            <select name="searchby">
                                <option value="byname">by name</option>
                                <option value="bycate">by category</option>
                            </select>
                            <input type="submit" value="search" name="action" />
                        </form> </li>
                </ul>
            </nav>
        </header>
    </body>
</html>
