<%-- 
    Document   : manageAccounts
    Created on : Mar 20, 2022, 11:45:30 PM
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

    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <h1>Accounts</h1>

        <form action="manageAccounts" method="POST">
            <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <button type="submit"> Search </button>
        </form>

        <table class="order">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Status</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty requestScope.ACCOUNTS}">
                    <c:forEach var="account" items="${requestScope.ACCOUNTS}">
                        <tr ${account.role eq 1 ? "class='admin_row'" : ""}}>
                            <td>${account.accID}</td>
                            <td>${account.email}</td>
                            <td>${account.fullname}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.status eq 1}"> active </c:when>
                                    <c:otherwise>inactive</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${account.phone}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${account.role eq 1}"> Admin </c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${account.role eq 0}"><!--only block/unblock account of the user-->
                                    <c:url var="updateAccLink" value="updateStatusAccount">
                                        <c:param name="email" value="${account.email}"/>
                                        <c:param name="status" value="${account.status}"/>
                                        <c:param name="txtSearch" value="${param.txtSearch}"/>
                                    </c:url>
                                    <a href="${updateAccLink}">Block/UnBlock</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>



        <c:import url="footer.jsp"/>
    </body>
</html>
