/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmStockTaking.java
 *
 * Created on Aug 30, 2015, 3:12:48 PM
 */
package pharmasoft.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import pharmasoft.db.dao.ProductDAO;
import pharmasoft.db.proxyClasses.ProductBatchProxy;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.StringFormatter;

/**
 *
 * @author Vipula
 */
public class FrmStockTaking extends javax.swing.JInternalFrame implements PaginationListner{

    private int supplierId;
    private int productId;
    private static int pageSize = 5;
    private int noOfPages;
    private int totalRecords;
    private int startingRowNo;
    private int currentPage = 1;
    
    private JButton btnNext;
    private JButton btnPrevious;
    private JButton btnLast;
    private JButton btnFirst;
            
    private ProductDAO productDAO;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /** Creates new form FrmStockTaking */
    public FrmStockTaking(Dimension innerPannel) {
        initComponents();
        pack();

        Dimension localFrm = UiSupliment.getFormLocation(innerPannel, this.getSize());
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setLocation((int) localFrm.getWidth(), (int) localFrm.getHeight());
        this.txtSupplierName.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        this.txtProName.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        productDAO = new ProductDAO();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(getBackground());
                    int modelRow = convertRowIndexToModel(row);
                    int type = (int)getModel().getValueAt(modelRow, 7);
                    if (type == 0 ) c.setBackground(Color.RED);
                }

                return c;
            }
        };
        jLabel3 = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtSupplierName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        txtProName = new javax.swing.JTextField();
        txtGenericName = new javax.swing.JTextField();
        chkAllItem = new javax.swing.JCheckBox();
        chkSupName = new javax.swing.JCheckBox();
        chkProduct = new javax.swing.JCheckBox();
        chkGeneric = new javax.swing.JCheckBox();
        paginationPane = new javax.swing.JPanel();
        newJPanel1 = new pharmasoft.ui.PaginationBox();

        setResizable(true);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "SUPPLER NAME", "BATCH NO", "UNIT SIZE", "PURCHASE VALUE", "RETAIL PRICE", "WHOLE SALE PRICE", "AVAILABLE QTY", "EXPIRE DATE"
            }
        ));
        jScrollPane2.setViewportView(tblStock);
        tblStock.getColumnModel().getColumn(2).setPreferredWidth(10);
        tblStock.getColumnModel().getColumn(3).setPreferredWidth(10);
        tblStock.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblStock.getColumnModel().getColumn(7).setPreferredWidth(20);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel3.setText("Total Purchase value");

        lblTotalValue.setFont(new java.awt.Font("Times New Roman", 1, 18));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtSupplierName.setEnabled(false);
        txtSupplierName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSupplierNameKeyPressed(evt);
            }
        });

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

        txtProName.setEnabled(false);
        txtProName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProNameKeyPressed(evt);
            }
        });

        txtGenericName.setEnabled(false);
        txtGenericName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGenericNameKeyPressed(evt);
            }
        });

        chkAllItem.setText("All Items");
        chkAllItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAllItemActionPerformed(evt);
            }
        });

        chkSupName.setText("By Suppler Name");
        chkSupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSupNameActionPerformed(evt);
            }
        });

        chkProduct.setText("By Product Name");
        chkProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkProductActionPerformed(evt);
            }
        });

        chkGeneric.setText("By Generic Name");
        chkGeneric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGenericActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAllItem)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkSupName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkProduct)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkGeneric)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGenericName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 742, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel1, btnSearch});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(chkAllItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSupName)
                    .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkProduct)
                            .addComponent(txtProName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkGeneric)
                            .addComponent(txtGenericName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel1, btnSearch});

        paginationPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout paginationPaneLayout = new javax.swing.GroupLayout(paginationPane);
        paginationPane.setLayout(paginationPaneLayout);
        paginationPaneLayout.setHorizontalGroup(
            paginationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paginationPaneLayout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(newJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370))
        );
        paginationPaneLayout.setVerticalGroup(
            paginationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paginationPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paginationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void txtSupplierNameKeyPressed(java.awt.event.KeyEvent evt) {                                           
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            FrmSearchBox frmSearchBox = new FrmSearchBox(this);
            frmSearchBox.setValue("s.supplier_name", "supplierSearch");
            frmSearchBox.setVisible(true);
            frmSearchBox.setLocation(200, 300);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {         
            setSearchData();
        }
    }                                          

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (chkAllItem.isSelected()) {
            setSearchData();
        } else if (chkSupName.isSelected()) {
            if (txtSupplierName.getText() != null && !txtSupplierName.getText().isEmpty()) {
                setSearchData();
            }
        } else if (chkProduct.isSelected()) {
            if (txtProName.getText() != null && !txtProName.getText().isEmpty()) {
                setSearchData();
            }
        }
    }                                         

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void txtProNameKeyPressed(java.awt.event.KeyEvent evt) {                                      
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            FrmSearchBox frmSearchBox = new FrmSearchBox(this);
            frmSearchBox.setValue("p.pro_name", "productSearch");
            frmSearchBox.setVisible(true);
            frmSearchBox.setLocation(200, 300);
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {         
            setSearchData();
        }
    }                                     

    private void txtGenericNameKeyPressed(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void chkSupNameActionPerformed(java.awt.event.ActionEvent evt) {                                           
        txtSupplierName.setEnabled(true);
        chkAllItem.setSelected(false);
    }                                          

    private void chkProductActionPerformed(java.awt.event.ActionEvent evt) {                                           
        txtProName.setEnabled(true);
        chkAllItem.setSelected(false);
    }                                          

    private void chkGenericActionPerformed(java.awt.event.ActionEvent evt) {                                           
        txtGenericName.setEnabled(true);
        chkAllItem.setSelected(false);
    }                                          

    private void chkAllItemActionPerformed(java.awt.event.ActionEvent evt) {                                           
        chkSupName.setSelected(false);
        chkProduct.setSelected(false);
        txtSupplierName.setEnabled(false);
        txtProName.setEnabled(false);
        txtSupplierName.setText("");
        txtProName.setText("");

    }                                          

    private void setSearchData() {
        int supId = getSupplierId(); 
        int productId = getProductId();
        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.getDataVector().removeAllElements();
        tblStock.repaint();
        Map<String, Object> dataMap = productDAO.getProDetailsBySuppId(chkAllItem.isSelected(), supId, productId, pageSize, startingRowNo, 0);
        Vector<ProductBatchProxy> resBatch = (Vector<ProductBatchProxy>) dataMap.get("productDetails");
        totalRecords = (int) dataMap.get("totalRecords");
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
        long totalValue = 0;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        if (resBatch != null && !resBatch.isEmpty()) {
            for (Iterator<ProductBatchProxy> it = resBatch.iterator(); it.hasNext();) {
                ProductBatchProxy proxy = it.next(); 
                totalValue = totalValue + proxy.getPurchaseValue();
                model.addRow(new Object[]{proxy.getProductName(), proxy.getSupplierName(), proxy.getBatchId(), proxy.getUnitSize(),
                    StringFormatter.formatToRupees(proxy.getPurchaseValue()),
                    StringFormatter.formatToRupees(proxy.getRetailPrice()), 
                    StringFormatter.formatToRupees(proxy.getWholeSalePrice()), 
                    proxy.getUnitInStock(), proxy.getExpireDate()});
                renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
                tblStock.getColumnModel().getColumn(1).setCellRenderer(renderer);
                tblStock.getColumnModel().getColumn(2).setCellRenderer(renderer);
                tblStock.getColumnModel().getColumn(3).setCellRenderer(renderer);
                tblStock.getColumnModel().getColumn(4).setCellRenderer(renderer);
                tblStock.getColumnModel().getColumn(5).setCellRenderer(renderer);
                
            }
        }
        lblTotalValue.setText(StringFormatter.formatToRupees(totalValue));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox chkAllItem;
    private javax.swing.JCheckBox chkGeneric;
    private javax.swing.JCheckBox chkProduct;
    private javax.swing.JCheckBox chkSupName;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalValue;
    private pharmasoft.ui.PaginationBox newJPanel1;
    private javax.swing.JPanel paginationPane;
    private javax.swing.JTable tblStock;
    public javax.swing.JTextField txtGenericName;
    public javax.swing.JTextField txtProName;
    public javax.swing.JTextField txtSupplierName;
    // End of variables declaration                   

    public void addPaginationaction(){
        SysAction sysAction = new SysAction();
        JPanel pagination = (JPanel) paginationPane.getComponent(0);
        if (pagination != null) {
            Component paginCom[] = pagination.getComponents();
            for (int i = 0; i < paginCom.length; i++) {
                Component component = paginCom[i];
                if ("btnNext".equals(component.getName())) {
                    btnNext = (JButton) component;
                    btnNext.addActionListener(sysAction);
                } else if ("btnPrevious".equals(component.getName())){
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
        setSearchData();
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
        setSearchData();
    }

    @Override
    public void navigateToFirst() {
        startingRowNo = 1;
        currentPage = 1;
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);
        btnPrevious.setEnabled(false);
        btnFirst.setEnabled(false);
        setSearchData();
    }

    @Override
    public void navigateToLast() {
        startingRowNo = ((noOfPages - 1) * pageSize) + 1;
        currentPage = noOfPages;
        btnFirst.setEnabled(true);
        btnPrevious.setEnabled(true);
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
        setSearchData();
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
}
