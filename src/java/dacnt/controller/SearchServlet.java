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
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel NG
 */
public class SearchServlet extends HttpServlet {

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
        String keyword = request.getParameter("txtsearch");
        String searchby = request.getParameter("searchby");
        PlantDAO dao = PlantDAO.getInstance();
        ArrayList<PlantDTO> list = null;
//        System.out.println(keyword + " " + searchby + " sdsdfnjhuksdwsedhnjk");
//        System.out.println(dao.hashCode());

        try (PrintWriter out = response.getWriter()) {
            dao.searchPlants(keyword, searchby);
            list = dao.getPlants();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");
            out.println("<link rel=\"stylesheet\" href=\"mycss.css\">");
            out.println("</head>");
            out.println("<body><section>");

            // servletcontext
            ServletContext context = getServletContext();
            String tmp = context.getInitParameter("countryName");
            out.println("<p>The website is deploying in " + tmp + "</p>");

            // servletconfig
            ServletConfig config = getServletConfig();
            String tmp2 = config.getInitParameter("language");
            out.println("<p>Language is used only only on this page: " + tmp2 + "</p>");

            out.println("<table class=\"producttable\">");
            out.println("<tr><td>product id</td><td>name</td><td>price</td><td>image</td><td>detail</td><td>action</td></tr>");

            for (PlantDTO plant : list) {
                out.println("<tr>");
                out.println("<td>" + plant.getId() + "</td>");
                out.println("<td>" + plant.getName() + "</td>");
                out.println("<td>" + plant.getPrice() + "</td>");
                out.println("<td><img src=\"" + plant.getImgPath() + "\" class=\"product\"/></td>");
                out.println("<td><a href='#'>view detail</a></td>");
                out.println("<td><a href='#'>add to your cart</a></td>");
                out.println("</tr>");
            }

            out.println("</table</section>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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
