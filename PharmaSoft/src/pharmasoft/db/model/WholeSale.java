/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pharmasoft.db.model;

import java.util.Date;

/**
 *
 * @author Vipula
 */
public class WholeSale {
    
    private int invoiceId;
    
    private int totalAmount;
    
    private int discount;
    
    private Date reciptDate;

    private String customerName;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getReciptDate() {
        return reciptDate;
    }

    public void setReciptDate(Date reciptDate) {
        this.reciptDate = reciptDate;
    }
       
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
}
