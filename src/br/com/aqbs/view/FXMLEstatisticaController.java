/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLEstatisticaController extends VBox {

    @FXML
    private Text txt1st12;
    @FXML
    private Rectangle rec1st12;
    @FXML
    private Text txt2st12;
    @FXML
    private Rectangle rec2st12;
    @FXML
    private Text txt3st12;
    @FXML
    private Rectangle rec3st12;
    @FXML
    private Text txt1a18;
    @FXML
    private Rectangle rec1a18;
    @FXML
    private Text txt19a36;
    @FXML
    private Rectangle rec19a36;
    @FXML
    private Text txtcoln1;
    @FXML
    private Rectangle reccoln1;
    @FXML
    private Text txtcoln2;
    @FXML
    private Rectangle reccoln2;
    @FXML
    private Text txtcoln3;
    @FXML
    private Rectangle reccoln3;
    @FXML
    private Text txtpar;
    @FXML
    private Rectangle recpar;
    @FXML
    private Text txtimpar;
    @FXML
    private Rectangle recimpar;
    @FXML
    private Text txtred;
    @FXML
    private Rectangle recred;
    @FXML
    private Text txtblack;
    @FXML
    private Rectangle recblack;
    @FXML
    private Text txtest1b;
    @FXML
    private Rectangle recest1b;
    @FXML
    private Text txtest2c;
    @FXML
    private Rectangle recest2c;

    public FXMLEstatisticaController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEstatistica.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
