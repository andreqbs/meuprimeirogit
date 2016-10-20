/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;




/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLGraficoController extends Pane {

    final static String aposta1 = "1st 12";
    final static String aposta2 = "2n 12";
    final static String aposta3 = "3rd 12";
    final static String aposta4 = "1 a 18";
    final static String aposta5 = "19 a 36";
    final static String aposta6 = "Coluna 1";
    final static String aposta7 = "Coluna 2";
    final static String aposta8 = "Coluna 3";
    final static String aposta9 = "Par";
    final static String aposta10 = "Ímpar";
    final static String aposta11 = "Vermelho";
    final static String aposta12 = "Preto";

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    public FXMLGraficoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLGrafico.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        gerarGrafico();
    }

    public void gerarGrafico() {

        XYChart.Series series1 = new XYChart.Series();
        xAxis.setLabel("Apostas");
        yAxis.setLabel("% Acerto");
        series1.getData().add(new XYChart.Data(aposta1, 0));
        series1.getData().add(new XYChart.Data(aposta2, 0));
        series1.getData().add(new XYChart.Data(aposta3, 0));
        series1.getData().add(new XYChart.Data(aposta4, 0));
        series1.getData().add(new XYChart.Data(aposta5, 0));
        series1.getData().add(new XYChart.Data(aposta6, 0));
        series1.getData().add(new XYChart.Data(aposta7, 0));
        series1.getData().add(new XYChart.Data(aposta8, 0));
        series1.getData().add(new XYChart.Data(aposta9, 0));
        series1.getData().add(new XYChart.Data(aposta10, 0));
        series1.getData().add(new XYChart.Data(aposta11, 0));
        series1.getData().add(new XYChart.Data(aposta12, 0));

        barChart.getData().addAll(series1);

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(500),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        for (XYChart.Series<String, Number> series : barChart.getData()) {
                            for (XYChart.Data<String, Number> data : series.getData()) {
                                data.setYValue(Math.random() * 1000);
                            }
                        }
                    }
                }
                ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();
    }

}
