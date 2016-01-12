package pharmasoft.ui.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import pharmasoft.ui.view.MainFrmView;

public class SplashScreen extends JWindow
{
    JLabel StatusBar;

    public static void main (String [] args)
    {
        new SplashScreen ("/images/ScreenJPG.jpg", true);
    }


    public SplashScreen (String imageName, boolean manualClose)
    {
        this (new ImageIcon(SplashScreen.class.getResource(imageName)));

        if (!manualClose)
            return;

        getContentPane().addMouseListener (new SplashMouse());
    }


    class SplashMouse extends java.awt.event.MouseAdapter
    {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            SplashScreen.this.close();
        }
    }


    public SplashScreen(ImageIcon CoolPicture)
    {
        super(new Frame());

        // Create a JPanel so we can use a BevelBorder
        JPanel PanelForBorder = new JPanel(new BorderLayout());
        StatusBar = new JLabel("...", SwingConstants.CENTER);
        
        StatusBar.setBackground(Color.white);
        PanelForBorder.setBackground(Color.white);
//      PanelForBorder.setLayout(new BorderLayout());
        PanelForBorder.add(new JLabel(CoolPicture), BorderLayout.CENTER);
        PanelForBorder.add(StatusBar,BorderLayout.SOUTH);
        PanelForBorder.setBorder(new BevelBorder(BevelBorder.RAISED));

 // JC: add(PanelForBorder);
        getContentPane().add(PanelForBorder);
        pack();

        // Plonk it on center of screen
        Dimension WindowSize = getSize();
        Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((ScreenSize.width - WindowSize.width)/2,
                  (ScreenSize.height - WindowSize.height)/2,
                  WindowSize.width, WindowSize.height);
        setVisible(true);
    }

    public void showStatus(String CurrentStatus)
    {
        try
        {
            // Update Splash-Screen's status bar in AWT thread
            SwingUtilities.invokeLater(new UpdateStatus(CurrentStatus));
        } catch(Exception e) {e.printStackTrace();}
    }

    public void close()
    {
        //try
        //{
            // Close and dispose Window in AWT thread
            SwingUtilities.invokeLater(new CloseSplashScreen());
        //} catch(Exception e) {e.printStackTrace();}
    }

    class UpdateStatus implements Runnable
    {
        String NewStatus;
        public UpdateStatus(String Status){NewStatus=Status;}
        public void run(){StatusBar.setText(NewStatus);}
    }

    class CloseSplashScreen implements Runnable
    {
        public void run(){setVisible(false);dispose();}
    }
}
