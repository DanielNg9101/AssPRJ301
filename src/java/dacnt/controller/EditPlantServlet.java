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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel NG
 */
@WebServlet(name = "EditPlantServlet", urlPatterns = {"/EditPlantServlet"})
public class EditPlantServlet extends HttpServlet {

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
            String txtPlantID = request.getParameter("plantID");
            if (txtPlantID.isEmpty()) {
                return;
            }

            int plantID = Integer.parseInt(txtPlantID);
            PlantDAO dao = PlantDAO.getInstance();
            PlantDTO editedPlant = dao.getPlant(plantID);
            if (editedPlant == null) {
                return;
            }

            String plantName = request.getParameter("txtPlantName");
            int price = Integer.parseInt(request.getParameter("txtPrice"));
            String img = request.getParameter("txtImg");
            int status = Integer.parseInt(request.getParameter("txtStatus"));
            int category = Integer.parseInt(request.getParameter("txtCategory"));
            String description = request.getParameter("txtDescription");

            // call dao
            boolean result = dao.updatePlant(plantID, plantName, price, img,
                    description, status, category);

            if (result) {
                String urlRewriting = "viewPlant?plantID="
                        + plantID;
                url = urlRewriting;
            }
        } catch (NumberFormatException ex) {
        } catch (NamingException ex) {
        } catch (SQLException ex) {
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
