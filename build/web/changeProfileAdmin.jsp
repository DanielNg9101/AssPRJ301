<%-- 
    Document   : changeProfileAdmin
    Created on : Feb 19, 2022, 12:34:19 PM
    Author     : Daniel NG
--%>

<%@page import="dacnt.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/mycss.css"/>
        <script>
            function reconfirm() {
                return confirm("Do you want to delete?");
            }
        </script>

    </head>
    <body>
        <header>
            <%@include file="header_loginedAdmin.jsp" %>
        </header>

        <%            if (adminAcc == null || adminAcc.getRole() != 1) {
        %>
        <p style="color: red;"> you don't have permission. Click <a href="DispatchController">here</a> to back </p> 
        <%
        } else {
            // changed acc != null b/c in render, if changed acc == null => redirect to index
            AccountDTO changedAccount
                    = (AccountDTO) request.getAttribute("CHANGED_ACCOUNT");
            if (changedAccount != null) {
                String email = changedAccount.getEmail();
                String name = changedAccount.getFullname();
                String phone = changedAccount.getPhone();
        %>
        <section>
            <h2> Change Profile of <%= changedAccount.getEmail()%> </h2>
        </section>
        <form action="AdminController" method="post">
            <table>
                <tr>
                    <td>email</td>
                    <td> 
                        <input type="text" name="txtEmail" value="<%= email%>" readonly="readonly" />
                    </td>
                </tr>
                <%
                    if (!changedAccount.getEmail().equals(adminAcc.getEmail())) {
                        String[] roles = {"user", "admin"};
                        String[] status = {"inactive", "active"};
                %>
                <tr>
                    <td>status</td>
                    <td> <select name="txtStatus">
                            <% for (int i = 0; i < status.length; i++) {
                                    if (changedAccount.getStatus() == i) {
                            %>
                            <option value="<%= i%>" selected=""><%= status[i]%></option>
                            <%
                            } else {
                            %>
                            <option value="<%= i%>" ><%= status[i]%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>role</td>
                    <td> <select name="txtRole">
                            <% for (int i = 0; i < roles.length; i++) {
                                    if (changedAccount.getRole() == i) {
                            %>
                            <option value="<%= i%>" selected=""><%= roles[i]%></option>
                            <%
                            } else {
                            %>
                            <option value="<%= i%>" ><%= roles[i]%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <% } else {%>

                <tr>
                    <td>full name</td>
                    <td> <input type="text" name="txtFullname" required="" value="<%= name%>"/> </td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td> <input type="text" name="txtPhone" required="" value="<%= phone%>" /> </td>
                </tr>
                <% }%>
                <tr>
                    <td colspan="2">             
                        <input type="hidden" name="doAction" value="changeProfileHandler" />
                        <input type="submit" value="changeProfile" name="action" />  
                        <input type="submit" 
                               value="deleteAccount" 
                               name="action"
                               onclick="return reconfirm()" />  
                    </td>
                </tr>
            </table>


        </form>

        <%
        } else {
        %>
        <h2>Something went wrong</h2>
        <%
                }
            }
        %>



        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
