/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.Capture;
import br.com.aqbs.controller.GerenciarRoleta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private FXMLGraficoController gc2;
    @FXML
    private Button btnLimpar;
    @FXML
    private CheckBox chkManha;
    @FXML
    private CheckBox chkTarde;
    @FXML
    private CheckBox chkNoite;

    private GerenciarRoleta gr = new GerenciarRoleta();
    private Capture c = new Capture();
    @FXML
    private ComboBox<String> cmbDealer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            load();
            loadDealer();
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() throws IOException {
        this.gc = new FXMLGraficoController();
        this.gc2 = new FXMLGraficoController();
        FXMLMesaController mc = new FXMLMesaController(4);
        vbxPrincipal.getChildren().addAll(gc, gc2);
        // Construct the GUI and begin the event-handling thread.

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
        String dealer;
        gc.atualizarGrafico(text);
        if (chkManha.isSelected()) {
            turno = "M";
        } else if (chkTarde.isSelected()) {
            turno = "T";
        } else {
            turno = "N";
        }
        dealer = cmbDealer.getValue();
        gr.inserirNumero(text, turno, dealer);
        gc2.atualizarGrafico();

    }

    @FXML
    private void capturarImagem(MouseEvent event) {
        Thread thr1 = new Thread(c);
        //   Capture a = new Capture("Capture");
        thr1.start();
        //txfNumero.setText(c.identificarNumero());
    }

    @FXML
    private void limparEstatisticas(MouseEvent event) {
    }

    private void loadDealer() {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Aguia",
                        "Marina",
                        "Option 3"
                );
        cmbDealer.setItems(options);

    }

}
