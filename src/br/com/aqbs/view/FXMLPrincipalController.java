/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLPrincipalController implements Initializable {

    private ImageView imvImage;
    @FXML
    private VBox vbxPrincipal;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() throws IOException {
       FXMLGraficoController ec = new FXMLGraficoController();
       FXMLMesaController mc = new FXMLMesaController(4);
       vbxPrincipal.getChildren().add(ec);
       vbxPrincipal.getChildren().add(mc);

    }
    
    private void loadImage() {
         Image image = new Image("/br/com/aqbs/resources/table.jpg");
         imvImage.setImage(image);
        // imvImage.setFitWidth(100);
         imvImage.setPreserveRatio(true);
         imvImage.setSmooth(true);
         imvImage.setCache(true);
    }

}
