package br.com.aqbs.controller;

// Capture.java

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.imageio.*;
import javax.imageio.stream.*;
import javax.swing.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * This class defines the application GUI and starts the application.
 */
public class Capture extends Thread{
   
    ITesseract instance = new Tesseract(); 
  
    
    public String identificarNumero() {
        File imageFile = new File("/Users/andreqbs/NetBeansProjects/meuprimeirogit/foto.jpg");
        instance.setDatapath("/Users/andreqbs/Downloads/Tess4J/");
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
    
    
    @Override
    public void run() {
        identificarNumero();
        
    }
    
    
}
