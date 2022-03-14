<%-- 
    Document   : header_loginedAdmin
    Created on : Feb 19, 2022, 9:30:13 AM
    Author     : Daniel NG
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
        <nav>
            <ul>
                <%
                    AccountDTO adminAcc = (AccountDTO) session.getAttribute("ADMIN");
                    if (adminAcc != null) {
                %>
                <li> <a href="DispatchController?action=index"> <img src="images/logo.jpg" id="logo" class="plantimg" /> </a> </li>
                <li> <a href="DispatchController?action=index">Home</a> </li>
                <li> <a href="AdminController?action=viewAccounts">all accounts</a> </li>
                <li> <a href="AdminController?action=addPlant">add plant</a> </li>
                <li> <a href="AdminController?action=changeProfile&accID=<%= adminAcc.getAccID()%>">change profile</a> </li>
                <li> <a href="DispatchController?action=logout">logout</a> </li>
                <li>
                    <form action="AdminController" method="POST">
                        <input type="text" name="txtSearch" 
                               value="<%= request.getParameter("txtSearch") == null
                                       ? "" : request.getParameter("txtSearch")%>" />
                        <input type="submit" value="search" name="action" />
                    </form>
                </li>
                <%
                } else {
                %>
                <li> <a href="DispatchController"> <img src="images/logo.jpg" id="logo" class="plantimg" /> </a> </li>
                <li> <a href="DispatchController">Home</a> </li>
                <li> <a href="DispatchController?action=loginHandler">Login</a> </li>
                    <%
                        }
                    %>
            </ul>
        </nav>
    </body>
</html>
