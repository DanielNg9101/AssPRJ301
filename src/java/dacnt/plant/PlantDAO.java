/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.plant;

import dacnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class PlantDAO implements Serializable {

    //[SINGLETON]
    private static PlantDAO dao = null;

    public static PlantDAO getInstance() {
        if (dao == null) {
            dao = new PlantDAO();
        }
        return dao;
    }

    private ArrayList<PlantDTO> plants;

    public ArrayList<PlantDTO> getPlants() {
        return plants;
    }

    // [SEARCH]
    // [IN]: keyword, search by
    // [OUT]: list
    public void searchPlantsAdmin(String keyword, String searchby)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (this.plants == null) {
            this.plants = new ArrayList<>();
        } else {
            // create new dao after each seach so that, we don't need to remove all elements of last plants arr
            this.plants.removeAll(plants);
        }
        try {
            //1. make connection

            con = DBUtils.makeConnection();
//            System.out.println(con);
            if (con != null && searchby != null) {
                // 2. query string
                String query = "Select PID, PName, price, imgPath, description, "
                        + "status, Plants.CateID as 'CateID', CateName\n"
                        + "From Plants join Categories on Plants.CateID = Categories.CateID\n";

                if (searchby.equalsIgnoreCase("byname")) {
                    query = query + "Where Plants.PName Like ?";
                } else {
                    query = query + "Where CateName Like ?";
                }
                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + keyword + "%");

                //4. execute query
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    PlantDTO plant = new PlantDTO(id, name, price, imgPath, description, status, cateid, catename);
                    this.plants.add(plant);

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

        // [SEARCH]
    // [IN]: keyword, search by
    // [OUT]: list
    public void searchPlants(String keyword, String searchby)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (this.plants == null) {
            this.plants = new ArrayList<>();
        } else {
            // create new dao after each seach so that, we don't need to remove all elements of last plants arr
            this.plants.removeAll(plants);
        }
        try {
            //1. make connection

            con = DBUtils.makeConnection();
//            System.out.println(con);
            if (con != null && searchby != null) {
                // 2. query string
                String query = "Select PID, PName, price, imgPath, description, "
                        + "status, Plants.CateID as 'CateID', CateName\n"
                        + "From Plants join Categories on Plants.CateID = Categories.CateID\n";

                if (searchby.equalsIgnoreCase("byname")) {
                    query = query + "Where Plants.PName Like ? and status = 1";
                } else {
                    query = query + "Where CateName Like ? and status = 1";
                }
                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + keyword + "%");

                //4. execute query
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    PlantDTO plant = new PlantDTO(id, name, price, imgPath, description, status, cateid, catename);
                    this.plants.add(plant);

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
    
    // [GET]: single plant base on plant id
    // [IN]: plantid
    // [OUT]: plantdto
    public PlantDTO getPlant(int plantID)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        PlantDTO plant = null;

        try {
            //1. make connection

            con = DBUtils.makeConnection();
//            System.out.println(con);
            if (con != null) {
                // 2. query string
                String query = "Select PID, PName, price, imgPath, description, "
                        + "status, Plants.CateID as 'CateID', CateName\n"
                        + "From Plants join Categories on Plants.CateID = Categories.CateID\n"
                        + "Where PID = ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setInt(1, plantID);

                //4. execute query
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    plant = new PlantDTO(id, name, price, imgPath,
                            description, status, cateid, catename);

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
        return plant;
    }

    // [POST]
    // [IN]: plant info
    // [OUT]: boolean
    public boolean insertPlant(String pName, int price, String imgPath,
            String description, int status, int cateID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "insert into Plants([PName], [price], "
                        + "[imgPath], [description], [status], [CateID])\n"
                        + "values (?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(query);

                stm.setString(1, pName);
                stm.setInt(2, price);
                stm.setString(3, imgPath);
                stm.setString(4, description);
                stm.setInt(5, status);
                stm.setInt(6, cateID);

                nrow = stm.executeUpdate();

                if (nrow > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // [POST]
    // [IN]: plant info
    // [OUT]: boolean
    public boolean updatePlant(int plantID, String pName, int price, String imgPath,
            String description, int status, int cateID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "Update Plants\n"
                        + "Set [PName] = ?, [price] = ?, "
                        + "[imgPath] = ?, [description] = ?, "
                        + "[status] = ?, [CateID] = ?\n"
                        + "Where PID = ?";

                stm = con.prepareStatement(query);

                stm.setString(1, pName);
                stm.setInt(2, price);
                stm.setString(3, imgPath);
                stm.setString(4, description);
                stm.setInt(5, status);
                stm.setInt(6, cateID);
                stm.setInt(7, plantID);

                nrow = stm.executeUpdate();

                if (nrow > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    // [DELETE]
    // [IN]: plant info
    // [OUT]: boolean
    public boolean deletePlant(int plantID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "Update Plants\n"
                        + "Set status = 0"
                        + "Where PID = ?";

                stm = con.prepareStatement(query);

                stm.setInt(1, plantID);

                nrow = stm.executeUpdate();

                if (nrow > 0) {
                    result = true;
                }

            }
        } finally {
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
