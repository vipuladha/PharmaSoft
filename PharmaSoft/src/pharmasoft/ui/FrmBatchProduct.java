/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmBatchProduct.java
 *
 * Created on Mar 4, 2015, 12:33:25 AM
 */
package pharmasoft.ui;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import pharmasoft.db.dao.ProductDAO;
import pharmasoft.db.proxyClasses.ProductBatchProxy;
import pharmasoft.ui.util.ListTableModel;
import pharmasoft.util.StringFormatter;

/**
 *
 * @author Vipula
 */
public class FrmBatchProduct extends javax.swing.JDialog {
    
    JInternalFrame internalFrame;
    ProductDAO productDAO;

    public JInternalFrame getInternalFrame() {
        return internalFrame;
    }

    public void setInternalFrame(JInternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    /** Creates new form FrmBatchProduct */
    public FrmBatchProduct(Vector batchProduct) {
        initComponents();
        this.setSize(600, 200);
//        this.setModal(true);setDataForModelTable
        this.setDataForModelTable(batchProduct);
        this.setInternalFrame(internalFrame);
        
    }
    
    public void setDataForModelTable(Vector batchProduct) {
//        try {
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columns = metaData.getColumnCount();
//            ArrayList<String> columnNames = new ArrayList<String>();
//            for (int i = 1; i <= columns; i++) {
//                String columnName = metaData.getColumnName(i);
//                String columnLabel = metaData.getColumnLabel(i);
//
//                if (columnLabel.equals(columnName)) {
//                    columnNames.add(formatColumnName(columnName));
//                } else {
//                    columnNames.add(columnLabel);
//                }
//            }

//            ListTableModel model = new ListTableModel(columnNames);
//            model.setModelEditable(false);
            
            

            //  Assign the class of each column

//            for (int i = 1; i <= columns; i++) {
//                try {
//                    String className = metaData.getColumnClassName(i);
//                    model.setColumnClass(i - 1, Class.forName(className));
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }

            //  Get row data

//            ArrayList<List> data = new ArrayList<List>();
//
//            while (resultSet.next()) {
//                ArrayList<Object> row = new ArrayList<Object>(columns);
//                Vector<Object> row = new Vector<Object>();
//                Object o = null;
//                for (int i = 1; i <= columns; i++) {
//                    String columnName = columnNames.get(i - 1);
//                    if (columnName.contains("PRICE")) {
//                        Object res = resultSet.getObject(i);
//                        o = StringFormatter.formatToRupees(Long.parseLong(res.toString()));
//                    } else {
//                        o = resultSet.getObject(i);
//                    }
//
//                    if (o != null) {
//                        row.add(o);
//                    }
//                }
            for (Iterator it = batchProduct.iterator(); it.hasNext();) {
                ProductBatchProxy proxy = (ProductBatchProxy) it.next();
                DefaultTableModel model = (DefaultTableModel) tblBatchProducts.getModel();
                 model.addRow(new Object[]{proxy.getBatchId(), StringFormatter.formatToRupees(proxy.getRetailPrice()),
                 StringFormatter.formatToRupees(proxy.getProductId()), proxy.getUnitInStock(), proxy.getExpireDate()});
                                 
//                model.addRow(new Object[]{2, StringFormatter.formatToRupees(200),
//                 StringFormatter.formatToRupees(550), 5, null});

            }
//                
//                DefaultTableModel model = (DefaultTableModel) tblBatchProducts.getModel();
//                model.addRow(row);
//                data.add(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    public static String formatColumnName(String columnName) {
        if (columnName.length() < 3) {
            return columnName;
        }

        StringBuffer buffer = new StringBuffer(columnName);
        boolean isPreviousLowerCase = false;

        for (int i = 1; i < buffer.length(); i++) {
            boolean isCurrentUpperCase = Character.isUpperCase(buffer.charAt(i));

            if (isCurrentUpperCase && isPreviousLowerCase) {
                buffer.insert(i, " ");
                i++;
            }

            isPreviousLowerCase = !isCurrentUpperCase;
        }

        return buffer.toString().replaceAll("_", " ");
    }
    
      public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

//                FrmBatchProduct fmf = new FrmBatchProduct();
//
//                fmf.setVisible(true);

//                FrmLogin frmLogin1 = new FrmLogin(JInnerPannel.getSize(), fmf);
//                JInnerPannel.add(frmLogin1);
//                frmLogin1.setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBatchProducts = new javax.swing.JTable();

        tblBatchProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BATCH ID", "RETAIL PRICE", "WHOLE SALE PRICE", "UNIT IN STOCK", "EXPIRE DATE"
            }
        ));
        jScrollPane1.setViewportView(tblBatchProducts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBatchProducts;
    // End of variables declaration                   
 
}
