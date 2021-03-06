/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmPayment.java
 *
 * Created on Feb 12, 2015, 9:47:22 PM
 */
package pharmasoft.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pharmasoft.db.dao.CommonDAO;
import pharmasoft.db.dao.TransactionDAO;
import pharmasoft.db.proxyClasses.TransDetailsProxy;
import pharmasoft.db.proxyClasses.TransPurchDetailsProxy;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.StringFormatter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

/**
 *
 * @author Vipula
 */
public class FrmSendToCashier extends javax.swing.JDialog {
    
    private CommonDAO commonDAO;
    private TransactionDAO transDAO;
    private Object frmType;
    private long grandTotal;
    private String transId;
    

    /** Creates new form FrmPayment */
    public FrmSendToCashier(long subTotal, String transId, Object frmType) {
        initComponents();
        commonDAO = new CommonDAO();
        transDAO = new TransactionDAO();
        this.setModal(true);
        this.setLocation(500, 330);
        this.grandTotal = subTotal;
        this.lblGrandTotal.setText(StringFormatter.formatToRupees(subTotal));
        this.lblReciptNoValue.setText(transId);
        this.frmType = frmType;
        transId = transId;
 
    }
    
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGrandTotal = new javax.swing.JLabel();
        lblReciptNoValue = new javax.swing.JLabel();
        lblBal = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblReciptNo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
            	clearTrnTable();
            	dispose();
        	}
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEND TO CASHIER");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 150));

        lblGrandTotal.setFont(new java.awt.Font("Times New Roman", 1, 18));

        lblReciptNoValue.setFont(new java.awt.Font("Times New Roman", 1, 14));

        lblBal.setFont(new java.awt.Font("Times New Roman", 1, 14));
        lblBal.setText("Balance");

        lblBalance.setFont(new java.awt.Font("Times New Roman", 1, 14));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel4.setText("Grand Total");

        lblReciptNo.setFont(new java.awt.Font("Times New Roman", 1, 14));
        lblReciptNo.setText("Recipt Number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblReciptNo)
        				.addComponent(lblBal))
        			.addGap(41)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblGrandTotal, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(lblBalance, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
        					.addGap(61, 89, Short.MAX_VALUE))
        				.addComponent(lblReciptNoValue, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(19)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
        				.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        				.addComponent(lblGrandTotal, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblReciptNo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(lblReciptNoValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(101)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
        				.addComponent(lblBal, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        				.addComponent(lblBalance, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.linkSize(SwingConstants.VERTICAL, new Component[] {lblGrandTotal, lblBalance});
        jPanel1.setLayout(jPanel1Layout);

        jPanel2.setPreferredSize(new java.awt.Dimension(318, 30));

        btnOk.setText("OK");
        btnOk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnOkKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(157)
        			.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2.setLayout(jPanel2Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jPanel2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(42, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    private void btnOkKeyPressed(java.awt.event.KeyEvent evt) {                                 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	clearTrnTable();
        	dispose();
        }
    }                                

    private void insertTransaction() {
        boolean flag = false;
//        try {
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FrmAddNewProductBatch.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
	private void clearTrnTable() {
		DefaultTableModel model = null;
		int rowCount = 0;
		FrmTransaction frmTrn = (FrmTransaction) frmType;
		model = (DefaultTableModel) frmTrn.tblTransaction.getModel();
		rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		frmTrn.txtProductCode.grabFocus();
	}
	
	
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBal;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblGrandTotal;
    private javax.swing.JLabel lblReciptNo;
    private javax.swing.JLabel lblReciptNoValue;
    // End of variables declaration                   
}
