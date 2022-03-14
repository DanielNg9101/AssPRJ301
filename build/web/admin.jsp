<%-- 
    Document   : admin
    Created on : Feb 19, 2022, 1:08:59 AM
    Author     : Daniel NG
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dacnt.account.AccountDTO"%>
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
            <%@include file="header_loginedAdmin.jsp" %>
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

        <section>
            <%
                ArrayList<AccountDTO> list = (ArrayList<AccountDTO>) request.getAttribute("ACCOUNTS");
                if (list != null && !list.isEmpty()) {
                    String[] roles = {"user", "admin"};
                    String[] status = {"inactive", "active"};
            %> 
            <table border="1" id="accounts" >
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Full name</th>
                        <th>Status</th>
                        <th>Phone</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (AccountDTO acc : list) {
                            if (acc.getEmail().equals(adminAcc.getEmail())) {
                                continue;
                            }
                    %>
                    <tr>
                        <td> <a 
                                href="AdminController?action=changeProfile&accID=<%= acc.getAccID()%>">
                                <%= acc.getAccID()%>
                            </a> 
                        </td>
                        <td> <a 
                                href="AdminController?action=changeProfile&accID=<%= acc.getAccID()%>">
                                <%= acc.getEmail()%>
                            </a> 
                        </td>
                        <td> <%= acc.getPassword()%> </td>
                        <td> <%= acc.getFullname()%> </td>
                        <td> <%= status[acc.getStatus()]%> </td>
                        <td> <%= acc.getPhone()%> </td>
                        <td> <%= roles[acc.getRole()]%> </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <section>
                <h2>Note</h2>
                <ul class="note">
                    <li>Click ID or Email to edit Account</li>
                </ul>
            </section>

            <%
            } else {
            %>
            <h1>No accounts exist!</h1>

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
