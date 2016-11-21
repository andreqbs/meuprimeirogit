/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.LeituraEscritaArquivo;
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
public class FXMLConfiguracaoController extends FXMLAbstract implements Initializable {

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

    private  LeituraEscritaArquivo lea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }


    
   
    @FXML
    private void salvarPreferencias(MouseEvent event) {

        String localCaptura = null;
        String localJAR = null;
        
        if (chkMac.isSelected()) {
            localCaptura = txfMacImagem.getText();
            localJAR = txfMacJar.getText();
        } else if (chkWin.isSelected()) {
            localCaptura = txfWinImagem.getText();
            localJAR = txfWinJar.getText();
        }
        lea.setLocalJar(localJAR);
        lea.setLocalImagem(localCaptura);
    }

  

   

}
