/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.test;

import dacnt.account.AccountDAO;
import dacnt.account.AccountDTO;
import dacnt.utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class TestConnection {

    public static void main(String[] args) throws SQLException {

        try {
            AccountDTO acc = AccountDAO.getAccount("dani@gmail.com", "123");
            if (acc != null) {
                if (acc.getRole() == 1) {
                    System.out.println("i am an admin");
                } else {
                    System.out.println("i am an user");
                }
            } else {
                System.out.println("login fail");
            }
            
            
            
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
