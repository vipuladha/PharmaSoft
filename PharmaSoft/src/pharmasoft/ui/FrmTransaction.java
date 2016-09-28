/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmTransaction.java
 *
 * Created on Dec 12, 2014, 11:17:04 AM
 */
package pharmasoft.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pharmasoft.db.dao.CommonDAO;
import pharmasoft.db.dao.IdGeneratorDAO;
import pharmasoft.db.dao.TransactionDAO;
import pharmasoft.db.model.Product;
import pharmasoft.db.proxyClasses.TransDetailsProxy;
import pharmasoft.ui.util.ListTableModel;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.StringFormatter;

/**
 *
 * @author Vipula
 */
public class FrmTransaction extends javax.swing.JInternalFrame implements ListSelectionListener{

    private int productId;
    private int batchId;
    private CommonDAO commonDAO;
    private TransactionDAO transDAO;
    private IdGeneratorDAO idGenDAO;
    private List<TransDetailsProxy> tableRowContent = new ArrayList<TransDetailsProxy>();
    private long grandTotal;
    private TransDetailsProxy transProxy;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }



    /** Creates new form FrmTransaction */
    public FrmTransaction(Dimension innerPannel) {
        initComponents(); 
        txtProductCode.setFocusTraversalKeysEnabled(false);
        commonDAO = new CommonDAO();
        transDAO = new TransactionDAO();
        idGenDAO = new IdGeneratorDAO();
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.txtProductCode.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                      txtProductCode.requestFocus();
                }
          });
        createEvents();
        
        

    }

                      
    private void initComponents() {

        btnPay = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtProductCode = new javax.swing.JTextField();
        txtQuentity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnPay.setText("Pay");
        btnPay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPay.setEnabled(false);
        btnPay.setMaximumSize(new java.awt.Dimension(65, 23));
        btnPay.setMinimumSize(new java.awt.Dimension(65, 23));


        btnCancel.setText("Cancel");
        btnCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblTransaction.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "UNIT SIZE", "RETAIL PRICE", "QUANTITY", "VALUE"
            }
        ));
        jScrollPane2.setViewportView(tblTransaction);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setText("Sub Total");

        lblSubtotal.setFont(new java.awt.Font("Times New Roman", 1, 18));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Product Code");
        jLabel2.setText("Quentity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuentity, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtQuentity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnPay});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnPay});

        pack();
    }
    
    private void createEvents() {
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        
        btnPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPayKeyPressed(evt);
            }
        });
        
        
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        
        

        txtProductCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductCodeFocusLost(evt);
            }
        });
        txtProductCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductCodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductCodeKeyTyped(evt);
            }
        });

        txtQuentity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuentityKeyPressed(evt);
            }

        });

	}

    private void txtProductCodeKeyPressed(java.awt.event.KeyEvent evt) {                                          
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            FrmSearchBox frmSearchBox = new FrmSearchBox(this);
            frmSearchBox.setValue("p.pro_name", "productSearch");
            frmSearchBox.setVisible(true);
            frmSearchBox.setLocation(200, 300);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int proId = getProductId();
            Product product = commonDAO.getProductDetails(proId, batchId);
            transProxy = new TransDetailsProxy();
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            if (product != null) {
                if (!isAddedToTable(product)) {
                    DefaultTableModel model = (DefaultTableModel) tblTransaction.getModel();
                    transProxy.setProId(product.getProductId());                  
                    transProxy.setBatchId(product.getBatchId());
                    transProxy.setProductName(product.getProductName());
                    transProxy.setUnitPrice(product.getRetailPrice());
                    model.addRow(new Object[]{product.getProductName(), product.getUnitSize(),
                                StringFormatter.formatToRupees(product.getRetailPrice())});
                    renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
                    tblTransaction.getColumnModel().getColumn(1).setCellRenderer(renderer);
                    tblTransaction.getColumnModel().getColumn(2).setCellRenderer(renderer);
                    tblTransaction.getColumnModel().getColumn(3).setCellRenderer(renderer);
                    tblTransaction.getColumnModel().getColumn(4).setCellRenderer(renderer);
                    txtProductCode.setText("");
//                    tableRowContent.add(product);
                    tableRowContent.add(transProxy);
                    setProductId(0);
                    txtQuentity.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Duplicate product not allowed.", "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    txtProductCode.setText("");
                }
            }
            
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB){
            btnPay.requestFocus();
        }
}                                         

    private void txtProductCodeKeyTyped(java.awt.event.KeyEvent evt) {                                        

    }                                       

    private void txtQuentityKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = tblTransaction.getRowCount();
            int quentity = 0;
            long price = 0;
            long value = 0;
            long subTotal = 0;
            if (txtQuentity.getText() != null && !txtQuentity.getText().isEmpty()) {
                quentity = Integer.parseInt(txtQuentity.getText());
                transProxy.setQuentity(quentity);
            }

            tblTransaction.setValueAt(quentity, row - 1, 3);
            price = StringFormatter.rupeesToLong(tblTransaction.getValueAt(row - 1, 2).toString());
            value = price * quentity;
            tblTransaction.setValueAt(StringFormatter.formatToRupees(value), row - 1, 4);
            subTotal = getSubTotalValue();
            grandTotal = subTotal;
            transProxy.setSubTotal(value);
            lblSubtotal.setText(StringFormatter.formatToRupees(subTotal));
            txtProductCode.setText("");
            txtQuentity.setText("");
            txtProductCode.requestFocus();
        }
    }                                      


    private void txtProductCodeFocusLost(java.awt.event.FocusEvent evt) {                                         
       if (tableRowContent != null && !tableRowContent.isEmpty())
            btnPay.setEnabled(true);
    }                                        

    private void btnPayKeyPressed(java.awt.event.KeyEvent evt) {                                  
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	insertData();
        
        }       
    }                                 

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {                                       
//            FrmPayment frmPayment = new FrmPayment(this.grandTotal, tableRowContent, this);
//            frmPayment.setVisible(true);
    }                                      

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
            dispose();
    }                                         

    private void insertData() {
    	try {
    		if (tblTransaction.getRowCount() > 0 ) {
            	String transId = transDAO.insertTransaction(this.grandTotal, tableRowContent);
            	if (transId != null){          		
                	FrmSendToCashier frmSend = new FrmSendToCashier(this.grandTotal, transId, this);
                    frmSend.setVisible(true);
            	}
    		}
            
    	} catch(Exception ex){
    		Logger.getLogger(FrmAddNewProductBatch.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }
    
    private long getSubTotalValue(){
        int row = tblTransaction.getRowCount();
        long subTotal = 0;
        if (row > 0) {
            for (int i = 0; i < row; i++) {
                subTotal += StringFormatter.rupeesToLong(tblTransaction.getValueAt(i, 4).toString());
            }
        }
        return subTotal;
    }
    
    private boolean isAddedToTable(Product addingPro) {
        if (!tableRowContent.isEmpty()) {
            for (Iterator<TransDetailsProxy> it = tableRowContent.iterator(); it.hasNext();) {
                TransDetailsProxy transProxy = it.next();
                if (addingPro.getProductId() == transProxy.getProId()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    protected javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSubtotal;
    public javax.swing.JTable tblTransaction;
    public javax.swing.JTextField txtProductCode;
    public javax.swing.JTextField txtQuentity;
    // End of variables declaration                   

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
