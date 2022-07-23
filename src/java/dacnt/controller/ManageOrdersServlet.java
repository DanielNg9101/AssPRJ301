/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.account.AccountDAO;
import dacnt.account.AccountDTO;
import dacnt.order.OrderDAO;
import dacnt.order.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel NG
 */
public class ManageOrdersServlet extends HttpServlet {

    private final String MANAGE_ORDER_PAGE = "manageOrders.jsp";

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
        String url = MANAGE_ORDER_PAGE;
        try {
            String email = request.getParameter("userEmail");
            OrderDAO dao = OrderDAO.getInstance();
            String from = request.getParameter("from");
            String to = request.getParameter("to");

            if (from == null || to == null || from.isEmpty() || to.isEmpty()) {
                if (email == null || email.isEmpty() || email.equals("All User")) {
                    dao.getOrders();
                } else {
                    dao.getOrders(email);
                }
            } else {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = new Date(df.parse(from).getTime());
                Date toDate = new Date(df.parse(to).getTime());

                if (email == null || email.isEmpty() || email.equals("All User")) {
                    dao.getOrdersByDate(fromDate, toDate);
                } else {
                    dao.getOrdersByDate(email, fromDate, toDate);
                }
                request.setAttribute("fromDate", fromDate);
                request.setAttribute("toDate", toDate);
            }

            ArrayList<OrderDTO> orders = dao.getOrdersList();
            request.setAttribute("ORDERS", orders);

//            System.out.println(orders);
            ArrayList<AccountDTO> accounts = AccountDAO.getAccounts();
            request.setAttribute("ACCOUNTS", accounts);

            url = url + "?userEmail=" + email;

        } catch (NamingException | SQLException | ParseException ex) {
//        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
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
