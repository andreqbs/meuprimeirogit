/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.GerenciarRoleta;
import java.io.IOException;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aqbs
 */
public class FXMLGraficoController extends VBox {

    final static String aposta1 = "Dúzia 1 e 2";
    final static String aposta2 = "Dúzia 1 e 3";
    final static String aposta3 = "Dúzia 2 e 3";
    final static String aposta4 = "1 a 18";
    final static String aposta5 = "19 a 36";
    final static String aposta6 = "Coluna 1 e 2";
    final static String aposta7 = "Coluna 1 e 3";
    final static String aposta8 = "Coluna 2 e 3";
    final static String aposta9 = "Par";
    final static String aposta10 = "Ímpar";
    final static String aposta11 = "Vermelho";
    final static String aposta12 = "Preto";
    final static String aposta13 = "Metade";

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    private XYChart.Series series1 = new XYChart.Series();
    @FXML
    private ComboBox<?> cmbDealer;
    @FXML
    private Label cmbTipo;

    private GerenciarRoleta gr;
    private FXMLGraficoConfiguracaoController gcc;

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

    public void initData(GerenciarRoleta gr) {
        this.gr = gr;
    }

    public FXMLGraficoController(List<String> l) {
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
        series1.getData().add(new XYChart.Data(aposta13, 0));

        barChart.getData().addAll(series1);

    }

    public void gerarGrafico(List<String> l) {

        xAxis.setLabel("Apostas");
        yAxis.setLabel("% Acerto");
        for (int i = 0; i < l.size(); i++) {
            series1.getData().add(new XYChart.Data(l.get(i), 0));
        }

        barChart.getData().addAll(series1);

    }

    public void atualizarGrafico() {
        List<Integer> numeros = gr.totalRodadas();
        Timeline tl = new Timeline();
        tl.stop();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(1500),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int i = 0;
                        for (XYChart.Series<String, Number> series : barChart.getData()) {
                            for (XYChart.Data<String, Number> data : series.getData()) {
                                data.setYValue((numeros.get(i)));
                                i++;
                            }
                        }
                    }
                }
                ));
        //  tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(false);
        tl.play();
    }

    public void atualizarGrafico(String valor) {

        List<Integer> numeros = gr.calculaEstatisticas(Integer.valueOf(valor));
        Timeline tl = new Timeline();
        tl.stop();
        tl.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int i = 0;
                        for (XYChart.Series<String, Number> series : barChart.getData()) {
                            for (XYChart.Data<String, Number> data : series.getData()) {
                                data.setYValue((numeros.get(i)));
                                i++;
                            }
                        }
                    }
                }
                ));
        //  tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(false);
        tl.play();

    }

    public void limparGrafico() {
        gr.limparApostas();

    }

}
