<%-- 
    Document   : login.jsp
    Created on : Jan 29, 2022, 7:42:02 PM
    Author     : dacng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link rel="stylesheet" href="styles/mycss.css"/>


    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>

            <form action="DispatchController" method="POST" class="formlogin">
                <h1>Login</h1>
                <%
                    String warning = (String) request.getAttribute("WARNING");
                %> 
                <font style="color: red;"> <%= warning == null ? "" : warning%> </font>
                <table>
                    <tr>
                        <td>email</td>
                        <td> <input type="text" name="txtEmail"/> </td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td> <input type="password" name="txtPassword"/> </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="checkbox" name="remember" value="remembered" /> Remember me </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="submit" value="login" name="action" /> </td>
                    </tr>
                </table>
            </form>

        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
