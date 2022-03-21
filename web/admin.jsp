<%-- 
    Document   : admin
    Created on : Feb 19, 2022, 1:08:59 AM
    Author     : Daniel NG
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <%@include file="header_loginedAdmin.jsp" %>
        
        <section class="right">
            <img src="images/background.jpg" class="img-bg" />
        </section>

        <%@include file="footer.jsp" %>
    </body>
</html>
