/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.LeituraEscritaArquivo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author andreqbs
 */
public class FXMLCapturaConfiguracaoController extends FXMLAbstract {

    @FXML
    private TextField txfLargura;
    @FXML
    private TextField txfAltura;
    
    private  LeituraEscritaArquivo lea;

    

    @FXML
    private void janelaCaptura(MouseEvent event) {
        lea.setAltura(Integer.valueOf(txfAltura.getText()));
        lea.setLargura(Integer.valueOf(txfLargura.getText()));
        lea.escritor(txfLargura.getText() + "," + txfAltura.getText());

    }

    void initData(LeituraEscritaArquivo lea) {
        this.lea = lea;
    }
    

  
}
