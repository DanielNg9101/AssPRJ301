/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.admin.controller;

import dacnt.account.AccountDTO;
import dacnt.plant.PlantDAO;
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
 * @author Daniel NG
 */
@WebServlet(name = "DeletePlantServlet", urlPatterns = {"/DeletePlantServlet"})
public class DeletePlantServlet extends HttpServlet {

    private final String INDEX_PAGE_URL = "DispatchController?action=index";

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
            
            HttpSession session = request.getSession(false);
            if (session == null) {
                return;
            }
            
            AccountDTO adminAcc = (AccountDTO) session.getAttribute("ADMIN");
            if (adminAcc == null) {
                return;
            }
            
            // call dao
            PlantDAO dao = PlantDAO.getDao();
            boolean result = dao.deletePlant(plantID);
            if (result) {
                url = INDEX_PAGE_URL;
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
