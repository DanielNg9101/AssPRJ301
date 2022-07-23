/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.account.AccountDTO;
import dacnt.order.OrderDAO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ChangeOrderStatusServlet", urlPatterns = {"/ChangeOrderStatusServlet"})
public class ChangeOrderStatusServlet extends HttpServlet {

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
        OrderDAO dao = OrderDAO.getInstance();
        String url = "";
//        System.out.println(urlRewriting);
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");

            if (account != null) {
                if (account.getRole() == 0) {
                    // handle last request
                    String urlRewriting = "viewOrders?category=";
                    String category = request.getParameter("category");
                    urlRewriting += category;
                    url = urlRewriting;
                } else {
                    url = "manageOrders";
                    String userEmail = request.getParameter("userEmail");
                    if (userEmail != null || !userEmail.isEmpty()) {
                        url = url + "?userEmail=" + userEmail;
                    }

                }
            }
            int orderID = Integer.parseInt(request.getParameter("orderID"));

            String action = request.getParameter("action");
            switch (action.trim()) {
                case "cancelOrder":
                    // call dao 1 -> 3
                    dao.updateOrderStatus(orderID, "cancel");
                    break;

                case "orderAgain":
                    //call dao 3 -> 1
                    dao.updateOrderStatus(orderID, "processing");
                    break;

            }
        } catch (NamingException | SQLException | NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
