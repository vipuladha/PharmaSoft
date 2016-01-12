package pharmasoft.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.plaf.DesktopPaneUI;

import pharmasoft.util.themes.GUIProperties;

public class FrmMainForm extends JFrame {

    public static GUIProperties guiProps = new GUIProperties();
    private static Properties dbProperties;
    static JDesktopPane innerPannel;
	FrmTransaction frmTransaction = null;
	FrmViewRetailTransaction frmViewTran = null;
	
    
	public FrmMainForm(){
		initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setLocation(20, 20);
	}

	public void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        setTitle("Pharma Soft v_1.0");
        setForeground(java.awt.Color.white);
        setLocationByPlatform(true);
        innerPannel = new JDesktopPane();
        add(innerPannel);

	}
	
	public JMenuBar getJMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		SysAction sysAction = new SysAction();
	
	    JMenu retailSaleMenu = new JMenu("Retail Sale");
//	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    
	    JMenuItem retailSaleMenuItem = new JMenuItem("Retail Transaction");
	    retailSaleMenuItem.addActionListener(sysAction);
	    retailSaleMenu.add(retailSaleMenuItem);
	    JMenuItem viewTrnMenuItem = new JMenuItem("View Transaction");
	    viewTrnMenuItem.addActionListener(sysAction);
	    retailSaleMenu.add(viewTrnMenuItem);
	    
	    menuBar.add(retailSaleMenu);
	    
	    JMenu wholeSaleMenu = new JMenu("Whole Sale");
	    JMenuItem wholeSaleMenuItem = new JMenuItem("Whole Sale Transaction");
	    wholeSaleMenu.add(wholeSaleMenuItem);
	    JSeparator jSeparator1 = new JSeparator();
	    wholeSaleMenu.add(jSeparator1);
	    JMenuItem viewWsTrnMenuItem = new JMenuItem("View W/S Transaction");
	    wholeSaleMenu.add(viewWsTrnMenuItem);
	    
	    menuBar.add(wholeSaleMenu);
	    
	    JMenu inventoryMenu = new JMenu("Inventory");
	    JMenuItem stockMenuItem = new JMenuItem("Stock Taking");
	    inventoryMenu.add(stockMenuItem);
	    JMenuItem purchaseOrderMenuItem = new JMenuItem("PurchasingOrder");
	    inventoryMenu.add(purchaseOrderMenuItem);
	    
	    menuBar.add(inventoryMenu);
	    
	    JMenu settingsMenu = new JMenu("Settings");
	    JMenuItem newCusMenuItem = new JMenuItem("New Customer (Doctor)");
	    settingsMenu.add(newCusMenuItem);
	    JMenuItem newSuppMenuItem = new JMenuItem("New Suppliers");
	    settingsMenu.add(newSuppMenuItem);
	    JMenuItem addProMenuItem = new JMenuItem("Add Products");
	    settingsMenu.add(addProMenuItem);
	    JMenuItem addProBatchMenuItem = new JMenuItem("Add New Product Batch");
	    settingsMenu.add(addProBatchMenuItem);
	    menuBar.add(settingsMenu);
	    
	    JMenu windowMenu = new JMenu("Window");
	    menuBar.add(windowMenu);
	    
	    JMenu helpMenu = new JMenu("Help");
	    menuBar.add(helpMenu);
	    
	    
		return menuBar;
	    
	}
	
	public JPanel getLogoutPane(){
        JPanel logOutPane = new JPanel();
//        GroupLayout layout = new GroupLayout(logOutPane);
//        logOutPane.setLayout(layout);
        JLabel lblLoginAs = new JLabel("Login As : ");
        JLabel lblLoginUser = new JLabel("dfgdfgdf");
        JButton btnLogout = new JButton();
        btnLogout.setBackground(new java.awt.Color(237, 236, 235));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout[1].gif"))); 
        btnLogout.setToolTipText("You can log-out from the system");
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(logOutPane);
        logOutPane.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                .addComponent(jToolBarMain, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                .addComponent(lblLoginAs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoginUser, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        		
		return logOutPane;
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
						frmTransaction.show();
					}
				} else if ("View Transaction".equals(appStr)) {
					if (frmViewTran != null && (frmViewTran.isIcon() || frmViewTran.isShowing())) {
						frmViewTran.setIcon(false);
						frmViewTran.toFront();
						frmViewTran.setSelected(true);
					} else {
						frmViewTran = new FrmViewRetailTransaction(innerPannel.getSize());
						innerPannel.add(frmViewTran);
						frmViewTran.show();
					}
				}

			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
        }
        	
    }
	
    public static void main(String args[]) {
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
            setUserLookAndFeel();
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    FrmMainForm fmf = new FrmMainForm();

                    fmf.setVisible(true);

                    FrmLogin frmLogin1 = new FrmLogin(innerPannel.getSize(), fmf);
                    innerPannel.add(frmLogin1);
                    frmLogin1.setVisible(true);
                }
            });

        } catch (Exception e) {
        	e.printStackTrace();
        }
   
    }
    
	private void formWindowClosing(java.awt.event.WindowEvent evt) {

		int flag = JOptionPane.showConfirmDialog(this, "Do You Want Exit From PELA Invoice System ?", "System Message",
				JOptionPane.OK_CANCEL_OPTION);

		if (flag == 0) {
			this.dispose();

		}

	}
    
	public static void setUserLookAndFeel() throws Exception {

		String LookAndFeel = getDbProperties().getProperty("LookAndFeel");
		if (LookAndFeel.equals("1")) {// Metal
			UIManager.setLookAndFeel(GUIProperties.PLAF_METAL);
		} else if (LookAndFeel.equals("2")) {// Mint
			UIManager.setLookAndFeel(GUIProperties.PLAF_MINT);
		} else if (LookAndFeel.equals("3")) {// Windows
			UIManager.setLookAndFeel(GUIProperties.PLAF_WINDOWS);
		} else if (LookAndFeel.equals("4")) {// Aero
			UIManager.setLookAndFeel(GUIProperties.PLAF_AERO);
		} else if (LookAndFeel.equals("5")) {// Bernstein
			UIManager.setLookAndFeel(GUIProperties.PLAF_BERNSTEIN);// Fast
		} else if (LookAndFeel.equals("6")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_FAST);// Fast
		} else if (LookAndFeel.equals("7")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_GRAPHITE);// PLAF_GRAPHITE
		} else if (LookAndFeel.equals("8")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_HIFI);// PLAF_GRAPHITE
		} else if (LookAndFeel.equals("9")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_LUNA);// PLAF_GRAPHITE
		} else if (LookAndFeel.equals("10")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_SMART);// PLAF_GRAPHITE
		} else if (LookAndFeel.equals("11")) {
			UIManager.setLookAndFeel(GUIProperties.PLAF_NOIRE);// PLAF_GRAPHITE
		} else {
			UIManager.setLookAndFeel(GUIProperties.PLAF_ACRYL);// PLAF_GRAPHITE
		}
	}
    
    /*********Loading Properties File*******************/
    public static Properties getDbProperties() {
        /******************  Getting the Property Value From Propeyty File ************************/
        dbProperties = new Properties();
        try {
            // InputStream inputStream = this.getClass().getClassLoader().getSystemResourceAsStream("config/settings.properties");
            String currentdir = System.getProperty("user.dir");
            //System.out.println("currentdir--->" + currentdir);
            File fs = new File(currentdir + "/src/" + "config.properties");
            InputStream inputStream = new FileInputStream(fs);
            dbProperties.load(inputStream);
            if (inputStream == null) {
                //System.out.println("File Not Found----------");
            }
            inputStream.close();
        } catch (IOException _IOExc) {
            //System.out.println("getDbProperties.IOException-->" + _IOExc.getMessage());
        } finally {
            return (dbProperties);
        }
    }
}
