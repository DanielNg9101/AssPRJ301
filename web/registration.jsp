<%-- 
    Document   : registration
    Created on : Jan 29, 2022, 7:47:52 PM
    Author     : dacng
--%>

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
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String txtEmail = (String) request.getAttribute("txtEmail");
                String txtFullname = (String) request.getAttribute("txtFullname");
                String txtPhone = (String) request.getAttribute("txtPhone");
                String ERROR = (String) request.getAttribute("ERROR");
            %>

            <form action="DispatchController" method="POST" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr>
                        <td>email</td>
                        <td> <input type="text" 
                                    name="txtEmail" 
                                    required="" 
                                    value="<%= txtEmail == null ? "" : txtEmail%>"
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/> 
                    </tr>
                    <tr>
                        <td>full name</td>
                        <td> <input type="text" 
                                    name="txtFullname" 
                                    value="<%= txtFullname == null ? "" : txtFullname%>"
                                    required=""/> </td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td> <input type="password" 
                                    required=""
                                    name="txtPassword"/> </td>
                    </tr>
                    <tr>
                        <td>phone</td>
                        <td> <input type="text" 
                                    name="txtPhone"
                                    value="<%= txtPhone == null ? "" : txtPhone%>"
                                    required=""/> <br/>
                            <%= ERROR == null ? "" : ERROR%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <input type="submit" value="register" name="action" /> </td>
                    </tr>
                </table>
            </form>

        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
