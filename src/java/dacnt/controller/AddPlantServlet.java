/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import dacnt.plant.PlantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel NG
 */
public class AddPlantServlet extends HttpServlet {

    private final String ADD_PLANT_PAGE = "viewAddPlant";
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
        String url = ADD_PLANT_PAGE;
        try {
            String txtPlantName = request.getParameter("txtPlantName");
            int price = Integer.parseInt(request.getParameter("txtPrice"));
            int status = Integer.parseInt(request.getParameter("txtStatus"));
            int category = Integer.parseInt(request.getParameter("txtCategory"));
            String imgUrl = request.getParameter("txtImg");
            String description = request.getParameter("txtDescription");

            if (txtPlantName.isEmpty()
                    || price < 0 || (status < 0 || status > 1)
                    || imgUrl.isEmpty() || description.isEmpty()) {
                return;
            } // done validation
//            System.out.println(txtPlantName);
//            System.out.println(price);
//            System.out.println(status);
//            System.out.println(category);
//            System.out.println(imgUrl);
//            System.out.println(description);

            PlantDAO dao = PlantDAO.getInstance();
//            System.out.println(dao);
            boolean result = dao.insertPlant(txtPlantName, price, imgUrl,
                    description, status, category);
//            System.out.println(result);
            if (result) {
                url = INDEX_PAGE_URL;
            }

        } catch (NamingException | SQLException |NumberFormatException ex) {
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
