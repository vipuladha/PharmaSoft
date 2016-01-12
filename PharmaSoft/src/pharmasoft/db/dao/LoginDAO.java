/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pharmasoft.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import pharmasoft.db.util.DBAccess;
import pharmasoft.dbConnection.DataBaseConnection;

/**
 *
 * @author Vipula
 */
public class LoginDAO {

    Statement statement = null;
    DataBaseConnection dbConnection;
    DBAccess dataAccess;

    public LoginDAO() {
        dbConnection = new DataBaseConnection();
    }
    
    public String AuthenticateUserLogin(String username, String password) {
        if (!username.isEmpty() || !password.isEmpty()) {
            String dbPasswd = null;
            String dbUserName = null;
            dataAccess = new DBAccess();
            try {
                ResultSet rs = dataAccess.getResultSet("SELECT * FROM user WHERE UserName ='" + username + "'");
                while (rs.next()) {
                    dbPasswd = rs.getString("PassWord");
                    dbUserName = rs.getString("UserName");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "Login Failed";
            }
            if (username.equals(dbUserName)) {
                if (password.equalsIgnoreCase(dbPasswd)) {
                    return "SUCCESS";
                } else {
                    return "Invalid User Name Or PassWord";
                }
            } else {
                return "Invalid User Name Or PassWord";
            }
        } else {
            return "Please Insert UserName And PassWord";
        }
    }
}
