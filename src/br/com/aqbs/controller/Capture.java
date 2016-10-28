package br.com.aqbs.controller;

// Capture.java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * This class defines the application GUI and starts the application.
 */
public class Capture extends Thread {

    ITesseract instance = new Tesseract();
    List<String> numeros = new ArrayList<>();

    public Capture() {

        for (int i = 0; i < 38; i++) {
            numeros.add(String.valueOf(i));
        }
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

    public void asa() {
    }

    public String traduzirNumero(String valor) {

        String resposta = null;
        
        if (numeros.contains(valor)) {
            resposta = valor;
        } else if (valor.equals("Ts")) {
            resposta = "25";
        } else if (valor.equals("E")) {
            resposta = "36";
        } else if (valor.equals("w")) {
            resposta = "19";
        }
        return resposta;
    }

    class ThreadReadData3 extends Thread {

        @Override
        public void run() {
            while (true) {
                if (CaptureTeste.windowReader) {
                    try {
                        System.out.println(traduzirNumero(identificarNumero()));
                        this.sleep(108000);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            }

        }

    }

}
