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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLPrincipalController implements Initializable {

    private ImageView imvImage;
    @FXML
    private VBox vbxPrincipal;
    @FXML
    private TextField txfNumero;
    @FXML
    private Button btnEnviar;

    
    private FXMLGraficoController gc;

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
       this.gc = new FXMLGraficoController();
       FXMLMesaController mc = new FXMLMesaController(4);
       vbxPrincipal.getChildren().add(gc);
      // vbxPrincipal.getChildren().add(mc);

    }
    
    private void loadImage() {
         Image image = new Image("/br/com/aqbs/resources/table.jpg");
         imvImage.setImage(image);
        // imvImage.setFitWidth(100);
         imvImage.setPreserveRatio(true);
         imvImage.setSmooth(true);
         imvImage.setCache(true);
    }
    
    @FXML
    private void enviarNumero() {
        String text = txfNumero.getText();
        gc.atualizarGrafico(text);
        
    }

}
