<%-- 
    Document   : editPlant
    Created on : Feb 19, 2022, 11:21:25 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.plant.PlantDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Plant</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script>
            function reconfirm() {
                return confirm("Do you want to delete?");
            }
        </script>
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
            PlantDTO editedPlant = (PlantDTO) request.getAttribute("EDITED_PLANT");
            if (editedPlant != null) {
                String[] status = {"out of stock", "available"};
                String[] categories = {"", "orchild", "roses", "others"};

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
                                value="<%= editedPlant.getName()%>"
                                /> 
                        <input type="hidden" 
                               name="plantID" 
                               value="<%= request.getParameter("plantID") %>" />
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td> <input type="number" 
                                name="txtPrice" 
                                required="" 
                                value="<%= editedPlant.getPrice()%>"

                                /> 
                    </td>

                </tr>
                <tr>
                    <td> Image Url </td>
                    <td> <input type="text" 
                                name="txtImg" 
                                required="" 
                                value="<%= editedPlant.getImgPath()%>"

                                /> 
                    </td>

                </tr>
                <tr>
                    <td>Status</td>
                    <td> 
                        <select name="txtStatus">
                            <% for (int i = 0; i < status.length; i++) {%>
                            <option value="<%= i%>" 
                                    <%= editedPlant.getStatus() == i ? "selected" : ""%> >
                                <%= status[i]%>
                            </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td> 
                        <select name="txtCategory">
                            <% for (int i = 1; i < categories.length; i++) {%>
                            <option value="<%= i%>" 
                                    <%= editedPlant.getCateid() == i ? "selected" : ""%> >
                                <%= categories[i]%>
                            </option>
                            <%}%>
                        </select>

                    </td>
                </tr>

                <tr>
                    <td> Description </td>
                    <td> 
                        <textarea name="txtDescription"
                                  required="" > <%= editedPlant.getDescription()%> </textarea>
                    </td>

                </tr>

                <tr>
                    <td colspan="2"> 
                        <input type="hidden" name="doAction" value="editPlantHandler" />
                        <input type="submit" value="editPlant" name="action" />
                        <input type="submit" 
                               value="deletePlant" 
                               name="action"
                               onclick="return reconfirm()" />  
                    </td>
                </tr>
            </table>


        </form>
        <%} else { %>
        <h1>Something went wrong</h1>
        <%}%>

        <%}%>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
