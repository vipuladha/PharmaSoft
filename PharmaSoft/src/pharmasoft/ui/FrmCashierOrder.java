package pharmasoft.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import pharmasoft.db.dao.TransactionDAO;
import pharmasoft.db.model.RetailSale;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.StringFormatter;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class FrmCashierOrder extends JInternalFrame {
	
	private JTable tblTrans;
    private TransactionDAO transDAO;
    private JDesktopPane innerPanel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmCashierOrder frame = new FrmCashierOrder();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmCashierOrder(JDesktopPane innerPannel) {
		initComponents();
        transDAO = new TransactionDAO();
        innerPanel = innerPannel;
        Dimension localFrm = UiSupliment.getFormLocation(innerPannel.getSize(), this.getSize());
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setLocation((int) localFrm.getWidth(), (int) localFrm.getHeight());
        createEvents();

	}

	private void initComponents() {
		setBounds(100, 100, 825, 443);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		tblTrans = new JTable();
		tblTrans.setFocusable(false);
		tblTrans.setRowSelectionAllowed(true);
		tblTrans.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "RECIPT ID", "RECIPT DATE", "DISCOUNT", "TOTAL AMOUNT"
                }
            ) { 
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		});
		jScrollPane.setViewportView(tblTrans);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refrshTrnTable();
			}
		});
		btnRefresh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(595, Short.MAX_VALUE)
					.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
	}
	
	private void createEvents() {
		tblTrans.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			     if (e.getClickCount() == 2) { 
			        	FrmViewRetailTransaction frmViewRtTrans = new FrmViewRetailTransaction(innerPanel.getSize());
			        	innerPanel.add(frmViewRtTrans);	        	
						frmViewRtTrans.setLocation((int) getLocalFrmDimension(frmViewRtTrans).getWidth(), (int) getLocalFrmDimension(frmViewRtTrans).getHeight());
						frmViewRtTrans.show();
						
			            System.out.println(tblTrans.getValueAt(tblTrans.getSelectedRow(), 0).toString());
			     }
			   }
			});		
	}
	
	public void refrshTrnTable() {
		DefaultTableModel model = (DefaultTableModel) tblTrans.getModel();
		model.getDataVector().removeAllElements();
		tblTrans.repaint();

		Vector<RetailSale> transList = transDAO.getTransactions();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        if (transList != null && !transList.isEmpty()) {
            for (Iterator<RetailSale> it = transList.iterator(); it.hasNext();) {
                RetailSale proxy = it.next();
                model.addRow(new Object[]{proxy.getReciptId(), proxy.getReciptDate(), proxy.getDiscount(), 
                    StringFormatter.formatToRupees(proxy.getTotalAmount())});
                renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
                tblTrans.getColumnModel().getColumn(0).setCellRenderer(renderer);
                tblTrans.getColumnModel().getColumn(1).setCellRenderer(renderer);
                tblTrans.getColumnModel().getColumn(2).setCellRenderer(renderer);
                tblTrans.getColumnModel().getColumn(3).setCellRenderer(renderer);

            }
        }
        
	}
	
	private Dimension getLocalFrmDimension(JInternalFrame frame) {
		Dimension localFrm = null;
		if (innerPanel != null)
			localFrm = UiSupliment.getFormLocation(innerPanel.getSize(), frame.getSize());
		return localFrm;
	}
}
