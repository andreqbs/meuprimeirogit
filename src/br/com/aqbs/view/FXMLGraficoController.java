/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.GerenciarRoleta;
import br.com.aqbs.dao.NumeroDaoJDBC;
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
     final static String aposta13 = "Quebrando";

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    
    private GerenciarRoleta gr = new GerenciarRoleta();
    
    private XYChart.Series series1 = new XYChart.Series();

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
    
    public void atualizarGrafico2(String valor) {
        List<Integer> numeros = gr.calculaEstatisticas(Integer.valueOf(valor));
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
