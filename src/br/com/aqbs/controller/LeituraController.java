/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import java.io.File;
import javafx.application.Platform;
import javafx.concurrent.Task;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author andreqbs
 */
public class LeituraController {

    private String localCaptura;
    private String localJAR;
    private String valor = "-1";

    private void identificarNUmero() {
        ITesseract instance = new Tesseract();
        File imageFile = new File(localCaptura);
        instance.setDatapath(localJAR);
        instance.setLanguage("eng");
        String result = null;
        try {
            result = instance.doOCR(imageFile);
            if (result.length() >= 1 && result.length() <= 3) {
                valor = result.substring(0, 1);
                if (valor.equals("0") || valor.equals("1") || valor.equals("2") || valor.equals("2") || valor.equals("3")
                        || valor.equals("4") || valor.equals("5") || valor.equals("6") || valor.equals("7")
                        || valor.equals("8") || valor.equals("9")) {
                } else {
                    valor = "-1";
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
                } else {
                    valor = "-1";
                }
            }

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setLocal(String localCaptura, String localJAR) {
        this.localCaptura = localCaptura;
        this.localJAR = localJAR;
    }

    public void leituraNumero() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            identificarNUmero();
                        }
                    });
                    Thread.sleep(55000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
    
    public String valorLido() {
        return valor;
    }

}
