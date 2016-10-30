/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.GerenciarRoleta;
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

    private GerenciarRoleta gr;

    public NumeroController(String valor) {
        gr = new GerenciarRoleta();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Numero.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            this.valor.setText(valor);
        } catch (IOException exception) {
            System.out.println("Erro para gerar Número");
        }
//        if (gr.pegarCor(Integer.valueOf(valor)).equals("Preto"))
//            fundo.setFill(Color.BLACK);
//        else
//            fundo.setFill(Color.RED);
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
