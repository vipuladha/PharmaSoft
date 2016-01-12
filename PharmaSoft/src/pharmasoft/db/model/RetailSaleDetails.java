/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pharmasoft.db.model;


/**
 *
 * @author Vipula
 */
public class RetailSaleDetails {

    private int reciptDetailId;
    
    private int unitPrice;
    
    private int quantity;
    
    private int subTotal;
    
    private int reciptId;
    
    private int productId;

    public int getReciptDetailId() {
        return reciptDetailId;
    }

    public void setReciptDetailId(int reciptDetailId) {
        this.reciptDetailId = reciptDetailId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getReciptId() {
        return reciptId;
    }

    public void setReciptId(int reciptId) {
        this.reciptId = reciptId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}
