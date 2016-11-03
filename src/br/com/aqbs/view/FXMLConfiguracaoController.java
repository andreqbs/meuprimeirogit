/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLConfiguracaoController implements Initializable {

    @FXML
    private CheckBox chkWin;
    @FXML
    private CheckBox chkMac;
    @FXML
    private TextField txfWinImagem;
    @FXML
    private TextField txfWinJar;
    @FXML
    private TextField txfMacImagem;
    @FXML
    private TextField txfMacJar;

    /**
     * Initializes the controller class.
     */
    private String localCaptura;
    private String localJAR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void salvarPreferencias(MouseEvent event) {
        if (chkMac.isSelected()) {
            localCaptura = txfMacImagem.getText();
            localJAR = txfMacJar.getText();
        } else if (chkWin.isSelected()) {
            localCaptura = txfWinImagem.getText();
            localJAR = txfWinJar.getText();
        } else {
            System.out.println("Escolha uma opção");
        }
    }

    public String getLocalCaptura() {
        return localCaptura;
    }

    public String getLocalJAR() {
        return localJAR;
    }

}
