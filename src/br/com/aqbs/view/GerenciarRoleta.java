package br.com.aqbs.view;


import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aqbs
 */
public class GerenciarRoleta {

    private void getScreen() throws IOException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle allScreenBounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
            allScreenBounds.width += screenBounds.width;
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            allScreenBounds.x = Math.min(allScreenBounds.x, screenBounds.x);
            allScreenBounds.y = Math.min(allScreenBounds.y, screenBounds.y);
        }
        Robot robot;
        try {
            robot = new Robot();

            BufferedImage bufferedImage = robot.createScreenCapture(allScreenBounds);
            File file = new File("C:\\Users\\aqbs\\Desktop\\scr.png");

            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            ImageIO.write(bufferedImage, "png", fos);
        } catch (AWTException ex) {
            Logger.getLogger(GerenciarRoleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
}
