<%-- 
    Document   : addPlant
    Created on : Feb 19, 2022, 9:06:42 PM
    Author     : Daniel NG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>

    </head>
    <body>
        <%@include file="header_loginedAdmin.jsp" %>


        <form action="addPlant" method="POST">
            <h1>Add Plant</h1>
            <table>
                <tr>
                    <td>Plant Name</td>
                    <td> <input type="text" 
                                name="txtPlantName" 
                                required="" 
                                value="${param.txtPlantName}"
                                /> 
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td> <input type="number" 
                                name="txtPrice" 
                                required="" 
                                value="${param.txtPrice}"
                                /> 
                    </td>

                </tr>
                <tr>
                    <td> Image Url </td>
                    <td> <input type="text" 
                                name="txtImg" 
                                required="" 
                                value="${param.txtImg}"
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
                            <c:if test="${not empty requestScope.CATEGORIES.keySet()}">
                                <c:forEach var="cate" items="${requestScope.CATEGORIES.keySet()}">
                                    <option value="${cate.cateID}">
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
                                  required="" 
                                  >${param.txtDescription}</textarea>
                    </td>

                </tr>

                <tr>
                    <td colspan="2"> 
                        <input type="submit" value="Add" />
                    </td>
                </tr>
            </table>
        </form> 
        <%@include file="footer.jsp" %>
    </body>
</html>
