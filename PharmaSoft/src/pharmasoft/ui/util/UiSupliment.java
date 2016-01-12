/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.ui.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;

/**
 *
 * @author eranga
 */
public class UiSupliment {
    
    public static  BufferedImage getFrameIcon(Object ss,String iconPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ss.getClass().getResource(iconPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    
     public static Dimension getFormLocation(Dimension JInnerPanel, Dimension FormPannel) {
        Dimension dimension = new Dimension();

        int height = (int) ((JInnerPanel.getHeight() / 2) - (FormPannel.getHeight() / 2));
        int width = (int) ((JInnerPanel.getWidth() / 2) - (FormPannel.getWidth() / 2));

        dimension.setSize(width, height);
        return dimension;
    }
     
    public static Dimension getInnerFormLocation(JInternalFrame parent, Dimension FormPannel) {
        Dimension dimension = new Dimension();

        int height = (int) ((parent.getHeight() / 2) - (FormPannel.getHeight() / 2));
        int width = (int) ((parent.getWidth() / 2) - (FormPannel.getWidth() / 2));

        dimension.setSize(width, height);
        return dimension;
    }
    
}
