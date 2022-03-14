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
public class AdvancedChallenge_1 {

    public static void main(String[] args) throws SQLException {

        try {
            List<AccountDTO> list = new ArrayList<>();
            list = AccountDAO.getAccounts();
            if (list.size() > 0) {
                for (AccountDTO acc : list) {
                    System.out.println(acc);
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
