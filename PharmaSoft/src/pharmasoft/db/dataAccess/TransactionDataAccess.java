/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.dataAccess;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import pharmasoft.db.dao.CommonDAO;
import pharmasoft.db.dao.ProductDAO;
import pharmasoft.db.model.Product;
import pharmasoft.db.model.RetailSale;
import pharmasoft.db.model.WholeSale;
import pharmasoft.db.proxyClasses.ProductBatchProxy;
import pharmasoft.db.proxyClasses.TransDetailsProxy;
import pharmasoft.db.proxyClasses.TransPurchDetailsProxy;
import pharmasoft.dbConnection.DataBaseConnection;

/**
 *
 * @author Vipula
 */
public class TransactionDataAccess {
    
    DataBaseConnection dbConnection;
    CommonDAO commonDAO;

    public TransactionDataAccess() {
        dbConnection = new DataBaseConnection();
        commonDAO = new CommonDAO();
    }
    
    public boolean insertTransaction(long grandTotal, List<TransDetailsProxy> transDetails,
            String insertRetSale, String insertRetSaleDetail, int cusId) throws SQLException {
        Connection dbCon = null;
        PreparedStatement preStatInsertRetS = null;
        PreparedStatement preStatinsertRetSDetails = null;
        try {
            int trnId = 0;
            dbCon = dbConnection.getConnection();

            dbCon.setAutoCommit(false);

            preStatInsertRetS = dbCon.prepareStatement(insertRetSale, Statement.RETURN_GENERATED_KEYS);
            preStatInsertRetS.setLong(1, grandTotal);
            preStatInsertRetS.setLong(2, 0);
            preStatInsertRetS.setDate(3, new java.sql.Date(new Date().getTime()));
            if (cusId > 0)
                 preStatInsertRetS.setInt(4, cusId);
            preStatInsertRetS.executeUpdate();
            ResultSet rs = preStatInsertRetS.getGeneratedKeys();
            if (rs.next()) {
                trnId = rs.getInt(1);
            }

            for (Iterator<TransDetailsProxy> it = transDetails.iterator(); it.hasNext();) {
                TransDetailsProxy transProxy = it.next();
                updateInventory(transProxy.getProId(), transProxy.getBatchId(), transProxy.getQuentity(), false);
                
                preStatinsertRetSDetails = dbCon.prepareStatement(insertRetSaleDetail);
                preStatinsertRetSDetails.setLong(1, transProxy.getUnitPrice());
                preStatinsertRetSDetails.setInt(2, transProxy.getQuentity());
                preStatinsertRetSDetails.setLong(3, transProxy.getSubTotal());
                preStatinsertRetSDetails.setLong(4, trnId);
                preStatinsertRetSDetails.setLong(5, transProxy.getProId());
                preStatinsertRetSDetails.executeUpdate();
            }

            dbCon.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            dbCon.rollback();
            return false;
        } finally {
            if (preStatInsertRetS != null) {
                preStatInsertRetS.close();
            }
            if (preStatinsertRetSDetails != null) {
                preStatinsertRetSDetails.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
    }
    
    public boolean insertPurchaseTrans(TransPurchDetailsProxy proxy, List<TransDetailsProxy> transDetails,
            String insertPurch, String insertPurchDetail) throws SQLException {
        Connection dbCon = null;
        PreparedStatement preStatInsPurch = null;
        PreparedStatement preStatInsPurchDetails = null;
        try {
            int pOrderId = 0;
            dbCon = dbConnection.getConnection();
            dbCon.setAutoCommit(false);

            preStatInsPurch = dbCon.prepareStatement(insertPurch, Statement.RETURN_GENERATED_KEYS);
            preStatInsPurch.setLong(1, proxy.getGrandTotal());
            preStatInsPurch.setLong(2, 0);
            preStatInsPurch.setDate(3, new java.sql.Date(new Date().getTime()));
            preStatInsPurch.setString(4, proxy.getPayType());
            preStatInsPurch.setLong(5, proxy.getChqNo());
            if (proxy.getChqDate() != null)
                preStatInsPurch.setDate(6, new java.sql.Date(proxy.getChqDate().getTime()));
            else
                 preStatInsPurch.setDate(6, null);
            preStatInsPurch.executeUpdate();
            ResultSet rs = preStatInsPurch.getGeneratedKeys();
            if (rs.next()) {
                pOrderId = rs.getInt(1);
            }

            for (Iterator<TransDetailsProxy> it = transDetails.iterator(); it.hasNext();) {
                TransDetailsProxy transProxy = it.next();       
                updateInventory(transProxy.getProId(), transProxy.getBatchId(), transProxy.getQuentity(), true);
                
                preStatInsPurchDetails = dbCon.prepareStatement(insertPurchDetail);
                preStatInsPurchDetails.setLong(1, transProxy.getUnitPrice());
                preStatInsPurchDetails.setInt(2, transProxy.getQuentity());
                preStatInsPurchDetails.setLong(3, transProxy.getSubTotal());
                preStatInsPurchDetails.setLong(4, pOrderId);
                preStatInsPurchDetails.setLong(5, transProxy.getProId());
                preStatInsPurchDetails.executeUpdate();
            }

            dbCon.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            dbCon.rollback();
            return false;
        } finally {
            if (preStatInsPurch != null) {
                preStatInsPurch.close();
            }
            if (preStatInsPurchDetails != null) {
                preStatInsPurchDetails.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
    }
    
    public boolean updateInventory(int proId, int batchId, int quantity, boolean isStockIn) throws SQLException {
        Connection dbCon = null;
        int upQuantity = 0;
        String updateSql = "UPDATE product_details SET unit_in_stock = ? WHERE pro_id = ? and batch_id = ?";
        PreparedStatement preStatUpdatePro = null;
        try {
            dbCon = dbConnection.getConnection();
            Product product = commonDAO.getProductDetails(proId, batchId);
            if (isStockIn) {
                upQuantity = product.getUnitInStock() + quantity;
            } else if (product.getUnitInStock() > 0) {
                upQuantity = product.getUnitInStock() - quantity;
            }
            
            dbCon.setAutoCommit(false);
            preStatUpdatePro = dbCon.prepareStatement(updateSql);
            preStatUpdatePro.setInt(1, upQuantity);
            preStatUpdatePro.setInt(2, proId);
            preStatUpdatePro.setInt(3, batchId);
            preStatUpdatePro.executeUpdate();

            dbCon.commit();

            System.out.println("Done!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            dbCon.rollback();
            return false;
        } finally {
            if (preStatUpdatePro != null) {
                preStatUpdatePro.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
    }
    
    public Vector<WholeSale> getOutStandingData(String sql, int cusId) {
        ResultSet resultSet = null;
        Vector<WholeSale> list = new Vector<WholeSale>();
        WholeSale proxy;
        try {
            resultSet = getResultSet(sql);
            while (resultSet.next()) {
                proxy = new WholeSale();
                proxy.setInvoiceId(resultSet.getInt("invoice_id"));
                proxy.setTotalAmount(resultSet.getInt("total_amount"));
                proxy.setDiscount(resultSet.getInt("discount"));
                proxy.setReciptDate(resultSet.getDate("recipt_date"));

                list.add(proxy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public Map<String, Object> getTransactionsByDate(String sql, int pageSize, int startNo) {
        ResultSet resultSet = null;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Vector<RetailSale> list = new Vector<RetailSale>();
        RetailSale proxy;
        int rowsAdded = 0;
        int totalRecords = 0;
        try {
            resultSet = getResultSet(sql);
            if(startNo > 1)
                resultSet.absolute(startNo - 1);
            while (resultSet.next()) {
                if (pageSize > 0 && pageSize == rowsAdded) 
                    break;
                proxy = new RetailSale();
                proxy.setReciptId(resultSet.getInt("recipt_id"));
                proxy.setTotalAmount(resultSet.getInt("total_amount"));
                proxy.setDiscount(resultSet.getInt("discount"));
                proxy.setReciptDate(resultSet.getDate("recipt_date"));

                list.add(proxy);
                rowsAdded++;
            }
            resultSet.last();
            totalRecords = resultSet.getRow();
            resultSet.beforeFirst();
            ResultSet rs = getResultSet("SELECT SUM(total_amount) as totalvalue FROM retail_sale");
            long totalValue = 0;
            while (rs.next()) {
                totalValue = rs.getLong("totalvalue");
            }
            dataMap.put("transDetails", list);
            dataMap.put("totalRecords", totalRecords);
            dataMap.put("totalValue", totalValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataMap;
    }
    
    public Map<String, Object> getWSTransactionsByDate(String sql, int pageSize, int startNo) {
        ResultSet resultSet = null;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Vector<WholeSale> list = new Vector<WholeSale>();
        WholeSale proxy;
        String cusFirstName;
        String cusLastName;
        int rowsAdded = 0;
        int totalRecords = 0;
        try {
            resultSet = getResultSet(sql);
            if (startNo > 1) {
                resultSet.absolute(startNo - 1);
            }
            while (resultSet.next()) {
                if (pageSize > 0 && pageSize == rowsAdded) {
                    break;
                }
                proxy = new WholeSale();
                proxy.setInvoiceId(resultSet.getInt("invoice_id"));
                proxy.setTotalAmount(resultSet.getInt("total_amount"));
                proxy.setDiscount(resultSet.getInt("discount"));
                proxy.setReciptDate(resultSet.getDate("recipt_date"));
                cusFirstName = resultSet.getString("first_name");
                cusLastName = resultSet.getString("last_name");
                String cutomerName = "Dr. "+ (cusFirstName != null ? cusFirstName : "") + " " + (cusLastName != null ? cusLastName : "");
                proxy.setCustomerName(cutomerName);

                list.add(proxy);
                rowsAdded++;
            }
            resultSet.last();
            totalRecords = resultSet.getRow();
            resultSet.beforeFirst();
            ResultSet rs = getResultSet("SELECT SUM(total_amount) as totalvalue FROM whole_sale");
            long totalValue = 0;
            while (rs.next()) {
                totalValue = rs.getLong("totalvalue");
            }
            dataMap.put("transDetails", list);
            dataMap.put("totalRecords", totalRecords);
            dataMap.put("totalValue", totalValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataMap;
    }
    
    public Vector<RetailSale> getTransactions(String sql) {
        ResultSet resultSet = null;
        Vector<RetailSale> list = new Vector<RetailSale>();
        RetailSale proxy;
        try {
            resultSet = getResultSet(sql);
            while (resultSet.next()) {
                proxy = new RetailSale();
                proxy.setReciptId(resultSet.getInt("recipt_id"));
                proxy.setTotalAmount(resultSet.getInt("total_amount"));
                proxy.setDiscount(resultSet.getInt("discount"));
                proxy.setReciptDate(resultSet.getDate("recipt_date"));

                list.add(proxy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ResultSet getResultSet(String sql) {
        Statement statement = null;
        try {
            statement = dbConnection.getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("sql error=============>" + ex);
            return null;
        }

    }
}
