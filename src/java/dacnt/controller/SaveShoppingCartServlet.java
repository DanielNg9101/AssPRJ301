/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.account.AccountDTO;
import dacnt.order.OrderDAO;
import dacnt.plant.PlantDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
public class SaveShoppingCartServlet extends HttpServlet {

    private final String INDEX_PAGE_URL = "SearchServlet";
    private final String VIEW_CART_PAGE_URL = "viewCart.jsp";

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
        String url = VIEW_CART_PAGE_URL;
        String warning = "";

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                // check log in time out
                AccountDTO currentUser
                        = (AccountDTO) session.getAttribute("USER");
                HashMap<PlantDTO, Integer> cart
                        = (HashMap<PlantDTO, Integer>) session.getAttribute("CART");
                if (cart == null || cart.isEmpty()) {
                    warning = "Your cart is empty";
                } else {
                    if (currentUser == null) {
                        warning = "You must login to finish the shopping";
                    } else {
                        OrderDAO dao = OrderDAO.getInstance();
                        boolean result = dao.insertOrder(currentUser.getEmail(), cart);
                        if (result) {
                            // insert successfully
                            session.setAttribute("CART", null);
                            session.setAttribute("TOTAL", 0);
                            warning = "Save your cart sucessfully";
                            url = INDEX_PAGE_URL;
                        } else {
                            warning = "These products are out of stock";
                        }
                    }
                }
                request.setAttribute("WARNING", warning);
            }

        } catch (NamingException | SQLException ex) {
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
