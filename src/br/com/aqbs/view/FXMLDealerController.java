/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.GerenciarDealer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author andreqbs
 */
public class FXMLDealerController implements Initializable {

    private GerenciarDealer gd;
    @FXML
    private TextField txfNome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void salvarDealer(MouseEvent event) {
        gd = new GerenciarDealer();
        gd.inserirDealer(txfNome.getText());

    }

}
