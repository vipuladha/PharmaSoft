/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.dao;


import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pharmasoft.db.model.Category;
import pharmasoft.db.model.Customer;
import pharmasoft.db.model.Product;
import pharmasoft.db.model.Supplier;
import pharmasoft.dbConnection.DataBaseConnection;
import pharmasoft.util.StringFormatter;

/**
 *
 * @author gayan
 */
public class CommonDAO {

    Statement statement = null;
    ResultSet rs = null;
    DataBaseConnection dbConnection;

    public CommonDAO() {

        dbConnection = new DataBaseConnection();

    }

    public void getNextSerial (String idType) {
    	Date todayDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    	String strDate = sdf.format(todayDate);
    	String nxtSeqNo = String.valueOf(getNextSequence(idType));
    	String id = strDate + nxtSeqNo;
    	String genId = StringFormatter.padLeft(id, 12, "0");   	
    	System.out.println(genId);
    	
    	boolean flag = updateNextSequence(idType);
    }
    
    public int getNextSequence (String idType) {
    	int nextSeq = 0;
    	Timestamp timestamp;
    	Date todayDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    	String strSystemDate = sdf.format(todayDate);
    	String tblDate = null;
    	
        String query = "SELECT next_seq_no, timestamp FROM sys_params WHERE id_type = '" + idType + "' ";
        try {
            statement = dbConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	nextSeq = rs.getInt("next_seq_no");
            	timestamp = rs.getTimestamp("timestamp");
            	tblDate = sdf.format(timestamp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (strSystemDate.equals(tblDate))
        	return ++nextSeq;
        else
        	return 1;
    }
    
	public boolean updateNextSequence(String idType) {
		int flag = 0;
		boolean ret = false;
		String query = "UPDATE sys_params SET next_seq_no = ? WHERE id_type = '" + idType + "' ";
		int nextSeq = getNextSequence(idType);
		try {
			
			PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
			stat.setInt(1, nextSeq);

			flag = stat.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (flag == 1) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}
    
    public boolean addNewSupplier(Supplier supplier) {

        int flag = 0;
        boolean ret = false;

       // SysparamDAO sysparamDAO = new SysparamDAO();

       // String memCode = sysparamDAO.getNextSerial("MemberCode", 8, 99999999);


        try {
            String query = "INSERT INTO supplier(supplier_name, com_name, address_line1, address_line2, address_line3"
                    + ", phone, fax) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, supplier.getSupplierName());
            stat.setString(2, supplier.getComName());
            stat.setString(3, supplier.getAddressLine1());
            stat.setString(4, supplier.getAddressLine2());
            stat.setString(5, supplier.getAddressLine3());
            stat.setString(6, supplier.getPhone());
            stat.setString(7, supplier.getFax());

          //  sysparamDAO.updateSerial("MemberCode", memCode);

            flag = stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }

        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }

        return ret;
    }
    
    public boolean updateSupplier(Supplier supplier, int supplierId) {

        int flag = 0;
        boolean ret = false;
        System.out.println("supplierId ================>" + supplierId);

        try {
            String query = "UPDATE supplier SET supplier_name=? ,com_name=?, address_line1=?, "
                    + "address_line2=?, address_line3=?, phone=?, fax=? "
                    + "WHERE supplier_id=" + supplierId + "";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, supplier.getSupplierName());
            stat.setString(2, supplier.getComName());
            stat.setString(3, supplier.getAddressLine1());
            stat.setString(4, supplier.getAddressLine2());
            stat.setString(5, supplier.getAddressLine3());
            stat.setString(6, supplier.getPhone());
            stat.setString(7, supplier.getFax());

            flag = stat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }

        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }

        return ret;
    }
    
   public Supplier getSupplierDetailsById(int supId) {
        ResultSet rs = null;
        String supplierName = null;
        String comName = null;
        String addressLine1 = null;
        String addressLine2 = null;
        String addressLine3 = null;
        String phone = null;
        String fax = null;
        Supplier supplier = null;
        String query = "SELECT * FROM supplier WHERE supplier_id='" + supId + "'";
        try {
            statement = dbConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                supplierName = rs.getString("supplier_name");
                comName = rs.getString("com_name");
                addressLine1 = rs.getString("address_line1");
                addressLine2 = rs.getString("address_line2");
                addressLine3 = rs.getString("address_line3");
                phone = rs.getString("phone");
                fax = rs.getString("fax"); 
            }
            supplier = new Supplier();
            supplier.setSupplierName(supplierName);
            supplier.setComName(comName);
            supplier.setAddressLine1(addressLine1);
            supplier.setAddressLine2(addressLine2);
            supplier.setAddressLine3(addressLine3);
            supplier.setPhone(phone);
            supplier.setFax(fax);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return supplier;
    }
    
    public boolean addNewCustomer(Customer customer) {
        int flag = 0;
        boolean ret = false;
        // SysparamDAO sysparamDAO = new SysparamDAO();
        // String memCode = sysparamDAO.getNextSerial("MemberCode", 8, 99999999);
        try {
            String query = "INSERT INTO customer(first_name, last_name, address_line1, address_line2, address_line3, "
                    + "phone, fax) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, customer.getFirstName());
            stat.setString(2, customer.getLastName());
            stat.setString(3, customer.getAddressLine1());
            stat.setString(4, customer.getAddressLine2());
            stat.setString(5, customer.getAddressLine3());
            stat.setString(6, customer.getPhone());
            stat.setString(7, customer.getFax());
            //  sysparamDAO.updateSerial("MemberCode", memCode);

            flag = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }
        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }
    
    public boolean updateCustomer(Customer customer, int customerId) {
        int flag = 0;
        boolean ret = false;
        System.out.println("customerId ================>" + customerId);
        try {
            String query = "UPDATE customer SET first_name=? ,last_name=?, address_line1=?, address_line2=?, "
                    + "address_line3=?, phone=?, fax=? WHERE customer_id=" + customerId + "";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, customer.getFirstName());
            stat.setString(2, customer.getLastName());
            stat.setString(3, customer.getAddressLine1());
            stat.setString(4, customer.getAddressLine2());
            stat.setString(5, customer.getAddressLine3());
            stat.setString(6, customer.getPhone());
            stat.setString(7, customer.getFax());

            flag = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }
        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    public Customer getCustomerDetailsById(int cusId) {
        ResultSet rs = null;
        String firstName = null;
        String lastName = null;
        String addressLine1 = null;
        String addressLine2 = null;
        String addressLine3 = null;
        String phone = null;
        String fax = null;
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE customer_id='" + cusId + "'";
        try {
            statement = dbConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                addressLine1 = rs.getString("address_line1");
                addressLine2 = rs.getString("address_line2");
                addressLine3 = rs.getString("address_line3");
                phone = rs.getString("phone");
                fax = rs.getString("fax"); 
            }
            customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setAddressLine1(addressLine1);
            customer.setAddressLine2(addressLine2);
            customer.setAddressLine3(addressLine3);
            customer.setPhone(phone);
            customer.setFax(fax);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }
    
    public boolean addNewCategory(Category category) {
        int flag = 0;
        boolean ret = false;
        // SysparamDAO sysparamDAO = new SysparamDAO();
        // String memCode = sysparamDAO.getNextSerial("MemberCode", 8, 99999999);
        try {
            String query = "INSERT INTO category(cat_name, cat_description) VALUES(?,?)";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, category.getCategoryName());
            stat.setString(2, category.getCategoryDesc());
            //  sysparamDAO.updateSerial("MemberCode", memCode);

            flag = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }
        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }
    
    public boolean updateCategory(Category category, int categoryId) {
        int flag = 0;
        boolean ret = false;
        System.out.println("customerId ================>" + categoryId);
        try {
            String query = "UPDATE category SET cat_name=? ,cat_description=? WHERE cat_id=" + categoryId + "";
            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
            stat.setString(1, category.getCategoryName());
            stat.setString(2, category.getCategoryDesc());

            flag = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException---->>" + e.getMessage());
        }
        if (flag == 1) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }
    
    public boolean addNewProduct(Product product) throws SQLException {
        Connection dbCon = null;
        int pro_id = 0;
        PreparedStatement preStatInsertPro = null;
        PreparedStatement preStatInsertProDesc = null;
        
        String insertProduct = "INSERT INTO product(pro_name, pro_description, supplier_id) VALUES(?,?,?)";
        String insertProductDetails = "INSERT INTO product_details(pro_id, batch_id, unit_size, discount, retail_price, whole_sale_price) "
                + "VALUES(?,?,?,?,?,?)";

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
            preStatInsertProDesc.setInt(2, getNextBatchId(pro_id));
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
    
    public boolean addNewBatchProduct(Product product) throws SQLException {
        Connection dbCon = null;
        int pro_id = product.getProductId();
        PreparedStatement preStatUpdatePro = null;
        PreparedStatement preStatInsertProDesc = null;
        
        String updateProduct = "UPDATE product SET pro_name =?, pro_description =?, supplier_id =? WHERE pro_id = ?";
        String insertProductDetails = "INSERT INTO product_details(pro_id, batch_id, unit_size, discount, retail_price, whole_sale_price) "
                + "VALUES(?,?,?,?,?,?)";

        try {
            dbCon = dbConnection.getConnection();

            dbCon.setAutoCommit(false);

            preStatUpdatePro = dbCon.prepareStatement(updateProduct);
            preStatUpdatePro.setString(1, product.getProductName());
            preStatUpdatePro.setString(2, product.getProductDesc());
            preStatUpdatePro.setInt(3, product.getSupplierId());
            preStatUpdatePro.setInt(4, pro_id);
            preStatUpdatePro.executeUpdate();

            preStatInsertProDesc = dbCon.prepareStatement(insertProductDetails);
            preStatInsertProDesc.setInt(1, pro_id);
            preStatInsertProDesc.setInt(2, getNextBatchId(pro_id));
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
    
    
    
//    public boolean addNewProduct(Product product) {
//        int flag = 0;
//        boolean ret = false;
//        Connection connections = null;
//        try {
//            connections = dbConnection.getConnection();
//            connections.setAutoCommit(false);
//            String query = "INSERT INTO product(pro_name, pro_description, supplier_id) VALUES(?,?,?)";
//            PreparedStatement stat = dbConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            stat.setString(1, product.getProductName());
//            stat.setString(2, product.getProductDesc());
//            stat.setInt(3, product.getSupplierId());
//
//            stat.execute();
//            ResultSet rs = stat.getGeneratedKeys();
//            if (rs.next()) {
//                int pro_id = rs.getInt(1);
//                String desc_query = "INSERT INTO product_details(pro_id, batch_id, unit_size, discount, retail_price, "
//                        + "whole_sale_price ) VALUES(?,?,?,?,?,?)";
//                PreparedStatement desc_stat = dbConnection.getConnection().prepareStatement(desc_query);
//                desc_stat.setInt(1, pro_id);
//                desc_stat.setInt(2, getNextBatchId(pro_id));
//                desc_stat.setInt(3, product.getUnitSize());
//                desc_stat.setInt(4, product.getDiscount());
//                desc_stat.setLong(5, product.getRetailPrice());
////                desc_stat.setInt(6, product.getWholeSalePrice());
////                stat.setInt(7, product.getSupplierId());
//
//                desc_stat.execute();
//                connections.commit();
//            }
//        } catch (SQLException e) {
//            try {
//                connections.rollback();
//            } catch (SQLException ex) {
//                Logger.getLogger(CommonDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            e.printStackTrace();
//            System.out.println("SQLException---->>" + e.getMessage());
//        }
//        if (flag == 1) {
//            ret = true;
//        } else {
//            ret = false;
//        }
//        return ret;
//    }
        
    public Product getProductDetails(int proId, int batchId) {
        ResultSet rs = null;
        int productId = 0;
        int batchID = 0;
        String proName = null;
        String proDesc = null;
        int unitSize = 0;
        int dicount = 0;
        long retailPrice = 0;
        int wholeSalePrice = 0;
        int unitInStock = 0;
        int suplierId = 0;
        Date expierDate = null;
        Product product = null;
        String query = null;
        
        if (proId > 0 && batchId > 0)
            query = "SELECT * FROM product p JOIN product_details pd ON p.pro_id = pd.pro_id  "
                    + "WHERE p.pro_id='" + proId + "' AND pd.batch_id='" + batchId + "'";
        else if (proId > 0)
            query = "SELECT * FROM product p JOIN product_details pd ON p.pro_id = pd.pro_id  WHERE p.pro_id='" + proId + "'";
            
        try {
            if (query != null) {
                statement = dbConnection.getConnection().createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    productId = rs.getInt("pro_id");
                    batchID = rs.getInt("batch_id");
                    proName = rs.getString("pro_name");
                    proDesc = rs.getString("pro_description");
                    unitSize = rs.getInt("unit_size");
                    dicount = rs.getInt("discount");
                    retailPrice = rs.getLong("retail_price");
                    wholeSalePrice = rs.getInt("whole_sale_price");
                    unitInStock = rs.getInt("unit_in_stock");
                    suplierId = rs.getInt("supplier_id");
                    expierDate = rs.getDate("expire_date");
                }
                product = new Product();
                product.setProductId(productId);
                product.setBatchId(batchID);
                product.setProductName(proName);
                product.setProductDesc(proDesc);
                product.setUnitSize(unitSize);
                product.setDiscount(dicount);
                product.setRetailPrice(retailPrice);
                product.setWholeSalePrice(wholeSalePrice);
                product.setUnitInStock(unitInStock);
                product.setSupplierId(suplierId);
                product.setExpireDate(expierDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
    
    public int getNextBatchId(int proId) {
        ResultSet rs = null;
        int batch_id = 1;
        String query = "SELECT * FROM product_details WHERE pro_id='" + proId + "' ORDER BY batch_id DESC Limit 1";
        try {
            rs = getResultSet(query);
            while (rs.next()) {
                batch_id = rs.getInt("batch_id");
                return batch_id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return batch_id;
    }
    
    public ResultSet getResultSet(String sql) {
        try {
            statement = dbConnection.getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("sql error=============>" + ex);
            return null;
        }

    }
}
