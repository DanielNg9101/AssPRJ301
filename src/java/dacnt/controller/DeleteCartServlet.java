/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.plant.PlantDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel NG
 */
@WebServlet(name = "DeleteCartServlet", urlPatterns = {"/DeleteCartServlet"})
public class DeleteCartServlet extends HttpServlet {

    private final String VIEW_CART_PAGE_URL = "DispatchController?action=viewCart";

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

        try {
            int plantID = Integer.parseInt(request.getParameter("plantID"));

            HttpSession session = request.getSession(false);
            if (session == null) {
                return;
            } // end if session != null

            HashMap<PlantDTO, Integer> cart
                    = (HashMap<PlantDTO, Integer>) session.getAttribute("CART");
            if (cart != null) {
                PlantDTO removedPlant = null;
                for (PlantDTO curPlant : cart.keySet()) {
                    if (curPlant.getId() == plantID) {
                        removedPlant = curPlant;
                        break;
                    }
                }

                if (removedPlant != null) {
                    // plant is in the cart ==> remove
                    int removedQuantity = cart.get(removedPlant);
                    cart.remove(removedPlant);
                    int total = (int) session.getAttribute("TOTAL");
                    // cart != null ==> total != 0 ==> no need to check
                    total -= removedQuantity * removedPlant.getPrice();

                    if (cart.isEmpty()) {
                        cart = null;
                    }

                    session.setAttribute("CART", cart);
                    session.setAttribute("TOTAL", total);
                }
            }

        } catch (NumberFormatException ex) {
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
