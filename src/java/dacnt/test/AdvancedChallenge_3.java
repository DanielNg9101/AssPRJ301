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
public class AdvancedChallenge_3 {

    public static void main(String[] args) throws SQLException {

        try {
            String email = "dacng@gmail.com";
            String newPassword = "test' Delete [dbo].[Accounts] print'";
            String newPhone = "654321";
            String newFullname = "Nguyen Tam Dac";
            boolean isSuccess = AccountDAO.updateAccount(email, newPassword, newFullname, newPhone);
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
