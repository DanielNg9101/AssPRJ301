<%-- 
    Document   : header
    Created on : Jan 29, 2022, 5:24:07 PM
    Author     : dacng
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ct" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<header>
    <nav>
        <ul>
            <li> 
                <a href="search"> 
                    <img src="images/logo.jpg" id="logo" class="plantimg" /> 
                </a> 
            </li>
            <li> 
                <a href="search">Home</a> 
            </li>
            
            <c:if test="${empty sessionScope.USER}">
                <li> <a href="login.jsp">Login</a> </li>
                <li> <a href="register.jsp">Register</a> </li>
            </c:if>
            <c:if test="${not empty sessionScope.USER}">
                <li> <a href="viewOrders">Personal Page</a> </li>
                <li> <a href="logout">Logout</a> </li>
            </c:if>

            <li> <a href="viewCart.jsp">View Cart</a> </li>
            <li> <form action="search" method="POST" class="formsearch" >
                    <input type="text" name="txtSearch" 
                           value="${param.txtSearch}" />
                    <select name="searchby">
                        <option value="byname" 
                                ${param.searchby.equals("byname") 
                                  ? "selected" : "" }>
                            by name
                        </option>
                        <option value="bycate"
                                ${param.searchby.equals("bycate") 
                                  ? "selected" : "" }>
                            by category
                        </option>
                    </select>
                    <button type="submit">search</button>
                </form> </li>
        </ul>
    </nav>
</header>

