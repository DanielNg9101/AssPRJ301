<%-- 
    Document   : changeProfile
    Created on : Feb 9, 2022, 11:12:47 PM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>


    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <%            AccountDTO currentUser = (AccountDTO) session.getAttribute("CURRENT_USER");
            if (currentUser == null) {
        %> 
        <p style="color: red;"> you must <a href="login.jsp">login</a> to view personal page </p>
        <%
        } else {
            String name = currentUser.getFullname();
            String email = currentUser.getEmail();
            String phone = currentUser.getPhone();
        %>
        <form action="DispatchController" method="POST" class="formregister">
            <h1> Change your profile </h1>
            <table>
                <tr>
                    <td>email</td>
                    <td> 
                        <input type="text" name="txtEmail" value="<%= email%>" readonly="readonly" />
                    </td>
                </tr>

                <tr>
                    <td>full name</td>
                    <td> <input type="text" name="txtFullname" required="" value="<%= name%>"/> </td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td> <input type="text" name="txtPhone" required="" value="<%= phone%>" /> </td>
                </tr>
                <tr>
                    <td colspan="2"> <input type="submit" value="changeProfile" name="action" /> </td>
                </tr>
            </table>
        </form>

        <% }%>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>    

    </body>
</html>
