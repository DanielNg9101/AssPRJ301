<%-- 
    Document   : index
    Created on : Jan 29, 2022, 7:39:57 PM
    Author     : dacng
--%>

<%@page import="dacnt.plant.PlantDAO"%>
<%@page import="dacnt.plant.PlantDTO"%>
<%@page import="java.util.ArrayList"%>
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
            <%
                session.setAttribute("lastUrl", "DispatchController?action=index");

                String keyword = request.getParameter("txtSearch");
                String searchby = request.getParameter("searchby");
                ArrayList<PlantDTO> list;
                PlantDAO dao = PlantDAO.getInstance();
                String[] tmp = {"out of stock", "available"};
                if (keyword == null && searchby == null) {
                    // when the page is loaded, display all products
                    dao.searchPlants("", "");
                } else {
                    dao.searchPlants(keyword, searchby);
                }
                // get list of plantdto
                list = dao.getPlants();

                if (list != null && !list.isEmpty()) {
                    for (PlantDTO dto : list) {
            %> 
            <table class="product">
                <tr>
                    <td> <img src="<%= dto.getImgPath()%>" class ="plantimg" /> </td>
                    <td> Product ID: <a href="DispatchController?action=viewPlant&plantID=<%= dto.getId()%>"><%= dto.getId()%></a> </td>
                    <td> Product Name: <%= dto.getName()%> </td>
                    <td> Price: <%= dto.getPrice()%> </td>
                    <td> Status: <%= tmp[dto.getStatus()]%> </td>
                    <td> Category: <%= dto.getCatename()%> </td>
                    <%
                        if (currentUser == null || currentUser.getRole() == 0) {
                    %>
                    <td> <a href="DispatchController?action=addToCart&plantID=<%= dto.getId()%>">add to cart</a> </td>
                    <%}%>
                </tr>
            </table>

            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

