/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.plant.PlantDAO;
import dacnt.plant.PlantDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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

        String plantID = request.getParameter("plantID");
        String newQuantity = request.getParameter("quantity");
        String url = VIEW_CART_PAGE_URL;
        try {
//            System.out.println(plantID + " wejhehj " + newQuantity);
            
            PlantDAO dao = PlantDAO.getInstance();
            HttpSession session = request.getSession();
            HashMap<PlantDTO, Integer> cart
                    = (HashMap<PlantDTO, Integer>) session.getAttribute("CART");
            PlantDTO plant = null;
            if (cart != null) {
                // check plant is exist in cart or not
                for (PlantDTO currentPlant : cart.keySet()) {
                    if (currentPlant.getId() == Integer.parseInt(plantID)) {
                        plant = currentPlant;
                        // System.out.println(newQuantity);
                        cart.put(plant, Integer.parseInt(newQuantity));

                        session.setAttribute("CART", cart);
                        break;
                    }
                }
                if (plant != null) {
                    // update total
                    int total = 0;
                    for (PlantDTO currentPlant : cart.keySet()) {
                        total += currentPlant.getPrice() * cart.get(currentPlant);
                    }
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
