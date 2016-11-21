/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author andreqbs
 */
public class FXMLGraficoConfiguracaoController extends FXMLAbstract implements Initializable {

    @FXML
    private VBox vbxApostas;
    
    private List<String> apostas = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void pegarApostas() {
        for (Node children : vbxApostas.getChildren()) {
            HBox hbx = (HBox)children;
            for (Node children1 : hbx.getChildren()) {
                CheckBox chk = (CheckBox)children1;
                if (chk.isSelected())
                    apostas.add(chk.getText());
            }
        }
    }

    public List<String> getApostas() {
        return apostas;
    }

}
