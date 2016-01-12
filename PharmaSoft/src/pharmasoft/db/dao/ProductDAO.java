/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import pharmasoft.db.dataAccess.ProductDataAccess;
import pharmasoft.db.model.Product;
import pharmasoft.db.proxyClasses.ProductBatchProxy;
import pharmasoft.db.proxyClasses.TransDetailsProxy;
import pharmasoft.ui.FrmSearchBox;

/**
 *
 * @author Vipula
 */
public class ProductDAO {

    private ProductDataAccess productDataAccess;

    public ProductDAO() {
        productDataAccess = new ProductDataAccess();

    }

    public boolean addNewProduct(Product product) throws SQLException {
        String insertProduct = "INSERT INTO product(pro_name, pro_description, supplier_id) VALUES(?,?,?)";
        String insertProductDetails = "INSERT INTO product_details(pro_id, batch_id, unit_size, discount, retail_price, "
                + "whole_sale_price, purchase_date, expire_date) VALUES(?,?,?,?,?,?,?,?)";

        return productDataAccess.addNewProduct(product, insertProduct, insertProductDetails);
    }
    
    public boolean updateProduct(Product product) throws SQLException {
        String updateProductSql = "UPDATE product SET pro_name =?, pro_description =?, supplier_id =? WHERE pro_id = ?";
        String updateProDetailsSql = "UPDATE product_details SET pro_id =?, unit_size =?, discount =?, retail_price=?, "
                + "whole_sale_price =?, expire_date =? WHERE pro_id = ?";

        return productDataAccess.updateProduct(product, updateProductSql, updateProDetailsSql);
    }

    public boolean addNewBatchProduct(Product product) throws SQLException {
        String updateProduct = "UPDATE product SET pro_name =?, pro_description =?, supplier_id =? WHERE pro_id = ?";
        String insertProductDetails = "INSERT INTO product_details(pro_id, batch_id, unit_size, discount, retail_price, whole_sale_price) "
                + "VALUES(?,?,?,?,?,?)";
        int nextBatchNo = getNextBatchId(product.getProductId());
        product.setBatchId(nextBatchNo);

        return productDataAccess.addNewBatchProduct(product, updateProduct, insertProductDetails);
    }
    
    public Vector<ProductBatchProxy> getProductByProId(int proId) {
        String sql = null;
        ResultSet resultSet = null;
        Vector<ProductBatchProxy> batchPro = new Vector<ProductBatchProxy>();
        ProductBatchProxy proxy;
        try {
            sql = "SELECT p.pro_id, pd.batch_id, p.pro_name, pd.retail_price, pd.whole_sale_price, pd.unit_in_stock, pd.expire_date  "
                    + "FROM product p JOIN product_details pd ON p.pro_id = pd.pro_id  WHERE p.pro_id='" + proId + "'";
            resultSet = productDataAccess.getResultSet(sql);
            while (resultSet.next()) {
                proxy = new ProductBatchProxy();
                proxy.setProductId(resultSet.getInt("pro_id"));
                proxy.setBatchId(resultSet.getInt("batch_id"));
                proxy.setProductName(resultSet.getString("pro_name"));
                proxy.setRetailPrice(resultSet.getLong("retail_price"));
                proxy.setWholeSalePrice(resultSet.getLong("whole_sale_price"));
                proxy.setUnitInStock(resultSet.getInt("unit_in_stock"));
//                proxy.setExpireDate(resultSet.getDate("expire_date"));
                
                batchPro.add(proxy);
            }
//            return batchPro;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return batchPro;
    }
    
    public Vector<TransDetailsProxy> getProDetailsByInvoiceId(int invoiceId) {
        String sql = null;
        ResultSet resultSet = null;
        Vector<TransDetailsProxy> proDetails = new Vector<TransDetailsProxy>();
        TransDetailsProxy proxy;
        try {
            sql = "SELECT * FROM whole_sale ws JOIN whole_sale_details wsd ON ws.invoice_id = wsd.invoice_id "
                    + "JOIN product p ON wsd.pro_id = p.pro_id JOIN product_details pd ON p.pro_id = pd.pro_id "
                    + "WHERE wsd.invoice_id='" + invoiceId + "'";
            resultSet = productDataAccess.getResultSet(sql);
            while (resultSet.next()) {
                proxy = new TransDetailsProxy();
                proxy.setInvoiceId(resultSet.getInt("invoice_id"));
                proxy.setProId(resultSet.getInt("pro_id"));
                proxy.setUnitSize(resultSet.getInt("unit_size"));
                proxy.setProductName(resultSet.getString("pro_name"));
                proxy.setUnitPrice(resultSet.getLong("whole_sale_price"));
                proxy.setQuentity(resultSet.getInt("quantity"));
                proxy.setSubTotal(resultSet.getLong("sub_total"));
                proxy.setGrandTotal(resultSet.getLong("total_amount"));
                proxy.setTimeStamp(resultSet.getTimestamp("time_stamp"));

                proDetails.add(proxy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return proDetails;
    }

     public Map<String, Object> getProDetailsBySuppId(boolean isAllSearch, int suppId, int proId, int pageSize, int startNo, int endNo) {
        StringBuffer sqlStr = null;
        ResultSet resultSet = null;
        boolean isWhere = false;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Vector<ProductBatchProxy> batchPro = new Vector<ProductBatchProxy>();
        ProductBatchProxy proxy;
        try {
            sqlStr = new StringBuffer("SELECT p.pro_id, pd.batch_id, p.pro_name, pd.purchase_value, pd.retail_price, "
                    + "pd.whole_sale_price, pd.unit_size, pd.unit_in_stock, pd.expire_date, s.supplier_name "
                    + "FROM product p JOIN product_details pd ON p.pro_id = pd.pro_id JOIN supplier s ON p.supplier_id = s.supplier_id ");
            
            if (!isAllSearch) {
                sqlStr.append("WHERE ");
                if (suppId > 0) {
                    sqlStr.append(" p.supplier_id='" + suppId + "' ");
                    isWhere = true;
                } 
                
                if (proId > 0){
                    if (isWhere)
                        sqlStr.append("AND ");
                    sqlStr.append(" p.pro_id='" + proId + "' ");
                    isWhere = true;
                }
            }
            sqlStr.append("ORDER BY p.pro_name ");
            
            int rowsAdded = 0;
            int totalRecords = 0;
            
            resultSet = productDataAccess.getResultSet(sqlStr.toString());
            if(startNo > 1)
                resultSet.absolute(startNo - 1);
            while (resultSet.next()) {
                if (pageSize > 0 && pageSize == rowsAdded) 
                    break;
                              
                proxy = new ProductBatchProxy();
                proxy.setProductId(resultSet.getInt("pro_id"));
                proxy.setBatchId(resultSet.getInt("batch_id"));
                proxy.setProductName(resultSet.getString("pro_name"));
                proxy.setSupplierName(resultSet.getString("supplier_name"));
                proxy.setPurchaseValue(resultSet.getLong("purchase_value"));
                proxy.setRetailPrice(resultSet.getLong("retail_price"));
                proxy.setWholeSalePrice(resultSet.getLong("whole_sale_price"));
                proxy.setUnitSize(resultSet.getInt("unit_size"));
                proxy.setUnitInStock(resultSet.getInt("unit_in_stock"));
                proxy.setExpireDate(resultSet.getDate("expire_date"));

                batchPro.add(proxy);
                
                rowsAdded++;
            }
            resultSet.last();
            totalRecords = resultSet.getRow();
            resultSet.beforeFirst();
            dataMap.put("productDetails", batchPro) ;       
            dataMap.put("totalRecords", totalRecords) ; 
//            return batchPro;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataMap;
    }
    
    public int getRowCount(ResultSet set) throws SQLException {
        int rowCount;
        int currentRow = set.getRow();
        rowCount = set.last() ? set.getRow() : 0;
        if (currentRow == 0) {
            set.beforeFirst();
        } else {
            set.absolute(currentRow);
        }
        return rowCount;
    }
    
    public int getNextBatchId(int proId) {
        ResultSet rs = null;
        int batch_id = 1;
        String query = "SELECT * FROM product_details WHERE pro_id='" + proId + "' ORDER BY batch_id DESC Limit 1";
        try {
            rs = productDataAccess.getResultSet(query);
            while (rs.next()) {
                batch_id = rs.getInt("batch_id");
                batch_id ++;
                return batch_id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return batch_id;
    }

    public ResultSet getSearchResultSet(String searchTerms, FrmSearchBox searchBox) throws SQLException {
        String sql = null;
        ResultSet resultSet = null;
        if (searchBox != null) {
            if (searchTerms.equals("productSearch")) {
                if (searchBox.getSearchId() > 0) {
                    sql = "SELECT p.pro_id as PRO_ID, p.pro_name as PRO_NAME, pd.retail_price as RETAIL_PRICE , "
                            + "pd.whole_sale_price as WHOLE_SALE_PRICE, s.supplier_id as SUPPLIER_ID, s.supplier_name as SUPPLIER_NAME, pd.unit_in_stock as UNIT_IN_STOCK "
                            + "FROM product p JOIN supplier s ON p.supplier_id = s.supplier_id JOIN product_details pd ON p.pro_id = pd.pro_id "
                            + "WHERE s.supplier_id = " +searchBox.getSearchId();
                } else {
                    sql = "SELECT p.pro_id as PRO_ID, p.pro_name as PRO_NAME, pd.retail_price as RETAIL_PRICE , "
                            + "pd.whole_sale_price as WHOLE_SALE_PRICE,  pd.unit_in_stock as UNIT_IN_STOCK  "
                            + "FROM product p JOIN product_details pd ON p.pro_id = pd.pro_id ";
                }
                searchBox.setQueryString(sql);
                sql = sql + " GROUP BY p.pro_name ";
            } else if (searchTerms.equals("supplierSearch")){
                sql = "SELECT s.supplier_id as SUPPLIER_ID, s.supplier_name as SUPPLIER_NAME, s.phone as PHONE_NO FROM supplier s";
                searchBox.setQueryString(sql);
            } else if (searchTerms.equals("customerSearch")){
                sql = "SELECT c.customer_id, c.first_name, c.last_name ,c.phone FROM customer c";
                searchBox.setQueryString(sql);
            }
            
            resultSet = productDataAccess.getResultSet(sql);
        } else {
            if (!searchTerms.isEmpty()) {
                resultSet = productDataAccess.getResultSet(searchTerms);
            }
        }
        return resultSet;
    }
    
       public ResultSet getSearchingResultSet(String searchTerms, String searchColumn, String searchValue) throws SQLException {
        String sql = null;
        ResultSet resultSet = null;
            if (!searchTerms.isEmpty()) {
                if (searchColumn.contains("pro_name")){
                    sql = searchTerms + " WHERE " + searchColumn + " LIKE '" + searchValue + "%' GROUP BY p.pro_name";
                } else {
                    sql = searchTerms + " WHERE " + searchColumn + " LIKE '" + searchValue + "%'";
                }              
                resultSet = productDataAccess.getResultSet(sql);
            }
//        }
        return resultSet;
    }
}
