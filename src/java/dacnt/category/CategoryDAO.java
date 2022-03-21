/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.category;

import dacnt.account.AccountDTO;
import dacnt.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class CategoryDAO {

    private static CategoryDAO dao;

    public static CategoryDAO getInstance() {
        if (dao == null) {
            dao = new CategoryDAO();
        }
        return dao;
    }

    // [GET ALL CATEGORY]
    // [in]: 
    // [out]: categories
//    private ArrayList<CategoryDTO> categoriesList;
//
//    public ArrayList<CategoryDTO> getCategoriesList() {
//        return categoriesList;
//    }
    private HashMap<CategoryDTO, Boolean> categoriesList;

    public HashMap<CategoryDTO, Boolean> getCategoriesList() {
        return categoriesList;
    }

    public void getCategories()
            throws SQLException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "SELECT [CateID]\n"
                        + "      ,[CateName]\n"
                        + "  FROM [dbo].[Categories]";

                // 3. create statement
                stm = con.createStatement();

                // 4. execute
                rs = stm.executeQuery(sql);

                // process
//                if (this.categoriesList == null) {
//                    this.categoriesList = new ArrayList<>();
//                } else {
//                    this.categoriesList.removeAll(this.categoriesList);
//                }
                if (this.categoriesList == null) {
                    this.categoriesList = new HashMap<>();
                } else {
                    this.categoriesList.clear();
                }

                while (rs.next()) {
                    int cateId = rs.getInt("CateID");
                    String cateName = rs.getString("CateName");
                    if (isUsed(cateId)) {
                        this.categoriesList.put(
                                new CategoryDTO(cateId, cateName), true);
                    } else {
                        this.categoriesList.put(
                                new CategoryDTO(cateId, cateName), false);
                    }
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

    public boolean isUsed(int cateId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = true;
        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "SELECT CateID, CateName\n"
                        + "FROM [dbo].[Categories]\n"
                        + "WHERE CateID NOT IN (SELECT CateID\n"
                        + "                     FROM [dbo].[Plants])\n"
                        + "AND CateID = ?";

                // 3. create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, cateId);

                // 4. execute
                rs = stm.executeQuery();

                // process
                if (rs.next()) {
                    result = false;
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
        return result;
    }

    // [POST]
    // [IN]: category name
    // [OUT]: boolean
    public boolean insertCategory(String cateName)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "INSERT INTO [dbo].[Categories]\n"
                        + "           ([CateName])\n"
                        + "     VALUES\n"
                        + "           (?)";

                stm = con.prepareStatement(query);

                stm.setString(1, cateName);

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
    // [IN]: category ID
    // [OUT]: boolean
    public boolean deleteCategory(int cateID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "DELETE FROM [dbo].[Categories]\n"
                        + "     WHERE CateID = ?";

                stm = con.prepareStatement(query);

                stm.setInt(1, cateID);

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

    // [UDDATE]
    // [IN]: category ID
    // [OUT]: boolean
    public boolean updateCategory(int cateID, String newCatename)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int nrow = 0;
        boolean result = false;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String query = "UPDATE [dbo].[Categories]\n"
                        + "     SET CateName = ?\n"
                        + "     WHERE CateID = ?";

                stm = con.prepareStatement(query);

                stm.setString(1, newCatename);
                stm.setInt(2, cateID);

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
