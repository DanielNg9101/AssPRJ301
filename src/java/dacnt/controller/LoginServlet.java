/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.account.AccountDAO;
import dacnt.account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel NG
 */
public class LoginServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.jsp";
    private final String ADMIN_PAGE = "admin.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String remember = request.getParameter("remember");
        AccountDTO acc = null;
        String token = "";
        String jsessionID = "";
        String url = LOGIN_PAGE;
        try {
            // get jsession id
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("JSESSIONID")) {
                    jsessionID = cookies[i].getValue();
                } else if (cookies[i].getName().equals("TOKEN")) {
                    token = cookies[i].getValue();
                }
                
                if (!token.isEmpty() && !jsessionID.isEmpty()) {
                    break;
                }
            } // having jsessionid and token

            if (email.isEmpty() && password.isEmpty()) {
//                System.out.println("check token store in browser");
//                System.out.println(token);

                // call dao
                acc = AccountDAO.getAccountByToken(token);
                if (acc != null) {
                    // store user-info to session ==> get (true) ==> not needing check null
                    HttpSession session = request.getSession();
                    // [ADMIN]
                    if (acc.getRole() == 1) {
                        // redirect to admin home page
                        url = ADMIN_PAGE;
                        session.setAttribute("USER", acc);
                        
                    } else {
                        // [USER]
                        // url rewriting forward to vieworderservlet
                        String urlRewriting = "viewOrders";
                        url = urlRewriting;
                        session.setAttribute("USER", acc);
                        
                    }
                } else {
                    request.setAttribute("ERROR", "Incorrect username or password");
                }
                
            } else {
                acc = AccountDAO.getAccount(email, password);

                // check account exist in dbs
                if (acc != null) {
                    if (remember != null) {
                        // add token to accounts in dbs ==> call dao
                        token = jsessionID;
                        AccountDAO.addToken(email, token);

                        // store token's cookie
                        Cookie storeToken = new Cookie("TOKEN", token);
                        storeToken.setMaxAge(60 * 30); // 30 mins
                        response.addCookie(storeToken);
                    } else {
                        // user don't check remember me ==> token carries value of cookie
                        AccountDTO checkedUser = AccountDAO.getAccountByToken(token);
                        if (checkedUser != null) {
                            // check token's user whether same as the user who login or not
                            if (!email.equals(checkedUser.getEmail())) {
                                // delete token
                                Cookie storeToken = new Cookie("TOKEN", "");
                                storeToken.setMaxAge(0); // 30 remove cookie
                                response.addCookie(storeToken);
                            }
                        } // if checkedUser == null ==> token doesn't exist ==> dothing

                    }

                    // store user-info to session ==> get (true) ==> not needing check null
                    HttpSession session = request.getSession();

                    // [ADMIN]
                    if (acc.getRole() == 1) {
                        // redirect to admin home page
                        url = ADMIN_PAGE;
                        session.setAttribute("USER", acc);
                        
                    } else {
                        // [USER]
                        // url rewriting forward to vieworderservlet
                        String urlRewriting = "viewOrders";
                        url = urlRewriting;
                        session.setAttribute("USER", acc);
                    }
                } else {
                    request.setAttribute("ERROR", "Incorrect username or password");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            if (url != LOGIN_PAGE) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
