/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.order;

import dacnt.plant.PlantDTO;
import dacnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author dacng
 */
public class OrderDAO implements Serializable {

    private static OrderDAO dao;

    public static OrderDAO getInstance() {
        if (dao == null) {
            dao = new OrderDAO();
        }
        return dao;
    }

    // [GET] - get all order of an user
    // [IN]: user email
    // [OUT]: void
    private ArrayList<OrderDTO> ordersList;

    public ArrayList<OrderDTO> getOrdersList() {
        return ordersList;
    }

    public void getOrders(String email)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // make query
                String query = "Select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "From Orders \n"
                        + "Where AccID = (Select [accID] \n"
                        + "               From Accounts\n"
                        + "		  Where email Like ?)";
                // make stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                // execute
                rs = stm.executeQuery();

                // process arraylist
                if (this.ordersList == null) {
                    this.ordersList = new ArrayList<>();
                } else {
                    this.ordersList.removeAll(this.ordersList);
                }

                // process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    OrderDTO dto = new OrderDTO(orderID, orderDate, shipDate, status, accID);
                    this.ordersList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getOrders()
            throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // make query
                String query = "Select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "From Orders \n";

                // make stm
                stm = con.createStatement();

                // execute
                rs = stm.executeQuery(query);

                // process arraylist
                if (this.ordersList == null) {
                    this.ordersList = new ArrayList<>();
                } else {
                    this.ordersList.removeAll(this.ordersList);
                }

                // process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    OrderDTO dto = new OrderDTO(orderID, orderDate, shipDate, status, accID);
                    this.ordersList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    // [GET} - get order by category: "caompleted", "canceled", "processing"
    // {IN]: email, category
    // [OUT]: void
    public void getOrdersByCategory(String email, String category)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int categoryIntType;

        // processing in vieworderservlet, so category can only be one in 3 opt
        if (category.equals("completed")) {
            categoryIntType = 2;
        } else if (category.equals("canceled")) {
            categoryIntType = 3;
        } else {
            categoryIntType = 1;
        }

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // make query
                String query = "Select OrderID, OrdDate, shipdate, status, AccID\n"
                        + "From Orders \n"
                        + "Where AccID = (Select [accID]\n"
                        + "               From Accounts\n"
                        + "		  Where email Like ?)\n"
                        + "And status = " + categoryIntType;
                // make stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                // execute
                rs = stm.executeQuery();

//                System.out.println(query);
                // process arraylist
                if (this.ordersList == null) {
                    this.ordersList = new ArrayList<>();
                } else {
                    this.ordersList.removeAll(this.ordersList);
                }

                // process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    OrderDTO dto = new OrderDTO(orderID, orderDate, shipDate, status, accID);
                    this.ordersList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    // [GET} - get order by date
    // {IN]: from, to
    // [OUT]: void
    public void getOrdersByDate(String email, Date from, Date to)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // make query
                String query = "Select OrderID, OrdDate, shipdate, status,AccID\n"
                        + "From Orders \n"
                        + "Where AccID = (Select [accID]\n"
                        + "               From Accounts\n"
                        + "		  Where email Like ?)\n"
                        + "And OrdDate between ? and ?";

                // make stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                stm.setDate(2, from);
                stm.setDate(3, to);

                // execute
                rs = stm.executeQuery();

//                System.out.println(query);
                // process arraylist
                if (this.ordersList == null) {
                    this.ordersList = new ArrayList<>();
                } else {
                    this.ordersList.removeAll(this.ordersList);
                }

                // process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    OrderDTO dto = new OrderDTO(orderID, orderDate, shipDate, status, accID);
                    this.ordersList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    // [GET} - get all order by date
    // {IN]: from, to
    // [OUT]: void
    public void getOrdersByDate(Date from, Date to)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // make query
                String query = "Select OrderID, OrdDate, shipdate, status,AccID\n"
                        + "From Orders \n"
                        + "Where OrdDate between ? and ?";

                // make stm
                stm = con.prepareStatement(query);
                stm.setDate(1, from);
                stm.setDate(2, to);

                // execute
                rs = stm.executeQuery();

//                System.out.println(query);
                // process arraylist
                if (this.ordersList == null) {
                    this.ordersList = new ArrayList<>();
                } else {
                    this.ordersList.removeAll(this.ordersList);
                }

                // process
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    OrderDTO dto = new OrderDTO(orderID, orderDate, shipDate, status, accID);
                    this.ordersList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    // [GET] - get order's detail of an order
    // [IN]: orderID
    // [OUT]: void
    ArrayList<OrderDetailDTO> orderDetailList;

    public ArrayList<OrderDetailDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public void getOrderDetail(int orderID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // make connection
            con = DBUtils.makeConnection();

            if (con != null) {
                // query
                String query = "Select DetailId, OrderID, PID, PName, price, imgPath, quantity \n"
                        + "From OrderDetails, Plants\n"
                        + "Where OrderID=? and OrderDetails.FID = Plants.PID";
                // stm
                stm = con.prepareStatement(query);
                stm.setInt(1, orderID);
                // execute
                rs = stm.executeQuery();

                // process arraylist
                if (this.orderDetailList == null) {
                    this.orderDetailList = new ArrayList<>();
                } else {
                    this.orderDetailList.removeAll(orderDetailList);
                }

                // process
                while (rs.next()) {
                    int detailID = rs.getInt("DetailId");
                    int plantID = rs.getInt("PID");
                    String plantName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");
                    OrderDetailDTO dto = new OrderDetailDTO(detailID, orderID, plantID, plantName, price, imgPath, quantity);
                    this.orderDetailList.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    // [POST] - get order's detail of an order
    // [IN]: OrderId
    // [OUT]: num of changed row
    public int updateOrderStatus(int orderID, String status)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        int integerStatus;

        switch (status.trim()) {
            case "processing":
                integerStatus = 1;
                break;
            case "completed":
                integerStatus = 2;
                break;

            case "cancel":
                integerStatus = 3;
                break;

            default:
                nrow = -1;
                integerStatus = -1;

        }

        try {
            // make connection
            con = DBUtils.makeConnection();

            if (con != null && integerStatus >= 0) {
                // query
                String query = "Update [dbo].[Orders]\n"
                        + "Set [status] = ?\n"
                        + "Where [OrderID] = ?";
                // stm
                stm = con.prepareStatement(query);
                stm.setInt(1, integerStatus);
                stm.setInt(2, orderID);
                // execute
                nrow = stm.executeUpdate();
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return nrow;
    }

    // [INSERT ORDER]
    // [IN]: email, cart
    // [OUT]: boolean
    public boolean insertOrder(String email, HashMap<PlantDTO, Integer> cart)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                int accid = 0, orderid = 0;
                con.setAutoCommit(false); // off auto commit
                // GET ACCOUNT ID BY EMAIL - query
                String query = "Select accID\n"
                        + "From Accounts\n"
                        + "Where email Like ?";
                // make stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);

                // exe
                rs = stm.executeQuery();

                // process  
                if (rs.next()) {
                    accid = rs.getInt("accID");
                }

                // INSERT A NEW ORDER
                System.out.println("accountid: " + accid);
                Date date = new Date(System.currentTimeMillis());
                System.out.println("order date: " + date);

                // 2nd query
                query = "Insert into dbo.[Orders](OrdDate, status, AccID)\n"
                        + "Values (?, ?, ?)";
                // 2nd stm
                stm = con.prepareStatement(query);
                stm.setDate(1, date);
                stm.setInt(2, 1);
                stm.setInt(3, accid);

                // 2nd process
                int nrow = stm.executeUpdate();

                if (nrow == 0) {
                    return result;
                }

                // GET ORDER ID - THAT IS THE LEARGEST NUMBER
                query = "Select top 1 OrderID\n"
                        + "From Orders\n"
                        + "Order By OrderID DESC";

                // 3rd stm
                stm = con.prepareStatement(query);

                // process
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderid = rs.getInt("OrderID");
                }

                if (orderid == 0) {
                    return result;
                }
                // INSERT INTO ORDER DETAILS
                System.out.println("orderid: " + orderid);
                Set<PlantDTO> plants = cart.keySet();
                query = "Insert into OrderDetails([OrderID], [FID], [quantity])\n"
                        + "Values (?, ?, ?)";
                stm = con.prepareStatement(query);
                for (PlantDTO plant : plants) {
                    stm.setInt(1, orderid);
                    stm.setInt(2, plant.getId());
                    stm.setInt(3, cart.get(plant));

                    nrow = stm.executeUpdate();
                    if (nrow == 0) {
                        return result;
                    }
                }
                con.commit();
                con.setAutoCommit(true);
                result = true;
            }
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}
