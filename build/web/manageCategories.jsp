<%-- 
    Document   : manageCategories
    Created on : Mar 21, 2022, 8:42:50 AM
    Author     : Daniel NG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script src="scripts/processingCategory.js"></script>

    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <h1>Categories</h1>
        <form action="addCategory" method="POST">
            <input type="text" name="txtAddedCateName" 
                   value="${param.txtAddedCateName}" />
            <button type="submit"> Add </button>
        </form>

        <table class="order">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Category Name</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty requestScope.CATEGORIES.keySet()}">
                    <c:forEach var="cate" items="${requestScope.CATEGORIES.keySet()}">
                        <tr>
                            <th>${cate.cateID}</th>
                            <th> 
                                <form action="updateCategory">

                                    <input type="text" name="txtNewCateName" 
                                           value="${cate.cateName}"
                                           required=""/>
                                    <input type="hidden" name="cateId" 
                                           value="${cate.cateID}" />
                                    <a href=""
                                       onclick="return updateCategory(this);"
                                       style="margin-left: 2rem;">
                                        Update
                                    </a>
                                </form>

                            </th>
                            <th>
                                <c:if test="${not requestScope.CATEGORIES.get(cate)}">
                                    <a href="deleteCategory?cateId=${cate.cateID}"
                                       onclick="return deleteCategory();">
                                        Delete
                                    </a>
                                </c:if>
                            </th>
                        </tr>

                    </c:forEach>
                </c:if>
            </tbody>
        </table>

        <c:import url="footer.jsp"/>

    </body>
</html>
