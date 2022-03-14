/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dacng
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

    private final String INDEX_PAGE = "index.jsp";

    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGIN_PAGE = "login.jsp";

    private final String REGISTER_CONTROLLER = "RegisterServlet";
    private final String REGISTER_PAGE = "registration.jsp";

    private final String LOGOUT_CONTROLLER = "LogoutServlet";

    private final String VIEWORDERS_CONTROLLER = "ViewOrdersServlet";

    private final String CHANGE_PROFILE_CONTROLLER = "ChangeProfileServlet";
    private final String CHANGE_PROFILE_PAGE = "changeProfile.jsp";

    // [ORDER]
    private final String PROCESSING_ORDER_CONTROLLER = "ProcessingOrderServlet";

    // [CART]
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String UPDATE_CART_CONTROLLER = "UpdateCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String DELETE_CART_CONTROLLER = "DeleteCartServlet";
    private final String SAVE_SHOPPING_CART_CONTROLLER = "SaveShoppingCartServlet";

    // [PLANTS]
    private final String VIEW_PLANT_PAGE = "plant.jsp";
    private final String VIEW_PLANT_CONTROLLER = "ViewPlantServlet";

    private final String PERSONAL_PAGE = "personalPage.jsp";

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
        String url = INDEX_PAGE;
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action.trim()) {
                // [LOGIN]
                case "loginHandler":
                    url = LOGIN_PAGE;
                    break;
                case "login":
                    url = LOGIN_CONTROLLER;
                    break;

                // [REGISTER]
                case "registerHandler":
                    url = REGISTER_PAGE;
                    break;
                case "register":
                    url = REGISTER_CONTROLLER;
                    break;

                // [LOGOUT]
                case "logout":
                    url = LOGOUT_CONTROLLER;
                    break;

                // [PERSONAL_PAGE]
                case "personalPage":
                    url = PERSONAL_PAGE;
                    break;

                // [SEARCH]
                case "search":
                    url = INDEX_PAGE;
                    break;

                // [VIEW_ORDERS]
                case "viewOrders":
                    url = VIEWORDERS_CONTROLLER;
                    break;

                case "Search Order":
                    url = VIEWORDERS_CONTROLLER;
                    break;

                // [CHANGE_PROFILE]
                case "changeProfileHandler":
                    url = CHANGE_PROFILE_PAGE;
                    break;
                case "changeProfile":
                    url = CHANGE_PROFILE_CONTROLLER;
                    break;

                // [PROCESSING ORDER]
                case "cancelOrder":
                case "orderAgain":
                    url = PROCESSING_ORDER_CONTROLLER;
                    break;

                // [ADD TO CART]
                case "addToCart":
                    url = ADD_TO_CART_CONTROLLER;
                    break;

                // [VIEW CART]
                case "viewCart":
                    url = VIEW_CART_PAGE;
                    break;

                // [UPDATE CART]
                case "updateCart":
                    url = UPDATE_CART_CONTROLLER;
                    break;

                // [DELETE CART]
                case "deleteCart":
                    url = DELETE_CART_CONTROLLER;
                    break;

                // [SAVE ORDER]
                case "saveOrder":
                    url = SAVE_SHOPPING_CART_CONTROLLER;
                    break;

                // [VIEW PLANT]
                case "viewPlant":
                    url = VIEW_PLANT_CONTROLLER;
                    break;

                case "viewPlantHandler":
                    url = VIEW_PLANT_PAGE;
                    break;

                // [INDEX]
                case "index":
                    url = INDEX_PAGE;
                    break;
                default:
                    url = INDEX_PAGE;
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
