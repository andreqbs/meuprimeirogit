package br.com.aqbs.controller;

// Capture.java
import java.io.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * This class defines the application GUI and starts the application.
 */
public class Capture extends Thread {

    ITesseract instance = new Tesseract();

    public Capture() {
        ThreadReadData3 t = new ThreadReadData3();
        t.start();
    }

    public String identificarNumero() {
        // File imageFile = new File("/Users/andreqbs/NetBeansProjects/meuprimeirogit/foto.jpg");
        File imageFile = new File("D:\\Documentos\\NetBeansProjects\\RoulleteBet\\foto.png");
        //  instance.setDatapath("/Users/andreqbs/Downloads/Tess4J/");
        instance.setDatapath("D:\\Downloads\\Tess4J\\");
        instance.setLanguage("eng");

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    class ThreadReadData3 extends Thread {


        @Override
        public void run() {
            System.out.println("Capture");
            while (true) {
                if (CaptureTeste.windowReader) {
                    try {
                        identificarNumero();
                        this.sleep(7000);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

        }

    }
}
