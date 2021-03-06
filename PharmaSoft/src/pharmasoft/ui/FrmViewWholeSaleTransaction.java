/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmViewRetailTransaction.java
 *
 * Created on Oct 18, 2015, 6:15:46 PM
 */
package pharmasoft.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pharmasoft.db.dao.TransactionDAO;
import pharmasoft.db.model.RetailSale;
import pharmasoft.db.model.WholeSale;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.StringFormatter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Vipula
 */
public class FrmViewWholeSaleTransaction extends JInternalFrame implements PaginationListner{

    private static int pageSize = 5;
    private int noOfPages;
    private int totalRecords;
    private int startingRowNo;
    private int currentPage = 1;
    
    private JButton btnNext;
    private JButton btnPrevious;
    private JButton btnLast;
    private JButton btnFirst;

    public JTextField getTxtCusName() {
        return txtCusName;
    }

    public void setTxtCusName(JTextField txtCusName) {
        this.txtCusName = txtCusName;
    }
    
    private TransactionDAO transDAO;
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
        
    /** Creates new form FrmViewRetailTransaction */
    public FrmViewWholeSaleTransaction(Dimension innerPannel) {
        initComponents();
        transDAO = new TransactionDAO();
        Dimension localFrm = UiSupliment.getFormLocation(innerPannel, this.getSize());
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setLocation((int) localFrm.getWidth(), (int) localFrm.getHeight());
        jDateStartDate.setDate(new Date());
        jDateEndDate.setVisible(false);
        lblEnddate.setVisible(false);
        rBtnTodayTrn.setSelected(true);
        this.txtCusName.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        addPaginationaction();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrans = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rBtnTodayTrn = new javax.swing.JRadioButton();
        rBtnPastTrn = new javax.swing.JRadioButton();
        jDateStartDate = new com.toedter.calendar.JDateChooser();
        lblStartDate = new javax.swing.JLabel();
        lblEnddate = new javax.swing.JLabel();
        jDateEndDate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCusName = new javax.swing.JTextField();
        paginationPane = new javax.swing.JPanel();
        paginationBox1 = new pharmasoft.ui.PaginationBox();
        jLabel1 = new javax.swing.JLabel();
        lblNoOfCus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View WholeSale Transactions");
//        setPreferredSize(new java.awt.Dimension(840, 600));
        setBounds(100, 100, 884, 650);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INVOICE ID", "RECIPT DATE", "CUTOMER NAME", "DISCOUNT", "TOTAL AMOUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrans);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(rBtnTodayTrn);
        rBtnTodayTrn.setText("Today Transaction");
        rBtnTodayTrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnTodayTrnActionPerformed(evt);
            }
        });

        buttonGroup1.add(rBtnPastTrn);
        rBtnPastTrn.setText("Past Transaction");
        rBtnPastTrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnPastTrnActionPerformed(evt);
            }
        });

        jDateStartDate.setDateFormatString("dd-MM-yyyy");

        lblStartDate.setText("Start Date :  ");

        lblEnddate.setText("End Date :");

        jDateEndDate.setDateFormatString("dd-MM-yyyy");

        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearch.setMaximumSize(new java.awt.Dimension(65, 23));
        btnSearch.setMinimumSize(new java.awt.Dimension(65, 23));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchKeyPressed(evt);
            }
        });

        btnCancel1.setText("Cancel");
        btnCancel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setText("Customer Name");

        txtCusName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCusNameFocusLost(evt);
            }
        });
        txtCusName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCusNameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rBtnTodayTrn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblStartDate)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(rBtnPastTrn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lblEnddate)
                                .addGap(18, 18, 18)
                                .addComponent(jDateEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rBtnTodayTrn)
                            .addComponent(rBtnPastTrn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStartDate)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEnddate)))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        paginationPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout paginationPaneLayout = new javax.swing.GroupLayout(paginationPane);
        paginationPaneLayout.setHorizontalGroup(
        	paginationPaneLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(paginationPaneLayout.createSequentialGroup()
        			.addGap(160)
        			.addComponent(paginationBox1, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        			.addGap(200))
        );
        paginationPaneLayout.setVerticalGroup(
        	paginationPaneLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(paginationPaneLayout.createSequentialGroup()
        			.addComponent(paginationBox1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        			.addContainerGap())
        );
        paginationPane.setLayout(paginationPaneLayout);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel1.setText("No of Customers");

        lblNoOfCus.setFont(new java.awt.Font("Times New Roman", 1, 14));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16));
        jLabel2.setText("Total Transactions Value");

        lblTotalValue.setFont(new java.awt.Font("Times New Roman", 1, 16));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        				.addComponent(paginationPane, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel1)
        						.addComponent(jLabel2))
        					.addGap(27)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTotalValue, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblNoOfCus, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(paginationPane, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel1)
        				.addComponent(lblNoOfCus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jLabel2)
        				.addComponent(lblTotalValue, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>                        

    private void rBtnTodayTrnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        jDateStartDate.setDate(new Date());
        jDateStartDate.setVisible(true);
        jDateEndDate.setVisible(false);
        lblStartDate.setVisible(true);
        lblEnddate.setVisible(false);
    }                                            

    private void rBtnPastTrnActionPerformed(java.awt.event.ActionEvent evt) {                                            
        jDateStartDate.setDate(null);
        jDateStartDate.setVisible(true);
        jDateEndDate.setVisible(true);
        lblStartDate.setVisible(true);
        lblEnddate.setVisible(true);
//        jdateToday.setVisible(false);
    }                                           

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
}                                    

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
}                                          

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        fetchData();
    }                                         

    private void txtCusNameKeyPressed(java.awt.event.KeyEvent evt) {                                      
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            FrmSearchBox frmSearchBox = new FrmSearchBox(this);
            frmSearchBox.setValue("c.first_name", "customerSearch");
            frmSearchBox.setVisible(true);
            frmSearchBox.setLocation(200, 300);
        }
    }                                     

    private void txtCusNameFocusLost(java.awt.event.FocusEvent evt) {                                     
        if (txtCusName.getText().isEmpty())
            setCustomerId(-1);
    }                                    

    private void tblTransMouseClicked(java.awt.event.MouseEvent evt) {                                      
        JTable table = (JTable) evt.getSource();
        Point p = evt.getPoint();
        int row = table.rowAtPoint(p);
        int invoiceId = (int) table.getModel().getValueAt(row, 0);
        if (evt.getClickCount() == 2) {
            FrmInvoiceDetail dialog = new FrmInvoiceDetail(this, true, invoiceId);
            dialog.setVisible(true);
        }

    }                                     

    private void fetchData() {
        if (rBtnTodayTrn.isSelected()) {
            searchTransactions(jDateStartDate.getDate(), null);
        } else if (rBtnPastTrn.isSelected()) {
            searchTransactions(jDateStartDate.getDate(), jDateEndDate.getDate());
        }
    }
     private void searchTransactions(Date startDate, Date endDate) {
         DefaultTableModel model = (DefaultTableModel) tblTrans.getModel();
         model.getDataVector().removeAllElements();
         tblTrans.repaint();
         
         Map<String, Object> transMap = transDAO.getWSTransactionsByDate(getCustomerId(), startDate, endDate, pageSize, startingRowNo);
         Vector<WholeSale> transList = (Vector<WholeSale>) transMap.get("transDetails");
         totalRecords = (int) transMap.get("totalRecords");
         lblNoOfCus.setText(String.valueOf(totalRecords));
         if (totalRecords > pageSize) {
             int mode = totalRecords % pageSize;
             if (mode == 0)
                noOfPages = totalRecords / pageSize;
             else
                noOfPages = totalRecords / pageSize + 1;
         } else {
             noOfPages = 1;
         }
         setNoOfPages(noOfPages);
         setCurrentPage();
         long totalValue = (long) transMap.get("totalValue");
         DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
         DefaultTableCellRenderer rendererL = new DefaultTableCellRenderer();
         if (transList != null && !transList.isEmpty()) {
             for (Iterator<WholeSale> it = transList.iterator(); it.hasNext();) {
                 WholeSale proxy = it.next();
//                 totalValue = totalValue + proxy.getTotalAmount();
                 model.addRow(new Object[]{proxy.getInvoiceId(), proxy.getReciptDate(), proxy.getCustomerName(),  proxy.getDiscount(), 
                     StringFormatter.formatToRupees(proxy.getTotalAmount())});
                 renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
                 rendererL.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
                 tblTrans.getColumnModel().getColumn(0).setCellRenderer(renderer);
                 tblTrans.getColumnModel().getColumn(1).setCellRenderer(renderer);
                 tblTrans.getColumnModel().getColumn(2).setCellRenderer(rendererL);
                 tblTrans.getColumnModel().getColumn(3).setCellRenderer(renderer);
                 tblTrans.getColumnModel().getColumn(4).setCellRenderer(renderer);

             }
             lblTotalValue.setText(StringFormatter.formatToRupees((totalValue)));
         }
     }
     
    public void addPaginationaction() {
        SysAction sysAction = new SysAction();
        JPanel pagination = (JPanel) paginationPane.getComponent(0);
        if (pagination != null) {
            Component paginCom[] = pagination.getComponents();
            for (int i = 0; i < paginCom.length; i++) {
                Component component = paginCom[i];
                if ("btnNext".equals(component.getName())) {
                    btnNext = (JButton) component;
                    btnNext.addActionListener(sysAction);
                } else if ("btnPrevious".equals(component.getName())) {
                    btnPrevious = (JButton) component;
                    btnPrevious.addActionListener(sysAction);
                    btnPrevious.setEnabled(false);
                } else if ("btnLast".equals(component.getName())) {
                    btnLast = (JButton) component;
                    btnLast.addActionListener(sysAction);
                } else if ("btnFirst".equals(component.getName())) {
                    btnFirst = (JButton) component;
                    btnFirst.addActionListener(sysAction);
                }
            }
        }
    }
    
    @Override
    public void setNoOfPages(int noOfPages) {
        JPanel pagination = (JPanel) paginationPane.getComponent(0);
        if (pagination != null){
            Component paginCom[] = pagination.getComponents();
            for (int i = 0; i < paginCom.length; i++) {
                Component component = paginCom[i];
                System.out.println("Names -- " + component.getName());
                if ("txtNoOfPages".equals(component.getName())){
                    JTextField noOfPage = (JTextField) component;
                    noOfPage.setText(noOfPages + "");
                    break;
                }
            }
        }
            
    }

    @Override
    public void setCurrentPage() {
        JPanel pagination = (JPanel) paginationPane.getComponent(0);
        if (pagination != null) {
            Component paginCom[] = pagination.getComponents();
            for (int i = 0; i < paginCom.length; i++) {
                Component component = paginCom[i];
                if ("txtCurrentPage".equals(component.getName())) {
                    JTextField txtCurrentPage = (JTextField) component;
                    txtCurrentPage.setText(currentPage + "");
                    break;
                }
            }
        }
    }

    @Override
    public void navigateToNext() {
        startingRowNo = (pageSize * currentPage) + 1;
        currentPage = currentPage + 1;
        if (currentPage > 1) {
            btnPrevious.setEnabled(true);
            btnFirst.setEnabled(true);
        }
        
        if (noOfPages == currentPage) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        }
        fetchData();
    }

    @Override
    public void navigateToPrevious() {
        startingRowNo = startingRowNo - pageSize;
        currentPage = currentPage - 1;
        if (currentPage == 1) {
            btnPrevious.setEnabled(false);
            btnFirst.setEnabled(false);
        }
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);
        fetchData();
    }

    @Override
    public void navigateToFirst() {
        startingRowNo = 1;
        currentPage = 1;
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);
        btnPrevious.setEnabled(false);
        btnFirst.setEnabled(false);
        fetchData();
    }

    @Override
    public void navigateToLast() {
        startingRowNo = ((noOfPages - 1) * pageSize) + 1;
        currentPage = noOfPages;
        btnFirst.setEnabled(true);
        btnPrevious.setEnabled(true);
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
        fetchData();
    }
    
    class SysAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if ("btnNext".equals(btn.getName())){
                navigateToNext();
            } else if ("btnPrevious".equals(btn.getName())){
                navigateToPrevious();
            } else if ("btnLast".equals(btn.getName())){
                navigateToLast();
            } else if ("btnFirst".equals(btn.getName())){
                navigateToFirst();
            }
        }
        
    }
     
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new FrmViewRetailTransaction().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateEndDate;
    private com.toedter.calendar.JDateChooser jDateStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEnddate;
    private javax.swing.JLabel lblNoOfCus;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblTotalValue;
    private pharmasoft.ui.PaginationBox paginationBox1;
    private javax.swing.JPanel paginationPane;
    private javax.swing.JRadioButton rBtnPastTrn;
    private javax.swing.JRadioButton rBtnTodayTrn;
    private javax.swing.JTable tblTrans;
    public javax.swing.JTextField txtCusName;
    // End of variables declaration                   
}
