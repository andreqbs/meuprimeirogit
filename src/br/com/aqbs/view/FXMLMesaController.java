/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLMesaController extends Pane {

    @FXML
    private Pane pnPrimeiro;
    @FXML
    private Pane pnSegundo;
    @FXML
    private Pane pnTerceiro;
    
     public FXMLMesaController(int v) {
         
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMesa.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            carregarMesa(3, 36, 1, pnPrimeiro);
        carregarMesa(2, 35, 0, pnSegundo);
        carregarMesa(1, 34, 1, pnTerceiro);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
     }

    public void carregarMesa(int inicio, int fim, int cor, Pane local) {
        double x = 0;

        for (int i = inicio; i <= fim; i += 3) {
            NumeroController n = new NumeroController(String.valueOf(i));
            local.getChildren().add(n);
            n.setLayoutX(x);
            n.setLayoutY(0);
            if (cor == 1) {
                n.setFundo(Color.RED);
                cor = 0;
                if (i == 16 || i == 9 || i == 18 || i == 27) {
                    cor = 1;
                }
                x += 30;
            } else {
                n.setFundo(Color.BLACK);
                cor = 1;
                if (i == 10 || i == 28 || i == 8 || i == 17 || i == 26) {
                    cor = 0;
                }
                x += 30;
            }
        }
        NumeroController n = new NumeroController("1/2");
        local.getChildren().add(n);
        n.setFundo(Color.TRANSPARENT);
        n.setLayoutX(x);
        n.setLayoutY(0);
    }

}
