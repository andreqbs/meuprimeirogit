/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class NumeroController extends StackPane {

    @FXML
    private Rectangle fundo;
    @FXML
    private Text valor;

   public NumeroController(String valor) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Numero.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

       
        try {
            fxmlLoader.load();  
                    this.valor.setText(valor);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Rectangle getFundo() {
        return fundo;
    }

    public void setFundo(Color fundo) {
        this.fundo.setFill(fundo);
    }

    public Text getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor.setText(valor);
    }
  
    
}
