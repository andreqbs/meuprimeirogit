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
            if (result.length() <= 3) {
                //   System.out.println("O numero com 1 digito é : " + result.substring(0, 2));
                valor = result.substring(0, 1);
                if (valor.equals("0") || valor.equals("1") || valor.equals("2") || valor.equals("2") || valor.equals("3")
                        || valor.equals("4") || valor.equals("5") || valor.equals("6") || valor.equals("7")
                        || valor.equals("8") || valor.equals("9")) {
                    return result.substring(0, 1);
                } else {
                    valor = "-1";
                    return valor;
                }
            } else {
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
        return "-1";
    }

    class ThreadReadData3 extends Thread {

        private int contador = 0;
        private String valor = "-1";

        @Override
        public void run() {
            while (true) {
                if (CaptureTeste.windowReader) {
                    try {
                        if (flag) {
                            valor = identificarNumero();
                            if (valor.equals("-1")) {
                                valor = "-1";
                            } else {
                                gr.inserirNumero(valor);
                                 ThreadReadData3.sleep(30000);
                            }
                        }
                        flag = true;

                        ThreadReadData3.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        }

    }

}
