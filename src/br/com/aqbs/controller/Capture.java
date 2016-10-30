package br.com.aqbs.controller;

// Capture.java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.HBox;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * This class defines the application GUI and starts the application.
 */
public class Capture extends Thread {

    ITesseract instance = new Tesseract();
    List<String> numeros = new ArrayList<>();
    private GerenciarRoleta gr = new GerenciarRoleta();
    private boolean flag = false;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Capture() {

        for (int i = 0; i < 38; i++) {
            numeros.add(String.valueOf(i));
        }
        ThreadReadData3 t = new ThreadReadData3();
        t.start();
    }

    public String identificarNumero() {
        File imageFile = new File("/Users/andreqbs/NetBeansProjects/meuprimeirogit/foto.png");
        //File imageFile = new File("D:\\Documentos\\NetBeansProjects\\RoulleteBet\\foto.png");
        instance.setDatapath("/Users/andreqbs/Downloads/Tess4J/");
        //instance.setDatapath("D:\\Downloads\\Tess4J\\");
        instance.setLanguage("eng");
        String result = null;
        try {
            result = instance.doOCR(imageFile);
           // System.out.println("O tamanho do vetor é " + result.length());
            if(result.length() <= 3) {
             //   System.out.println("O numero com 1 digito é : " + result.substring(0, 2));
             valor = result.substring(0, 1);
                return result.substring(0, 1); }
            else {
             valor = result.substring(0, 2);
                return result.substring(0, 2);
            }
            
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return "-1";
    }

    class ThreadReadData3 extends Thread {

        private int contador = 0;

        @Override
        public void run() {
            while (true) {
                if (CaptureTeste.windowReader) {
                    try {
                        //contador++;
                        //if(contador < 5) {
                        if (flag) {
                            gr.inserirNumero(identificarNumero());
                        }
                        flag = true;
                        //}

                        ThreadReadData3.sleep(72000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        }

    }

}
