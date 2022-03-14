<%-- 
    Document   : addPlant
    Created on : Feb 19, 2022, 9:06:42 PM
    Author     : Daniel NG
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
        <%
            if (adminAcc == null || adminAcc.getRole() != 1) {
        %>
        <p style="color: red;"> you don't have permission. Click <a href="DispatchController">here</a> to back </p> 
        <%
        } else {
            String name = adminAcc.getFullname();
        %>
        <section>
            <%--<h3>Welcome <%= name%> come back </h3>--%>
            <ct:welcome name="<%= name %>"/>
        </section>
        <form action="AdminController" method="POST">
            <h1>Add Plant</h1>
            <table>
                <tr>
                    <td>Plant Name</td>
                    <td> <input type="text" 
                                name="txtPlantName" 
                                required="" 
                                /> 
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td> <input type="number" 
                                name="txtPrice" 
                                required="" 
                                /> 
                    </td>

                </tr>
                <tr>
                    <td> Image Url </td>
                    <td> <input type="text" 
                                name="txtImg" 
                                required="" 
                                /> 
                    </td>

                </tr>
                <tr>
                    <td>Status</td>
                    <td> 
                        <select name="txtStatus">
                            <option value="1">available</option>
                            <option value="0">out of stock</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td> 
                        <select name="txtCategory">
                            <option value="2"> roses </option>
                            <option value="1"> orchild </option>
                            <option value="3"> others </option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td> Description </td>
                    <td> 
                        <textarea name="txtDescription"
                                  required="" ></textarea>
                    </td>

                </tr>

                <tr>
                    <td colspan="2"> 
                        <input type="hidden" name="action" value="addPlantHandler" />
                        <input type="submit" value="add" />
                    </td>
                </tr>
            </table>


        </form> 
        <%}%>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
