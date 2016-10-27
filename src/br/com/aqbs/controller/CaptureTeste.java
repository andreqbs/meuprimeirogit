/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author aqbs
 */
public class CaptureTeste {

    JFrame p;
    
    public static CaptureTeste windowReader;

    public CaptureTeste() {

       GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice gd =  ge.getDefaultScreenDevice();
       
       if(!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
           System.exit(0);
       }
       
       TransparentFrame tw = new TransparentFrame();
       

    }
    
    class TransparentFrame extends JFrame implements MouseMotionListener, ActionListener
    {
        
        public TransparentFrame() {
            addMouseListener(this);
            setUndecorated(true);
        
            setLayout(new GridBagLayout());
            setSize(150,150);
            setLocation(200, 200);
            ThreadReadData t = new ThreadReadData();
            t.windowRefence = this;
                    t.start();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

    public void capturar() {
        Rectangle rect = p.getBounds();

        try {
            String format = "png";
            String fileName = p.getName() + "." + format;
            BufferedImage captureImage
                    = new BufferedImage(rect.width, rect.height,
                            BufferedImage.TYPE_INT_ARGB);
            p.paint(captureImage.getGraphics());

            ImageIO.write(captureImage, format, new File(fileName));

            System.out.printf("The screenshot of %s was saved!", p.getName());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    

}
