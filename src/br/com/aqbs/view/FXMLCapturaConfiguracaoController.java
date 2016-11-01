/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.CaptureTeste;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author andreqbs
 */
public class FXMLCapturaConfiguracaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private CaptureTeste ct;
    private Thread x;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ct = new CaptureTeste();
    }

    @FXML
    private void janelaCaptura(MouseEvent event) {
        ct.mostrarJanela(40, 40);
        x.start();

    }

    public CaptureTeste getCaptureTeste() {
        return ct;
    }

    void initData(Thread x) {
        this.x = x;
    }

}