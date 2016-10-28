/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
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

    public static boolean windowReader = false;
    private int largura;
    private int altura;

    public CaptureTeste(int altura, int largura) {

        this.altura = altura;
        this.largura = largura;
        windowReader = true;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            System.exit(0);
        }

        TransparentFrame tw = new TransparentFrame();
        tw.setOpacity(0.3f);
        tw.setVisible(true);

    }

    class TransparentFrame extends JFrame implements MouseMotionListener, ActionListener {

        public TransparentFrame() {
            addMouseMotionListener(this);
            setUndecorated(true);

            setLayout(new GridBagLayout());
            setSize(largura, altura);
            setLocation(200, 200);
            ThreadReadData3 t = new ThreadReadData3();
            t.windowReference = this;
            t.start();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            this.setLocation(e.getLocationOnScreen().x - this.getSize().width / 2, e.getLocationOnScreen().y - this.getSize().height / 2);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }

    class ThreadReadData3 extends Thread {

        public TransparentFrame windowReference;

        @Override
        public void run() {
            try {
                while (true) {
                    if (this.windowReference.isShowing()) {
                        Robot robot;
                        try {
                            robot = new Robot();

                            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(windowReference.getLocationOnScreen().x, windowReference.getLocationOnScreen().y, windowReference.getSize().width, windowReference.getSize().height));
                            ImageIO.write(screenshot, "png", new File("foto.png"));
                        } catch (IOException e) {
                            e.printStackTrace();

                        }

                    }
                    this.sleep(100000);
                }
            } catch (Exception e) {
                e.printStackTrace();

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
 
  
}
