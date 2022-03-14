/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.test;

import dacnt.account.AccountDAO;
import dacnt.account.AccountDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Daniel NG
 */
public class AdvancedChallenge_2 {

    public static void main(String[] args) throws SQLException {

        try {
            String email = "dacng@gmail.com";
            int status = 0;
            boolean isSuccess = AccountDAO.updateAccountStatus(email, 1);
            if (isSuccess) {
                System.out.println("status of account " + email + " has been updated");
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
