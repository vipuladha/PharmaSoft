package pharmasoft.ui.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import pharmasoft.db.dao.LoginDAO;
import pharmasoft.ui.util.SplashScreen;
import pharmasoft.util.themes.GUIProperties;

public class LoginFrmView extends JFrame {
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnExit;
    private static Properties dbProperties;
    private static SplashScreen splashScreen = null;
    
    public static final  Cursor WAIT    = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
    public static final  Cursor DEFAULT = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        try {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
			setUserLookAndFeel();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						splashScreen = new SplashScreen ("/images/ScreenJPG.jpg", true);
						splashScreen.setCursor(WAIT);
						splashScreen.showStatus("PHARMASOFT is Initializing... Please wait.");
						LoginFrmView frame = new LoginFrmView();
						frame.setVisible(true);
						splashScreen.showStatus("Logging on....");

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginFrmView() {
		setResizable(false);
		setTitle("PharmaSoft Login");
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.txtUserName.setText("ADMIN");
        this.txtPassword.setText("123");
		createEvents();

	}

	private void initComponents() {
		setBounds(100, 100, 374, 206);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		
		JLabel lblUserName = new JLabel("User Name : ");
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		
		JLabel lblPasswoard = new JLabel("Password : ");
		
		txtPassword = new JPasswordField();
		
		btnLogin = new JButton("Login");
		btnLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnExit = new JButton("Exit");
		btnExit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUserName)
						.addComponent(lblPasswoard))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPassword)
						.addComponent(txtUserName, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPasswoard)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnExit))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
	}

	private void createEvents() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userLogging();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        System.exit(0);
			}
		});
		
	}
	
    private void userLogging() {
        LoginDAO loginDAO = new LoginDAO();
        String result = loginDAO.AuthenticateUserLogin(txtUserName.getText(), txtPassword.getText());
        if ("SUCCESS".equals(result)) {
            this.dispose();
            MainFrmView mainFrm = new MainFrmView(txtUserName.getText());
            mainFrm.setVisible(true);
			splashScreen.setCursor(DEFAULT);
			splashScreen.close();
        } else {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            txtUserName.setText("");
            txtPassword.setText("");
            txtUserName.requestFocus();
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
