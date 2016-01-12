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

import pharmasoft.ui.util.UiSupliment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCashierOrder extends JInternalFrame {
	private JTable tblTrans;

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
	public FrmCashierOrder(Dimension innerPannel) {
		initComponents();
        Dimension localFrm = UiSupliment.getFormLocation(innerPannel, this.getSize());
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setIconifiable(true);
        this.setLocation((int) localFrm.getWidth(), (int) localFrm.getHeight());

	}

	private void initComponents() {
		setBounds(100, 100, 825, 443);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		tblTrans = new JTable();
		tblTrans.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "RECIPT ID", "RECIPT DATE", "DISCOUNT", "TOTAL AMOUNT"
                }
            ));
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
	
	public void refrshTrnTable() {
		DefaultTableModel model = (DefaultTableModel) tblTrans.getModel();
		model.getDataVector().removeAllElements();
		tblTrans.repaint();

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        model.addRow(new Object[]{"0001", "2015-01-15", "5", "545.00"});
//		if (transList != null && !transList.isEmpty()) {
//			for (Iterator<RetailSale> it = transList.iterator(); it.hasNext();) {
//				RetailSale proxy = it.next();
//				model.addRow(new Object[] { proxy.getReciptId(), proxy.getReciptDate(), proxy.getDiscount(),
//						StringFormatter.formatToRupees(proxy.getTotalAmount()) });
//				renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
//				tblTrans.getColumnModel().getColumn(0).setCellRenderer(renderer);
//				tblTrans.getColumnModel().getColumn(1).setCellRenderer(renderer);
//				tblTrans.getColumnModel().getColumn(2).setCellRenderer(renderer);
//				tblTrans.getColumnModel().getColumn(3).setCellRenderer(renderer);
//
//			}
//		}
	}
}
