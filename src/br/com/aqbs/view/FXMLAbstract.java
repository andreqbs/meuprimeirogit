/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author andreqbs
 */
public abstract class FXMLAbstract {
    
    protected Stage primaryStage;
    
     public void criarJanela(String janelaFXML, int largura, int altura) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(janelaFXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            primaryStage = stage;
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root,largura, altura);
            scene.setFill(null);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }
     
     
     public void criarJanela(String janelaFXML) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(janelaFXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();         
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro");
        }
    }
    
}
