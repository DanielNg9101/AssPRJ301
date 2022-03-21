<%-- 
    Document   : header_loginedAdmin
    Created on : Mar 20, 2022, 11:10:37 PM
    Author     : Daniel NG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/customtag" prefix="ct" %>
        <header>
            <nav>
                <ul>
                    <li> <a href="search"> 
                            <img src="images/logo.jpg" id="logo" class="plantimg" /> 
                        </a> 
                    </li>
                    <li> <a href="manageAccounts">Manage Accounts</a> </li>
                    <li> <a href="manageOrders">Manage Orders</a> </li>
                    <li> <a href="search">Manage Plants</a> </li>
                    <li> <a href="manageCategories">Manage Categories</a> </li>
                    <li> Welcome ${sessionScope.USER.fullname} | <a href="logout">logout</a> </li>
                </ul>
            </nav>
        </header>
