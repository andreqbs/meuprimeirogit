/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.GerenciarRoleta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button btnLimpar;
    @FXML
    private CheckBox chkManha;
    @FXML
    private CheckBox chkTarde;
    @FXML
    private CheckBox chkNoite;

    private GerenciarRoleta gr = new GerenciarRoleta();

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
        String turno;
        gc.atualizarGrafico(text);
        if (chkManha.isSelected()) {
            turno = "M";
        } else if (chkTarde.isSelected()) {
            turno = "T";
        } else {
            turno = "N";
        }
        gr.inserirNumero(text, turno);

    }

    @FXML
    private void limparEstatisticas(MouseEvent event) {

    }

}
