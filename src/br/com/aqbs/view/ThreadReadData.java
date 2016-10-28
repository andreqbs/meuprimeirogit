/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import javafx.application.Platform;

/**
 *
 * @author aqbs
 */
public class ThreadReadData extends Thread {
    
    public CapturaController a;

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boolean isRunning = true;
                long timeout = 5000;
                while (isRunning) {
                    try {
                        a.capturar();
                        System.out.println("FOI!");
                        Thread.sleep(timeout);
                    } catch (InterruptedException ex) {
                        isRunning = false;
                    }
                }
            }
        });
    }
    
}
