package pharmasoft.db.model;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {

	private int transId;
	
	private int totalAmount;
	
    private int discount;
    
    private Date reciptDate;
    
    private String tranType;
    
    private Timestamp timeStamp;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
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

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	
	
}
