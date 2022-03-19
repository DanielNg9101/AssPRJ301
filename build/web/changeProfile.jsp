<%-- 
    Document   : changeProfile
    Created on : Feb 9, 2022, 11:12:47 PM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Profile</title>
        <link rel="stylesheet" href="styles/mycss.css"/>


    </head>
    <body>
        <%@include file="header_loginedUser.jsp" %>
        <h1>Change Profile</h1>
        <c:if test="${empty sessionScope.USER}">
            <%
                request.setAttribute("ERROR",
                        "You must login to use this function");
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            %>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
            <form action="changeProfile" method="POST" class="formregister">
                <table>
                    <c:if test="${not empty requestScope.ERROR}">
                        <tr>
                            <td colspan="2">
                                <p style="color: red;">${requestScope.ERROR}</p>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>email</td>
                        <td> 
                            <input type="text" 
                                   name="txtEmail" 
                                   value="${sessionScope.USER.email}" 
                                   readonly="readonly" />
                        </td>
                    </tr>

                    <tr>
                        <td>full name</td>
                        <td> <input type="text" 
                                    name="txtFullname" 
                                    required="" 
                                    value="${sessionScope.USER.fullname}"/> </td>
                    </tr>
                    <tr>
                        <td>phone</td>
                        <td> <input type="text" 
                                    name="txtPhone" 
                                    required="" 
                                    value="${sessionScope.USER.phone}" /> </td>
                    </tr>
                    <tr>
                        <td colspan="2"> <button type="submit">Submit</button> </td>
                    </tr>
                </table>
            </form>
        </c:if>




        <%@include file="footer.jsp" %>

    </body>
</html>
