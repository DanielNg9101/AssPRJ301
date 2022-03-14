/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel NG
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    private final String ADMIN_PAGE = "admin.jsp";
    // C

    // R
    private final String VIEW_ACCOUNTS_CONTROLLER = "ViewAccountsServlet";

    // U
    private final String CHANGE_PROFILE_PAGE = "changeProfileAdmin.jsp";
    private final String CHANGE_PROFILE_RENDER = "RenderChangeProfileServlet";
    private final String CHANGE_PROFILE_CONTROLLER = "ChangeProfileServlet";

    // D
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";

    // plant
    private final String ADD_PLANT_PAGE = "addPlant.jsp";
    private final String ADD_PLANT_CONTROLLER = "AddPlantServlet";
    
    private final String EDIT_PLANT_PAGE = "editPlant.jsp";
    private final String EDIT_PLANT_RENDER = "RenderEditPlantServlet";
    private final String EDIT_PLANT_CONTROLLER = "EditPlantServlet";
    
    private final String DELETE_PLANT_CONTROLLER = "DeletePlantServlet";

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
        String url = ADMIN_PAGE;
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action.trim()) {
                // R
                case "viewAccounts":
                    url = VIEW_ACCOUNTS_CONTROLLER;
                    break;

                case "search":
                    url = VIEW_ACCOUNTS_CONTROLLER;
                    break;

                // U
                case "changeProfile":
                    String doAction = request.getParameter("doAction");
                    if (doAction == null) {
                        doAction = "";
                    }
                    if (doAction.equals("")) {
                        url = CHANGE_PROFILE_RENDER;
                    } else if (doAction.equals("tranferToProfilePage")) {
                        url = CHANGE_PROFILE_PAGE;
                    } else if (doAction.equals("changeProfileHandler")) {
                        url = CHANGE_PROFILE_CONTROLLER;
                    } else {
                        String urlRewriting = "DispatchController?action=viewOrders&category=";
                        url = urlRewriting;
                    }
                    break;

                // D
                case "deleteAccount":
                    url = DELETE_ACCOUNT_CONTROLLER;
                    break;

                // plant
                case "addPlant":
                    url = ADD_PLANT_PAGE;
                    break;

                case "addPlantHandler":
                    url = ADD_PLANT_CONTROLLER;
                    break;

                case "editPlant":
                    doAction = request.getParameter("doAction");
                    if (doAction == null) {
                        doAction = "";
                    }
                    if (doAction.equals("")) {
                        url = EDIT_PLANT_RENDER;
                    } else if (doAction.equals("tranferToEditPage")) {
                        url = EDIT_PLANT_PAGE;
                    } else if (doAction.equals("editPlantHandler")) {
                        url = EDIT_PLANT_CONTROLLER;
                    } else {
                        String urlRewriting = "DispatchController?action=viewOrders&category=";
                        url = urlRewriting;
                    }
                    break;
                    
                case "deletePlant":
                    url = DELETE_PLANT_CONTROLLER;
                    break;

                case "index":
                    url = ADMIN_PAGE;
                    break;

                default:
                    url = ADMIN_PAGE;
                    break;
            }

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
