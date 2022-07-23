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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dacng
 */
@WebServlet(name = "ChangeProfileServlet", urlPatterns = {"/ChangeProfileServlet"})
public class ChangeProfileServlet extends HttpServlet {

    private final String PERSONAL_PAGE_URL = "personalPage.jsp";
    private final String CHANGE_PROFILE_PAGE_URL = "changeProfile.jsp";
    private final String LOGIN_PAGE_URL = "login.jsp";

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

        String url = LOGIN_PAGE_URL;
        String newName = request.getParameter("txtFullname");
        String newPhone = request.getParameter("txtPhone");
        String email = request.getParameter("txtEmail");

        try {
            if (newName.trim().isEmpty() || newPhone.trim().isEmpty()) {
                url = CHANGE_PROFILE_PAGE_URL;
                request.setAttribute("ERROR", "Invalid information");
                request.getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }

            if (newPhone.matches("^.*[a-zA-Z]+.*$")) {
                url = CHANGE_PROFILE_PAGE_URL;
                request.setAttribute("ERROR", "The phone is invalid");
                request.getRequestDispatcher(url)
                        .forward(request, response);
                return;
            }

            // call dao
            HttpSession session = request.getSession(false);
            // session time out
            if (session == null) {
                return;
            } // end if session != null

            // session has time but user log out ==> cannot update
            AccountDTO currentUser = (AccountDTO) session.getAttribute("USER");
            if (currentUser == null) {
                return;
            } // end if user != null

            boolean result
                    = AccountDAO.updateAccount(email, currentUser.getPassword(),
                            newName, newPhone);

            if (result) {
                currentUser.setFullname(newName);
                currentUser.setPhone(newPhone);

                session.setAttribute("USER", currentUser);

                String urlRewriting = "viewOrders";
                url = urlRewriting;
                response.sendRedirect(url);

            }
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
        } finally {
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
