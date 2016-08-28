package pharmasoft.db.model;

public class TransactionDetails {

    private int transDetailId;
    
    private int unitPrice;
    
    private int quantity;
    
    private int subTotal;
    
    private int transId;
    
    private int productId;
    
	public TransactionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTransDetailId() {
		return transDetailId;
	}

	public void setTransDetailId(int transDetailId) {
		this.transDetailId = transDetailId;
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

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
}
