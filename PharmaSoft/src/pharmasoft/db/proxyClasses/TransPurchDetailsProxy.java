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
public class TransPurchDetailsProxy{
    private int pOrderId;
    private long grandTotal;
    private String payType;
    private long chqNo;
    private Date chqDate;

    public Date getChqDate() {
        return chqDate;
    }

    public void setChqDate(Date chqDate) {
        this.chqDate = chqDate;
    }

    public long getChqNo() {
        return chqNo;
    }

    public void setChqNo(long chqNo) {
        this.chqNo = chqNo;
    }

    public int getpOrderId() {
        return pOrderId;
    }

    public void setpOrderId(int pOrderId) {
        this.pOrderId = pOrderId;
    }
    
    public long getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(long grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
        
}
