/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Daniel NG
 */
public class DBUtils {

    public static Connection makeConnection()
            throws /*ClassNotFoundException*/ NamingException, SQLException {

        // 1. get current system file
        Context context = new InitialContext();
        // 2. get container context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        // 3. get DataSource
        DataSource ds = (DataSource) tomcatContext.lookup("PlantDB");
        // 4. get connection
        Connection con = ds.getConnection();
        
        return con;
        

//        // 1. load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        // 2. create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=PlantShop";
//        Connection con = DriverManager.getConnection(url, "sa", "2067");
//        return con;
    }
}
