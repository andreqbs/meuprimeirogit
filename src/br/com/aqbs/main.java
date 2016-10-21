package br.com.aqbs;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqbs
 */
public class main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/br/com/aqbs/view/FXMLPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setMaximized(false);
        stage.setTitle("Roullete Bet");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
}
