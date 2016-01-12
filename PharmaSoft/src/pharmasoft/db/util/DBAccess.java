/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.util;

import pharmasoft.dbConnection.DataBaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import pharmasoft.ui.FrmMainForm;

/**
 *
 * @author gayan
 */
public class DBAccess {

    Statement statement = null;
    DataBaseConnection dbConnection;
    private ResultSet rs;
    FrmMainForm frmMainForm;

    public FrmMainForm getFrmMainForm() {
        return frmMainForm;
    }

    private void setFrmMainForm(FrmMainForm frmMainForm) {
        this.frmMainForm = frmMainForm;
    }



    public DBAccess() {
      
        dbConnection = new DataBaseConnection();
    }

    public String[][] getResultsStringArray(String query) {
        System.out.println("getResultsStringArray(---->>>>>");
        ResultSet resultList = null;

        try {
            dbConnection = new DataBaseConnection();
            statement = dbConnection.getConnection().createStatement();

            statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("SQLException exQQQQQQQQQQQQ");
            ex.printStackTrace();

        }

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return null;
//        if (resultList== null) {
//
//            return null;
//        }
//
//        int numberOfRecords = resultList.size();
//        int numberOfColumns = resultList.get(0).length;
//
////        System.out.println("***********************************");
////        System.out.println("Number of Columns " + numberOfColumns);
////        System.out.println("Number of Rows " + numberOfRecords);
////        System.out.println("***********************************");
//
//        String[][] stringRecordArrayList = new String[numberOfRecords][numberOfColumns];
//        int i = 0;
//
//        for (Object[] recordList : resultList) {
//            for (int j = 0; j < numberOfColumns; j++) {
//                stringRecordArrayList[i][j] = recordList[j].toString();
////                System.out.print(stringRecordArrayList[i][j] + " ," + "----------" + i + "+++++++++++" + j);
//            }
//            i++;
////            System.out.println("####################################");
////            System.out.println("");
//        }
//        return stringRecordArrayList;
    }

    public ResultSet getResultSet(String sql) {
//        ArrayList al = new ArrayList();
        try {
            statement = dbConnection.getConnection().createStatement();
            rs = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println("SQLException--->" + ex.getLocalizedMessage());
            ex.printStackTrace();
            return null;

        }
        if (rs != null) {
            return rs;
        } else {
            return null;
        }
    }

//    public boolean performDBBackUp() {

//        String currentdir = System.getProperty("user.dir");
//     
//       
//        String date = DateHelper.format(new Date(), "yyyy-MM-dd");
//        try {
//
//            String serverIp=FrmMainForm.getDbProperties().getProperty("DBserverIP");
//
//            System.out.println("Server--->"+serverIp);
//            System.out.println("Current Dir---->"+currentdir);
//
//            Process p = Runtime.getRuntime().exec("mysqldump -h "+serverIp+" --user=ubuntu --password=rahasak --opt billing -r " + currentdir + "back_" + date + ".sql");
//            if (p.getInputStream().read() == -1) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
}
