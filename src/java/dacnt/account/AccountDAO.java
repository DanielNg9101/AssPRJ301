/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.account;

import dacnt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class AccountDAO implements Serializable {

    // [SINGLETON]
    private static AccountDAO dao;

    public static AccountDAO getInstance() {
        if (dao == null) {
            dao = new AccountDAO();
        }
        return dao;
    }

    // [GET A ACCOUNT]
    // [in]: email, password
    // [out]: a account in db
    public static AccountDTO getAccount(String email, String password)
            throws SQLException, NamingException {
        Connection con = null;
        AccountDTO acc = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]\n"
                        + "Where email Like ? And password Like ? COLLATE SQL_Latin1_General_CP1_CS_AS";
                // 3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                // 4. execute
                rs = stm.executeQuery();
                // process
                if (rs.next()) {
                    int accId = rs.getInt("accID");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String token = rs.getString("token");
                    acc = new AccountDTO(accId, email, password, fullname, status, phone, role, token);
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
        return acc;
    }

    public static ArrayList<AccountDTO> getAccounts()
            throws SQLException, NamingException {
        Connection con = null;
        ArrayList<AccountDTO> list = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]";

                // 3. create stm
                stm = con.createStatement();

                // 4. exe
                rs = stm.executeQuery(query);

                // 5. process
                while (rs.next()) {
                    int accId = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String token = rs.getString("token");
                    AccountDTO acc = new AccountDTO(accId, email, password, fullname, status, phone, role, token);
                    list.add(acc);
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
        return list;
    }

    // [SEARCH]
    // [IN]: email keyword
    // [OUT]: all account contain
    public static ArrayList<AccountDTO> searchAccounts(String keyword)
            throws SQLException, NamingException {
        Connection con = null;
        ArrayList<AccountDTO> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]\n"
                        + "Where email like ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, keyword);

                // 4. exe
                rs = stm.executeQuery();

                // 5. process
                while (rs.next()) {
                    int accId = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String token = rs.getString("token");
                    AccountDTO acc
                            = new AccountDTO(accId, email, password,
                                    fullname, status, phone, role, token);
                    list.add(acc);
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
        return list;
    }

    public static boolean updateAccountStatus(String email, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null && (status == 0 || status == 1)) {
                // 2. create query
                String query = "Update [dbo].[Accounts]\n"
                        + "Set [status] = ? \n"
                        + "Where [email] Like ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setInt(1, status);
                stm.setString(2, email);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    // [UPDATE BY USER]
    // [IN]
    // [OUT]: void
    public static boolean updateAccount(String email, String password, String newFullname, String newPhone)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Update [dbo].[Accounts]\n"
                        + "Set [password] = ?, [fullname] = ?, [phone] = ?\n"
                        + "Where [email] Like ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, password);
                stm.setString(2, newFullname);
                stm.setString(3, newPhone);
                stm.setString(4, email);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    // [UPDATE BY ADMIN]
    // [IN]: email, status, role
    // [OUT]: void
    public static boolean updateAccount(String email, int newStatus, int newRole)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Update [dbo].[Accounts]\n"
                        + "Set status = ?, role=?\n"
                        + "Where [email] Like ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setInt(1, newStatus);
                stm.setInt(2, newRole);
                stm.setString(3, email);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    // [DELETE ACCOUNT]
    // [IN]
    // [OUT]: void
    public static boolean deleteAccount(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Delete [dbo].[Accounts]\n"
                        + "Where [email] Like ?";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    public static AccountDTO getAccountByEmail(String email)
            throws SQLException, NamingException {
        Connection con = null;
        AccountDTO acc = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]\n"
                        + "Where email Like ? COLLATE SQL_Latin1_General_CP1_CS_AS";
                // 3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                // 4. execute
                rs = stm.executeQuery();
                // process
                if (rs.next()) {
                    int accId = rs.getInt("accID");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String token = rs.getString("token");
                    acc = new AccountDTO(accId, email, password, fullname, status, phone, role, token);
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
        return acc;
    }

    // [GET ACCOUNT]
    // [IN]: ACCID
    // [OUT]: ACCOUNT
    public static AccountDTO getAccountByID(int accID)
            throws SQLException, NamingException {
        Connection con = null;
        AccountDTO acc = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]\n"
                        + "Where accID = ?";
                // 3. create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, accID);
                // 4. execute
                rs = stm.executeQuery();
                // process
                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String token = rs.getString("token");
                    acc = new AccountDTO(accID, email, password, fullname, status, phone, role, token);
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
        return acc;
    }

    public static boolean insertAccount(String email, String password, String fullname, String phone, int status, int role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null && (status == 0 || status == 1)) {
                // 2. create query
                String query = "Insert Into [Accounts] (email, password, fullname, phone, status,  role)\n"
                        + "Values (?, ?, ?, ?, ?, ?)";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, phone);
                stm.setInt(5, status);
                stm.setInt(6, role);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    public static boolean addToken(String email, String token)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int nRow = 0;
        boolean result = false;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. create query
                String query = "Update [Accounts]\n"
                        + "Set token = ?\n"
                        + "Where email like ?\n";

                // 3. create stm
                stm = con.prepareStatement(query);
                stm.setString(1, token);
                stm.setString(2, email);

                // 4. exe
                nRow = stm.executeUpdate();

                // 5. process
                if (nRow > 0) {
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

    public static AccountDTO getAccountByToken(String token)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO account = null;

        try {
            // 1. connect
            con = DBUtils.makeConnection();
            if (con != null) {
                // 2. query
                String sql = "Select [accID], [email], [password], [fullname], [phone], [status], [role], [token]\n"
                        + "From [dbo].[Accounts]\n"
                        + "Where token Like ?";

                // 3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                // 4. execute
                rs = stm.executeQuery();
                // process
                if (rs.next()) {
                    int accId = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    account = new AccountDTO(accId, email, password,
                            fullname, status, phone, role, token);
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
        return account;
    }
}
