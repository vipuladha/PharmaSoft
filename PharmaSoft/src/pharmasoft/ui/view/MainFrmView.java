package pharmasoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import pharmasoft.ui.FrmAddNewCustomer;
import pharmasoft.ui.FrmAddNewProduct;
import pharmasoft.ui.FrmAddNewProductBatch;
import pharmasoft.ui.FrmAddNewSupplier;
import pharmasoft.ui.FrmCashierOrder;
import pharmasoft.ui.FrmPurchaseOrder;
import pharmasoft.ui.FrmStockTaking;
import pharmasoft.ui.FrmTransaction;
import pharmasoft.ui.FrmViewRetailTransaction;
import pharmasoft.ui.FrmViewWholeSaleTransaction;
import pharmasoft.ui.FrmWholeSaleTransaction;
import pharmasoft.ui.util.UiSupliment;
import pharmasoft.util.themes.GUIProperties;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class MainFrmView extends JFrame {

	private JPanel contentPane;
	private JLabel lblLoggingUser;
	private JMenuItem mntmRetailTransaction;
	private JDesktopPane innerPannel;
	private JMenuItem mntmViewTransaction;
	private JMenuItem mntCashierOrder;
	private JMenuItem mntmWholeSaleTransaction;
	
	JInternalFrame innerFrame = null;
	FrmTransaction frmTransaction = null;
	FrmViewRetailTransaction frmViewRtTrans = null;
	FrmCashierOrder frmCashierOrder = null;
	FrmWholeSaleTransaction frmWSTrans = null;
	FrmViewWholeSaleTransaction frmViewWSTrans = null;
	FrmStockTaking frmstockTaking = null;
	FrmPurchaseOrder frmPurchaseOdr = null;
	FrmAddNewCustomer frmAddNewCus = null;
	FrmAddNewSupplier frmAddNewSupp = null;
	FrmAddNewProduct frmAddNewPro = null;
	FrmAddNewProductBatch frmAddNewProbatch = null;
	private JButton btnLogout;
			

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//        try {
//            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
//			setUserLookAndFeel();
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						MainFrmView frame = new MainFrmView();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//	}

	/**
	 * Create the frame.
	 */
	public MainFrmView(String userName) {
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		lblLoggingUser.setText(userName);
		createEvents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		SysAction sysAction = new SysAction();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRetailSale = new JMenu("Retail Sale");
		menuBar.add(mnRetailSale);
		
		mntmRetailTransaction = new JMenuItem("Retail Transaction");
		mntmRetailTransaction.addActionListener(sysAction);
		mnRetailSale.add(mntmRetailTransaction);
		
		mntmViewTransaction = new JMenuItem("View Transaction");
		mntmViewTransaction.addActionListener(sysAction);
		mnRetailSale.add(mntmViewTransaction);
		
		mntCashierOrder = new JMenuItem("Cashier Orders");
		mntCashierOrder.addActionListener(sysAction);
		mnRetailSale.add(mntCashierOrder);
		
		JMenu mnWholeSale = new JMenu("Whole Sale");
		menuBar.add(mnWholeSale);
		
		mntmWholeSaleTransaction = new JMenuItem("Whole Sale Transaction");
		mntmWholeSaleTransaction.addActionListener(sysAction);
		mnWholeSale.add(mntmWholeSaleTransaction);
		
		JMenuItem mntmViewWsTransaction = new JMenuItem("View W/S Transaction");
		mntmViewWsTransaction.addActionListener(sysAction);
		mnWholeSale.add(mntmViewWsTransaction);
		
		JMenu mnInventory = new JMenu("Inventory");
		menuBar.add(mnInventory);
		
		JMenuItem mntmStockTaking = new JMenuItem("Stock Taking");
		mntmStockTaking.addActionListener(sysAction);
		mnInventory.add(mntmStockTaking);
		
		JMenuItem mntmPurchasingOrder = new JMenuItem("Purchasing Order");
		mntmPurchasingOrder.addActionListener(sysAction);
		mnInventory.add(mntmPurchasingOrder);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmNewCustomerdoctor = new JMenuItem("New Customer (Doctor)");
		mntmNewCustomerdoctor.addActionListener(sysAction);
		mnSettings.add(mntmNewCustomerdoctor);
		
		JMenuItem mntmNewSuppliers = new JMenuItem("New Suppliers");
		mntmNewSuppliers.addActionListener(sysAction);
		mnSettings.add(mntmNewSuppliers);
		
		JMenuItem mntmAddProducts = new JMenuItem("Add Products");
		mntmAddProducts.addActionListener(sysAction);
		mnSettings.add(mntmAddProducts);
		
		JMenuItem mntmAddNewProduct = new JMenuItem("Add New Product Batch");
		mntmAddNewProduct.addActionListener(sysAction);
		mnSettings.add(mntmAddNewProduct);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnLogout = new JButton("");
		btnLogout.setIcon(new ImageIcon(MainFrmView.class.getResource("/images/logout[1].gif")));
		
		lblLoggingUser = new JLabel("");
		
		JLabel lblLoggingAs = new JLabel("Login As :");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(610, Short.MAX_VALUE)
					.addComponent(lblLoggingAs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLoggingUser, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoggingUser, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoggingAs, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addContainerGap(23, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		innerPannel = new JDesktopPane();
		contentPane.add(innerPannel, BorderLayout.CENTER);
		
	}

	private void createEvents() {	
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrmView().setVisible(true);
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if (innerFrame != null && (innerFrame.isIcon() || innerFrame.isShowing())) {
					Dimension localFrm = UiSupliment.getFormLocation(innerPannel.getSize(), innerFrame.getSize());
					innerFrame.setLocation((int) localFrm.getWidth(), (int) localFrm.getHeight());
				}
			}
		});
		
	}
	
    class SysAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JMenuItem jMnItem = (JMenuItem) e.getSource();
        	String appStr = jMnItem.getText();        	
			try {
				if ("Retail Transaction".equals(appStr)) {
					if (frmTransaction != null && (frmTransaction.isIcon() || frmTransaction.isShowing())) {
						frmTransaction.setIcon(false);
						frmTransaction.toFront();
						frmTransaction.setSelected(true);
					} else {
						frmTransaction = new FrmTransaction(innerPannel.getSize());
						innerPannel.add(frmTransaction);

						frmTransaction.setLocation((int) getLocalFrmDimension(frmTransaction).getWidth(), (int) getLocalFrmDimension(frmTransaction).getHeight());
						frmTransaction.show();
					}
					innerFrame = frmTransaction;
				} else if ("View Transaction".equals(appStr)) {
					if (frmViewRtTrans != null && (frmViewRtTrans.isIcon() || frmViewRtTrans.isShowing())) {
						frmViewRtTrans.setIcon(false);
						frmViewRtTrans.toFront();
						frmViewRtTrans.setSelected(true);
					} else {
						frmViewRtTrans = new FrmViewRetailTransaction(innerPannel.getSize());
						innerPannel.add(frmViewRtTrans);

						frmViewRtTrans.setLocation((int) getLocalFrmDimension(frmViewRtTrans).getWidth(), (int) getLocalFrmDimension(frmViewRtTrans).getHeight());
						frmViewRtTrans.show();
					}
					innerFrame = frmViewRtTrans;
				} else if ("Cashier Orders".equals(appStr)) {
					if (frmCashierOrder != null && (frmCashierOrder.isIcon() || frmCashierOrder.isShowing())) {
						frmCashierOrder.setIcon(false);
						frmCashierOrder.toFront();
						frmCashierOrder.setSelected(true);
					} else {
						frmCashierOrder = new FrmCashierOrder(innerPannel.getSize());
						innerPannel.add(frmCashierOrder);

						frmCashierOrder.setLocation((int) getLocalFrmDimension(frmCashierOrder).getWidth(), (int) getLocalFrmDimension(frmCashierOrder).getHeight());
						frmCashierOrder.show();
					}
					innerFrame = frmViewRtTrans;
				} else if ("Whole Sale Transaction".equals(appStr)){
					if (frmWSTrans != null && (frmWSTrans.isIcon() || frmWSTrans.isShowing())) {
						frmWSTrans.setIcon(false);
						frmWSTrans.toFront();
						frmWSTrans.setSelected(true);
					} else {
						frmWSTrans = new FrmWholeSaleTransaction(innerPannel.getSize());
						innerPannel.add(frmWSTrans);

						frmWSTrans.setLocation((int) getLocalFrmDimension(frmWSTrans).getWidth(), (int) getLocalFrmDimension(frmWSTrans).getHeight());
						frmWSTrans.show();
					}
					innerFrame = frmWSTrans;
				} else if ("View W/S Transaction".equals(appStr)){
					if (frmViewWSTrans != null && (frmViewWSTrans.isIcon() || frmViewWSTrans.isShowing())) {
						frmViewWSTrans.setIcon(false);
						frmViewWSTrans.toFront();
						frmViewWSTrans.setSelected(true);
					} else {
						frmViewWSTrans = new FrmViewWholeSaleTransaction(innerPannel.getSize());
						innerPannel.add(frmViewWSTrans);

						frmViewWSTrans.setLocation((int) getLocalFrmDimension(frmViewWSTrans).getWidth(), (int) getLocalFrmDimension(frmViewWSTrans).getHeight());
						frmViewWSTrans.show();
					}
					innerFrame = frmViewWSTrans;
				} else if ("Stock Taking".equals(appStr)){
					if (frmstockTaking != null && (frmstockTaking.isIcon() || frmstockTaking.isShowing())) {
						frmstockTaking.setIcon(false);
						frmstockTaking.toFront();
						frmstockTaking.setSelected(true);
					} else {
						frmstockTaking = new FrmStockTaking(innerPannel.getSize());
						innerPannel.add(frmstockTaking);

						frmstockTaking.setLocation((int) getLocalFrmDimension(frmstockTaking).getWidth(), (int) getLocalFrmDimension(frmstockTaking).getHeight());
						frmstockTaking.show();
					}
					innerFrame = frmstockTaking;
				} else if ("Purchasing Order".equals(appStr)){
					if (frmPurchaseOdr != null && (frmPurchaseOdr.isIcon() || frmPurchaseOdr.isShowing())) {
						frmPurchaseOdr.setIcon(false);
						frmPurchaseOdr.toFront();
						frmPurchaseOdr.setSelected(true);
					} else {
						frmPurchaseOdr = new FrmPurchaseOrder(innerPannel.getSize());
						innerPannel.add(frmPurchaseOdr);

						frmPurchaseOdr.setLocation((int) getLocalFrmDimension(frmPurchaseOdr).getWidth(), (int) getLocalFrmDimension(frmPurchaseOdr).getHeight());
						frmPurchaseOdr.show();
					}
					innerFrame = frmPurchaseOdr;
				} else if ("New Customer (Doctor)".equals(appStr)){
					if (frmAddNewCus != null && (frmAddNewCus.isIcon() || frmAddNewCus.isShowing())) {
						frmAddNewCus.setIcon(false);
						frmAddNewCus.toFront();
						frmAddNewCus.setSelected(true);
					} else {
						frmAddNewCus = new FrmAddNewCustomer(innerPannel.getSize());
						innerPannel.add(frmAddNewCus);

						frmAddNewCus.setLocation((int) getLocalFrmDimension(frmAddNewCus).getWidth(), (int) getLocalFrmDimension(frmAddNewCus).getHeight());
						frmAddNewCus.show();
					}
					innerFrame = frmAddNewCus;
				} else if ("New Suppliers".equals(appStr)){
					if (frmAddNewSupp != null && (frmAddNewSupp.isIcon() || frmAddNewSupp.isShowing())) {
						frmAddNewSupp.setIcon(false);
						frmAddNewSupp.toFront();
						frmAddNewSupp.setSelected(true);
					} else {
						frmAddNewSupp = new FrmAddNewSupplier(innerPannel.getSize());
						innerPannel.add(frmAddNewSupp);

						frmAddNewSupp.setLocation((int) getLocalFrmDimension(frmAddNewSupp).getWidth(), (int) getLocalFrmDimension(frmAddNewSupp).getHeight());
						frmAddNewSupp.show();
					}
					innerFrame = frmAddNewSupp;
				} else if ("Add Products".equals(appStr)){
					if (frmAddNewPro != null && (frmAddNewPro.isIcon() || frmAddNewPro.isShowing())) {
						frmAddNewPro.setIcon(false);
						frmAddNewPro.toFront();
						frmAddNewPro.setSelected(true);
					} else {
						frmAddNewPro = new FrmAddNewProduct(innerPannel.getSize());
						innerPannel.add(frmAddNewPro);

						frmAddNewPro.setLocation((int) getLocalFrmDimension(frmAddNewPro).getWidth(), (int) getLocalFrmDimension(frmAddNewPro).getHeight());
						frmAddNewPro.show();
					}
					innerFrame = frmAddNewPro;
				} else if ("Add New Product Batch".equals(appStr)){
					if (frmAddNewProbatch != null && (frmAddNewProbatch.isIcon() || frmAddNewProbatch.isShowing())) {
						frmAddNewProbatch.setIcon(false);
						frmAddNewProbatch.toFront();
						frmAddNewProbatch.setSelected(true);
					} else {
						frmAddNewProbatch = new FrmAddNewProductBatch(innerPannel.getSize());
						innerPannel.add(frmAddNewProbatch);

						frmAddNewProbatch.setLocation((int) getLocalFrmDimension(frmAddNewProbatch).getWidth(), (int) getLocalFrmDimension(frmAddNewProbatch).getHeight());
						frmAddNewProbatch.show();
					}
					innerFrame = frmAddNewProbatch;
				}

			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
        }
        	
    }

	private Dimension getLocalFrmDimension(JInternalFrame frame) {
		Dimension localFrm = null;
		if (innerPannel != null)
			localFrm = UiSupliment.getFormLocation(innerPannel.getSize(), frame.getSize());
		return localFrm;
	}

}
