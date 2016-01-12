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
import pharmasoft.db.model.Product;
import pharmasoft.dbConnection.DataBaseConnection;

/**
 *
 * @author Vipula
 */
public class ProductDataAccess {    
    DataBaseConnection dbConnection;

    public ProductDataAccess() {
        dbConnection = new DataBaseConnection();

    }
    
    public boolean addNewProduct(Product product, String insertProduct, String insertProductDetails) throws SQLException {
        Connection dbCon = null;
        int pro_id = 0;
        PreparedStatement preStatInsertPro = null;
        PreparedStatement preStatInsertProDesc = null;
        try {
            dbCon = dbConnection.getConnection();
            dbCon.setAutoCommit(false);

            preStatInsertPro = dbCon.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS);
            preStatInsertPro.setString(1, product.getProductName());
            preStatInsertPro.setString(2, product.getProductDesc());
            preStatInsertPro.setInt(3, product.getSupplierId());
            preStatInsertPro.executeUpdate();
            ResultSet rs = preStatInsertPro.getGeneratedKeys();
            if (rs.next()) {
                pro_id = rs.getInt(1);
            }

            preStatInsertProDesc = dbCon.prepareStatement(insertProductDetails);
            preStatInsertProDesc.setInt(1, pro_id);
            preStatInsertProDesc.setInt(2, product.getBatchId());
            preStatInsertProDesc.setInt(3, product.getUnitSize());
            preStatInsertProDesc.setInt(4, product.getDiscount());
            preStatInsertProDesc.setLong(5, product.getRetailPrice());
            preStatInsertProDesc.setLong(6, product.getWholeSalePrice());
            preStatInsertProDesc.setDate(7, new java.sql.Date(product.getPurchaseDate().getTime()));
            preStatInsertProDesc.setDate(8, new java.sql.Date(product.getExpireDate().getTime()));
            preStatInsertProDesc.executeUpdate();

            dbCon.commit();

            System.out.println("Done!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            dbCon.rollback();
            return false;
        } finally {
            if (preStatInsertPro != null) {
                preStatInsertPro.close();
            }
            if (preStatInsertProDesc != null) {
                preStatInsertProDesc.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }

    }
    
      public boolean updateProduct(Product product, String updateProductSql, String updateProDetailsSql) throws SQLException {
          Connection dbCon = null;
        int pro_id = product.getProductId();
        PreparedStatement preStatUpdatePro = null;
        PreparedStatement preStatInsertProDesc = null;
        try {
            dbCon = dbConnection.getConnection();

            dbCon.setAutoCommit(false);

            preStatUpdatePro = dbCon.prepareStatement(updateProductSql);
            preStatUpdatePro.setString(1, product.getProductName());
            preStatUpdatePro.setString(2, product.getProductDesc());
            preStatUpdatePro.setInt(3, product.getSupplierId());
            preStatUpdatePro.setInt(4, pro_id);
            preStatUpdatePro.executeUpdate();
            
            preStatInsertProDesc = dbCon.prepareStatement(updateProDetailsSql);
            preStatInsertProDesc.setInt(1, pro_id);
            preStatInsertProDesc.setInt(2, product.getUnitSize());
            preStatInsertProDesc.setInt(3, product.getDiscount());
            preStatInsertProDesc.setLong(4, product.getRetailPrice());
            preStatInsertProDesc.setLong(5, product.getWholeSalePrice());
            preStatInsertProDesc.setDate(7, new java.sql.Date(product.getExpireDate().getTime()));
            preStatInsertProDesc.executeUpdate();

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
            if (preStatInsertProDesc != null) {
                preStatInsertProDesc.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
    }
    
    public boolean addNewBatchProduct(Product product, String updateProduct, String insertProductDetails) throws SQLException {
        Connection dbCon = null;
        int pro_id = product.getProductId();
        PreparedStatement preStatUpdatePro = null;
        PreparedStatement preStatInsertProDesc = null;
        try {
            dbCon = dbConnection.getConnection();
            dbCon.setAutoCommit(false);          
            preStatInsertProDesc = dbCon.prepareStatement(insertProductDetails);
            preStatInsertProDesc.setInt(1, pro_id);
            preStatInsertProDesc.setInt(2, product.getBatchId());
            preStatInsertProDesc.setInt(3, product.getUnitSize());
            preStatInsertProDesc.setInt(4, product.getDiscount());
            preStatInsertProDesc.setLong(5, product.getRetailPrice());
            preStatInsertProDesc.setLong(6, product.getWholeSalePrice());
            preStatInsertProDesc.executeUpdate();

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
            if (preStatInsertProDesc != null) {
                preStatInsertProDesc.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
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
