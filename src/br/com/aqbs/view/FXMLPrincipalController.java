/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.Capture;
import br.com.aqbs.controller.CaptureWindow;
import br.com.aqbs.controller.GerenciarDealer;
import br.com.aqbs.controller.GerenciarRoleta;
import br.com.aqbs.model.Dealer;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private CapturaController cc;

    @FXML
    private CheckBox chkManha;
    @FXML
    private CheckBox chkTarde;
    @FXML
    private CheckBox chkNoite;

    private GerenciarRoleta gr = new GerenciarRoleta();
    private GerenciarDealer gd = new GerenciarDealer();
    private Capture c = new Capture();
    @FXML
    private ComboBox<String> cmbDealer;
    @FXML
    private TextField txfValor;
    @FXML
    private Button btnEnviarAposta;
    @FXML
    private ComboBox<?> cmbTipoAposta;
    @FXML
    private CheckBox chkWin;
    @FXML
    private CheckBox chkLost;
    @FXML
    private TextField txfNumeroAnt;

    // private CapturaController cc;
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
        vbxPrincipal.getChildren().addAll(gc);
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
    private void capturarImagem() {
        cc = new CapturaController();
        cc.showWindow();
        Thread a = new Thread(cc.runa());
        a.start();
        
        
//         try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Captura.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.TRANSPARENT);
//            //   stage.setTitle("teste");
//            Scene scene = new Scene(root, 29, 29);
//            scene.setFill(null);
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void inserirDealer() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDealer.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            // stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        txfNumeroAnt.setText(text);
        txfNumero.clear();

    }

    private void capturarImagem(MouseEvent event) {
        Thread thr1 = new Thread(c);
        //   Capture a = new Capture("Capture");
        thr1.start();
        //txfNumero.setText(c.identificarNumero());
    }

    private void loadDealer() {

        List<Dealer> listar = gd.listar();
        ObservableList<String> options
                = FXCollections.observableArrayList();
        options.clear();
        for (int i = 0; i < listar.size(); i++) {
            options.add(listar.get(i).getNome());
        }
        cmbDealer.setItems(options);

    }

    @FXML
    private void inserirAposta(ActionEvent event) {
    }

    @FXML
    private void loadDealers(ActionEvent event) {
    }

    @FXML
    private void enviarAposta(MouseEvent event) {
    }

    @FXML
    private void loadApostas(ActionEvent event) {
    }

}
