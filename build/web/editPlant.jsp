<%-- 
    Document   : editPlant
    Created on : Feb 19, 2022, 11:21:25 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.plant.PlantDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Plant</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script src="scripts/processingPlant.js"></script>
    </head>
    <body>        <!--<p style="color: red;"> you don't have permission. Click <a href="DispatchController">here</a> to back </p>--> 

        <c:import url="header_loginedAdmin.jsp"/>
        <c:set var="status" value="${fn:split('out of stock, available', ',')}" />

        <c:if test="${not empty requestScope.EDITED_PLANT}">
            <c:set var="plant" value="${requestScope.EDITED_PLANT}"/>
            <form action="editPlant" method="POST">
                <h1>Edit Plant</h1>
                <table>
                    <tr>
                        <td>Plant Name</td>
                        <td> <input type="text" 
                                    name="txtPlantName" 
                                    required=""
                                    value="${plant.name}"
                                    /> 
                            <input type="hidden" 
                                   name="plantID" 
                                   value="<%= request.getParameter("plantID")%>" />
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td> 
                            <input type="number" 
                                   name="txtPrice" 
                                   required="" 
                                   value="${plant.price}"
                                   /> 
                        </td>

                    </tr>
                    <tr>
                        <td> Image Url </td>
                        <td> <input type="text" 
                                    name="txtImg" 
                                    required="" 
                                    value="${plant.imgPath}"
                                    /> 
                        </td>

                    </tr>
                    <tr>
                        <td>Status</td>
                        <td> 
                            <select name="txtStatus">
                                <c:forEach var="i" begin="0" 
                                           end="${fn:length(status) - 1}">
                                    <option value="${i}" 
                                            ${plant.status eq i ? "selected" : ""}>
                                        ${status[i]}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td> 
                            <select name="txtCategory">
                                <c:if test="${not empty requestScope.CATEGORIES}">
                                    <c:forEach var="cate" items="${requestScope.CATEGORIES}">
                                        <option value="${cate.cateID}"
                                                ${plant.cateid eq i ? "selected" : ""}>
                                            ${cate.cateName}
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>

                        </td>
                    </tr>

                    <tr>
                        <td> Description </td>
                        <td> 
                            <textarea name="txtDescription"
                                      required="" > ${plant.description} </textarea>
                        </td>

                    </tr>

                    <tr>
                        <td> <a href="" 
                                onclick="return editPlant(this);">Edit</a> </td>
                        <td> <a href="deletePlant?plantID=${plant.id}"
                                onclick="return deletePlant()">Delete</a> </td>
                    </tr>
                </table>


            </form>
        </c:if>
        <%@include file="footer.jsp" %>
    </body>
</html>
