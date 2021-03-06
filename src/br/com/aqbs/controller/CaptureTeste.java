/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author aqbs
 */
public class CaptureTeste {

    public static boolean windowReader = false;
    private int largura;
    private int altura;

    ITesseract instance = new Tesseract();
    private GerenciarRoleta gr;
    private String valor = "-1";
    private ThreadReadData3 t;
    private  TransparentFrame tw;
    private String localCaptura;
    private String localJAR;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void initData(GerenciarRoleta gr) {
        this.gr = gr;
        
    }

    public CaptureTeste() {

    }
    
    public void finalizarJanela() {
        t.interrupt();
        tw.setVisible(false);
       
    }

    public void mostrarJanela(int altura, int largura) {

        this.altura = altura;
        this.largura = largura;
        windowReader = true;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            System.exit(0);
        }

        tw = new TransparentFrame();
        tw.setOpacity(0.3f);
        tw.setVisible(true);

    }

    public String identificarNumero() {
        File imageFile = new File(localCaptura);
        //File imageFile = new File("D:\\Documentos\\NetBeansProjects\\meuprimeirogit\\foto.png");
        instance.setDatapath(localJAR);
        //instance.setDatapath("D:\\Downloads\\Tess4J\\");
        instance.setLanguage("eng");
        String result = null;
        try {
            result = instance.doOCR(imageFile);
            if (result.length() >= 1 && result.length() <= 3) {
                valor = result.substring(0, 1);
                if (valor.equals("0") || valor.equals("1") || valor.equals("2") || valor.equals("2") || valor.equals("3")
                        || valor.equals("4") || valor.equals("5") || valor.equals("6") || valor.equals("7")
                        || valor.equals("8") || valor.equals("9")) {                
                    return result.substring(0, 1);
                } else {
                    valor = "-1";
                    return valor;
                }
            } else if (result.length() >= 4 && result.length() < 6) {
                valor = result.substring(0, 2);
                if (valor.equals("10") || valor.equals("11") || valor.equals("12") || valor.equals("13")
                        || valor.equals("14") || valor.equals("15") || valor.equals("16") || valor.equals("17")
                        || valor.equals("18") || valor.equals("19") || valor.equals("20") || valor.equals("21")
                        || valor.equals("22") || valor.equals("23") || valor.equals("24") || valor.equals("25")
                        || valor.equals("26") || valor.equals("27") || valor.equals("28") || valor.equals("29")
                        || valor.equals("30") || valor.equals("31") || valor.equals("32") || valor.equals("33")
                        || valor.equals("34") || valor.equals("35") || valor.equals("36")) {
                    return result.substring(0, 2);
                } else {
                    valor = "-1";
                    return valor;
                }
            }

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        valor = "-1";
        return valor;
    }

    public void setLocal(String localCaptura, String localJAR) {
        this.localCaptura = localCaptura;
        this.localJAR = localJAR;
    }

    class TransparentFrame extends JFrame implements MouseMotionListener, ActionListener {

        public TransparentFrame() {
            addMouseMotionListener(this);
            setUndecorated(true);
            setLayout(new GridBagLayout());
            setSize(largura, altura);
            setLocation(535, 435);
            t = new ThreadReadData3();
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

                            valor = identificarNumero();
                            if (valor.equals("-1")) {
                                valor = "-1";
                            } else {
                                gr.inserirNumero(valor);
                                ThreadReadData3.sleep(52000);
                            }

                        } catch (IOException e) {
                            System.out.println("Erro na captura da imagem");

                        }

                    }
                    ThreadReadData3.sleep(1500);
                }
            } catch (AWTException | InterruptedException e) {
                System.out.println("Erro na captura da imagem");

            }

        }
    }

}
