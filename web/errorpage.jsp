<%-- 
    Document   : errorpage.jsp
    Created on : Mar 5, 2022, 2:34:25 PM
    Author     : Daniel NG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <p>Something went wrong, please click <a href="index.jsp">here</a> to return index page </p>

        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
