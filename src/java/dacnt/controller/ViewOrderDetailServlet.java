/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.account.AccountDTO;
import dacnt.order.OrderDAO;
import dacnt.order.OrderDetailDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel NG
 */
public class ViewOrderDetailServlet extends HttpServlet {

    private final String ORDER_DETAIL_PAGE = "orderDetail.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String INDEX_PAGE_URL = "SearchServlet";

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
        String url = ORDER_DETAIL_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                request.setAttribute("ERROR", "You must login to view order detail");
                url = LOGIN_PAGE;
                return;
            } // end if session != null

            AccountDTO currentUser = (AccountDTO) session.getAttribute("USER");
            if (currentUser == null) {
                request.setAttribute("ERROR", "You must login to view order detail");
                url = LOGIN_PAGE;
                return;
            } // end if current user != null

            String orderid = request.getParameter("orderid");
            if (orderid != null) {
                int orderID = Integer.parseInt(orderid);
                OrderDAO dao = OrderDAO.getInstance();
                dao.getOrderDetail(orderID);
                ArrayList<OrderDetailDTO> list = dao.getOrderDetailList();
                request.setAttribute("DETAILS", list);

            }
        } catch (NamingException | NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            url = INDEX_PAGE_URL;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
