/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.db.proxyClasses;

import java.util.Date;

/**
 *
 * @author Vipula
 */
public class ProductBatchProxy {

    private int productId;
    private String productName;
    private String supplierName;
    private int batchId;
    private long purchaseValue;
    private long retailPrice;
    private long wholeSalePrice;
    private int unitSize;
    private int unitInStock;
    private Date expireDate;


    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public long getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(long purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public long getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(long retailPrice) {
        this.retailPrice = retailPrice;
    }
    
    public long getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(long wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
    
    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }
}
