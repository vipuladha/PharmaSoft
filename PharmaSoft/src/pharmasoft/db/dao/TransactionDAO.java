/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import pharmasoft.db.dataAccess.TransactionDataAccess;
import pharmasoft.db.model.Product;
import pharmasoft.db.model.RetailSale;
import pharmasoft.db.model.WholeSale;
import pharmasoft.db.proxyClasses.TransDetailsProxy;
import pharmasoft.db.proxyClasses.TransPurchDetailsProxy;

/**
 *
 * @author Vipula
 */
public class TransactionDAO {
    private TransactionDataAccess transDataAccess;

    public TransactionDAO() {
        transDataAccess = new TransactionDataAccess();

    }
    
    public String insertTransaction(long grandTotal, List<TransDetailsProxy> transDetails) throws SQLException {
        String insertRetSale = "INSERT INTO transaction(tran_id, total_amount, discount, recipt_date, trn_type, status) VALUES(?,?,?,?,?,?)";
        String insertRetSaleDetails = "INSERT INTO transaction_details(trns_detail_id, unit_price, quantity, sub_total, trans_id, pro_id) VALUES(?,?,?,?,?,?)";

        return transDataAccess.insertTransaction(grandTotal, transDetails, insertRetSale, insertRetSaleDetails, "RS", "SENT", -1);
    }
    
//    public boolean insertTransaction(long grandTotal, List<TransDetailsProxy> transDetails) throws SQLException {
//        String insertRetSale = "INSERT INTO retail_sale(total_amount, discount, recipt_date) VALUES(?,?,?)";
//        String insertRetSaleDetails = "INSERT INTO retail_sale_details(unit_price, quantity, sub_total, recipt_id, pro_id) VALUES(?,?,?,?,?)";
//
//        return transDataAccess.insertTransaction(grandTotal, transDetails, insertRetSale, insertRetSaleDetails, -1);
//    }
    
    public boolean inserWholeSaleTrans(long grandTotal, List<TransDetailsProxy> transDetails, int cusId) throws SQLException {
        String insertRetSale = "INSERT INTO whole_sale(total_amount, discount, recipt_date, customer_id) VALUES(?,?,?,?)";
        String insertRetSaleDetails = "INSERT INTO whole_sale_details(unit_price, quantity, sub_total, invoice_id, pro_id) VALUES(?,?,?,?,?)";

//        return transDataAccess.insertTransaction(grandTotal, transDetails, insertRetSale, insertRetSaleDetails, "WS", "SENT", cusId);
        return false;
    }
    
     public boolean insertPurchaseTrans(TransPurchDetailsProxy proxy, List<TransDetailsProxy> transDetails) throws SQLException {
        String insertPuchase = "INSERT INTO purchase_order(total_amount, discount, order_date, payment_type, chq_no, chq_date) "
                + "VALUES(?,?,?,?,?,?)";
        String insertPuchaseDetails = "INSERT INTO purchase_order_details(unit_price, quantity, sub_total, porder_id, pro_id) "
                + "VALUES(?,?,?,?,?)";

        return transDataAccess.insertPurchaseTrans(proxy, transDetails, insertPuchase, insertPuchaseDetails);
    }
     
     public Vector<WholeSale> getOsDataByCusId(int cusId, String osDate){
        String osDataSql = "SELECT * FROM whole_sale where customer_id = " + cusId + " AND recipt_date < '" + osDate + "'";
        
       return transDataAccess.getOutStandingData(osDataSql, cusId);  
     }
     
    public Map<String, Object> getTransactionsByDate(Date startdate, Date endDate, int pageSize, int startNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sqlStr = null;
        sqlStr = new StringBuffer("SELECT * FROM retail_sale rs WHERE ");
        if (startdate != null && endDate == null) {
            sqlStr.append("rs.recipt_date='" + sdf.format(startdate) + "' ");
        } else if (startdate != null && endDate != null) {
            sqlStr.append("rs.recipt_date > '" + sdf.format(startdate) + "' AND rs.recipt_date < '" + sdf.format(endDate) + "' ");
        }
            
        return transDataAccess.getTransactionsByDate(sqlStr.toString(), pageSize, startNo);
    }
    
     public Map<String, Object> getWSTransactionsByDate(int cusId, Date startdate, Date endDate, int pageSize, int startNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sqlStr = null;
        sqlStr = new StringBuffer("SELECT * FROM whole_sale ws JOIN customer c on ws.customer_id = c.customer_id WHERE ");
        if (cusId > 0) {
            sqlStr.append("ws.customer_id='" + cusId + "' ");
        } else if (startdate != null && endDate == null) {
            sqlStr.append("ws.recipt_date='" + sdf.format(startdate) + "' ");
        } else if (startdate != null && endDate != null) {
            sqlStr.append("ws.recipt_date > '" + sdf.format(startdate) + "' AND ws.recipt_date < '" + sdf.format(endDate) + "' ");
        }

        return transDataAccess.getWSTransactionsByDate(sqlStr.toString(), pageSize, startNo);
    }
     
     public Vector<RetailSale> getTransactions() {
         StringBuffer sqlStr = null;
         sqlStr = new StringBuffer("SELECT * FROM retail_sale rs ");

         return transDataAccess.getTransactions(sqlStr.toString());
     }
}
