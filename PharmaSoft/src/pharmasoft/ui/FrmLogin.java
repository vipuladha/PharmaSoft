/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmLogin1.java
 *
 * Created on Jul 13, 2011, 11:27:11 AM
 */
package pharmasoft.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pharmasoft.db.dao.LoginDAO;
import pharmasoft.db.util.DBAccess;
import pharmasoft.ui.util.UiSupliment;

/**
 *
 * @author eranga
 */
public class FrmLogin extends javax.swing.JInternalFrame {
    
    FrmMainForm frmMainForm;
    DBAccess dataAccess;
    String UserName;
    
    public String getUserName() {
        return UserName;
    }
    
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
    public FrmMainForm getFrmMainForm() {
        return frmMainForm;
    }
    
    private void setFrmMainForm(FrmMainForm frmMainForm) {
        this.frmMainForm = frmMainForm;
    }

    /** Creates new form FrmLogin1 */
    public FrmLogin(Dimension innerPannel, FrmMainForm mainFrame) {
    	frmMainForm = mainFrame;
        initComponents();
        this.setClosable(false);
        this.setMaximizable(false);
        this.txtUserName.requestFocus();
        this.txtUserName.setText("ADMIN");
        this.txtPassword.setText("123");

        Dimension localDimen = UiSupliment.getFormLocation(innerPannel, this.getSize());
        this.setLocation((int) localDimen.getWidth(), (int) localDimen.getHeight());
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
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnExt = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setTitle("Login");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(207, 217, 222));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBackground(new java.awt.Color(240, 242, 240));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });
        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserNameKeyPressed(evt);
            }
        });

        jLabel1.setText("User Name");
        jLabel2.setText("Password");

        btnLogin.setText("Login");
        btnLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        btnExt.setText("Exit");
        btnExt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtActionPerformed(evt);
            }
        });
        btnExt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExtKeyPressed(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(btnExt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExt)
                    .addComponent(btnLogin))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) { 
    	userLogging();
    }                                        
    
    private void btnExtActionPerformed(java.awt.event.ActionEvent evt) {                                       
        this.dispose();
        this.getFrmMainForm().dispose();
    }                                      
    
    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {                                      
    	userLogging();
    }                                   
    
    private void btnExtKeyPressed(java.awt.event.KeyEvent evt) {                                  
        this.dispose();
        this.getFrmMainForm().dispose();
    }                                 
    
    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	userLogging();
        }
    }                                      
    
    private void txtUserNameKeyPressed(java.awt.event.KeyEvent evt) {                                       
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	userLogging();
        }
    }                                      

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {                                      
        txtUserName.setText(txtUserName.getText().toUpperCase());
    }  
    
    
    private void userLogging() {
        LoginDAO loginDAO = new LoginDAO();
        String result = loginDAO.AuthenticateUserLogin(txtUserName.getText(), txtPassword.getText());
        if ("SUCCESS".equals(result)) {
        	frmMainForm.setJMenuBar(frmMainForm.getJMenuBar());
        	frmMainForm.getContentPane().add(frmMainForm.getLogoutPane(), BorderLayout.PAGE_START);
            setUserName(txtUserName.getText());
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            txtUserName.setText("");
            txtPassword.setText("");
            txtUserName.requestFocus();
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnExt;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration                   
}
