/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.dbConnection;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author prime
 */
public class DataBaseConnection {

    String driverName="com.mysql.jdbc.Driver";
    String userName="ubuntu";
    String password="rahasak";
    String connectionUrl="jdbc:mysql://localhost:3306/pharma";
    Connection connections;
    private Properties dbProperties;
    private String dataBaseMessage;

    public DataBaseConnection() {
      
        this.driverName = this.getDbProperties().getProperty("DBDriverName");
        this.userName = this.getDbProperties().getProperty("DBuser");
        this.password = this.getDbProperties().getProperty("DBpassword");
        this.connectionUrl = "jdbc:mysql://"+this.getDbProperties().getProperty("DBserverIP")+":"+this.getDbProperties().getProperty("DBserverPort") +"/"+this.getDbProperties().getProperty("DBName");
 
    }
    
    

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setConnection(Connection connection) {
        this.connections = connection;
    }

    public String getDataBaseMessage() {
        return dataBaseMessage;
    }

    public void setDataBaseMessage(String dataBaseMessage) {
        this.dataBaseMessage = dataBaseMessage;
    }
    
    

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driverName);
            try {
                connections = (Connection) DriverManager.getConnection(connectionUrl, userName, password);
                this.setConnection(connections);
            } catch (SQLException ex) {
                this.setDataBaseMessage(ex.getLocalizedMessage());
                System.out.println("SQLException-**->" + ex.getLocalizedMessage());
            }
        } catch (ClassNotFoundException ex) {
            this.setDataBaseMessage(ex.getLocalizedMessage());
            System.out.println("ClassNotFoundException-->" + ex.getLocalizedMessage());
        }
        return connections;
    }

    public void closeDbConnection() {
        try {
            this.getConnection().close();
        } catch (SQLException ex) {
            System.out.println(" closeDbConnection()-->" + ex.getMessage());
        }
    }

    public void commitData() {
        try {
            this.getConnection().commit();
        } catch (SQLException ex) {
            System.out.println("commitData()------>" + ex.getMessage());
        }
    }
     public void rollbackData() {
        try {
            this.getConnection().rollback();
        } catch (SQLException ex) {
            System.out.println("commitData()------>" + ex.getMessage());
        }
    }
    
     /*********Loading Properties File*******************/
    private Properties getDbProperties() {
        /******************  Getting the Property Value From Propeyty File ************************/
        dbProperties = new Properties();
        try {
            // InputStream inputStream = this.getClass().getClassLoader().getSystemResourceAsStream("config/settings.properties");

            String currentdir = System.getProperty("user.dir");


           //System.out.println("currentdir--->" + currentdir);

            File fs = new File(currentdir + "/src/" + "config.properties");

            InputStream inputStream = new FileInputStream(fs);

            dbProperties.load(inputStream);
            if (inputStream == null) {

                //System.out.println("File Not Found----------");

            }
            inputStream.close();
        } catch (IOException _IOExc) {
            //System.out.println("getDbProperties.IOException-->" + _IOExc.getMessage());
        } finally {
            return (dbProperties);
        }
    }
    
//      public void setValues() {
//
//        txtBrCode.setText(getDbProperties().getProperty("brCode"));
//        txtMainSrvrIp.setText(getDbProperties().getProperty("ipaddress"));
//        txtMainSrvrPort.setText(getDbProperties().getProperty("portno"));
//        txtPrllelPrintName.setText(getDbProperties().getProperty("printerNormal"));
//        txtPrllelPrintDpath.setText(getDbProperties().getProperty("printerNormalSystemPath"));
//        txtUsbPrintName.setText(getDbProperties().getProperty("printerNameUsb"));
//        txtUsbPrinDPath.setText(getDbProperties().getProperty("printerUsbSystemPath"));
//
//
//
//
//    }
}
