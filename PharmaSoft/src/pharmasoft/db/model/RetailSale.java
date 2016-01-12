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
public class RetailSale {
    
    private int reciptId;
    
    private int totalAmount;
    
    private int discount;
    
    private Date reciptDate;

    public int getReciptId() {
        return reciptId;
    }

    public void setReciptId(int reciptId) {
        this.reciptId = reciptId;
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
    
    
}
