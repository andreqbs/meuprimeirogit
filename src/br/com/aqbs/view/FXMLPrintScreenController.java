package br.com.aqbs.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.glass.ui.Robot;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLPrintScreenController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void saveGraphAs() {
        
        Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
        robot.getScreenCapture(0, 0, 0, 0);
    }

    @FXML
    private void saveAsPng(MouseEvent event) {
    }
       
}
