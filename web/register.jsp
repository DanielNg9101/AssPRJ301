<%-- 
    Document   : register
    Created on : Jan 29, 2022, 7:47:52 PM
    Author     : dacng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section>
            <form action="register" method="POST" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr>
                        <td>email</td>
                        <td> <input type="text" 
                                    name="txtEmail" 
                                    required="" 
                                    value="${param.txtEmail}"
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/> 
                    </tr>
                    <tr>
                        <td>full name</td>
                        <td> <input type="text" 
                                    name="txtFullname" 
                                    value="${param.txtFullname}"
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
                                    value="${param.txtPhone}"
                                    required=""/> <br/>
                            ${requestScope.ERROR}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <button> register </button> </td>
                    </tr>
                </table>
            </form>

        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
