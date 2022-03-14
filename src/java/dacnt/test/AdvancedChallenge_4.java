/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.test;

import dacnt.account.AccountDAO;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class AdvancedChallenge_4 {

    public static void main(String[] args) throws SQLException {

        try {
            String email = "test@gmail.com";
            String password = "test";
            String phone = "65321";
            String fullname = "tester";
            int status = 0;
            int role = 1;
            
            boolean isSuccess = AccountDAO.insertAccount(email, password, fullname, phone, status, role);
            if (isSuccess) {
                System.out.println("Success");
            } else {
                System.out.println("Update failed");
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
