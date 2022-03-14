<%-- 
    Document   : invalid
    Created on : Mar 5, 2022, 2:29:33 PM
    Author     : Daniel NG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invalid</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <p>invalid email or password</p>
            <p> <a href="login.jsp">please, login again</a> </p>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
