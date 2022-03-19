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
import javax.naming.NamingException;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String INDEX_PAGE_URL = "search";

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
        String url = INDEX_PAGE_URL;
        try {            
            // get the selected id
            String plantIDString = request.getParameter("plantID");
            
            if (plantIDString.isEmpty()) {
                return;
            }
            int plantID = Integer.parseInt(plantIDString);
            
//            System.out.println(plantID);
           

            // get session to store product id ==> get true
            HttpSession session = request.getSession();
            HashMap<PlantDTO, Integer> cart
                    = (HashMap<PlantDTO, Integer>) session.getAttribute("CART");

            // getplant
            PlantDAO dao = PlantDAO.getDao();
            PlantDTO plant = dao.getPlant(plantID);

            if (plant == null) {
                return;
            }
            
//            // handler url
//            String lastUrl = (String) session.getAttribute("lastUrl");
//            if (lastUrl != null) {
//                url = lastUrl;
//            }

            // total amount of cart
            Integer total = (Integer) session.getAttribute("TOTAL");
            if (total == null) {
                total = 0;
            }

            // session timeout or user checkout
            if (cart == null) {
                cart = new HashMap<>();
                cart.put(plant, 1);
            } else {
                // check plant is exist in cart or not
                for (PlantDTO currentPlant : cart.keySet()) {
                    if (currentPlant.getId() == plantID) {
                        plant = currentPlant;
                        break;
                    }
                }
                Integer quantity = cart.get(plant);
                if (quantity == null) {
                    cart.put(plant, 1);
                } else {
                    cart.put(plant, ++quantity);
                }
            }
            total += plant.getPrice();

            session.setAttribute("CART", cart);
            session.setAttribute("TOTAL", total);            
        } catch (NamingException | SQLException | NumberFormatException ex) {
        } finally {
//            response.sendRedirect(url);
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
