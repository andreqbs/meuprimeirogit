/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import br.com.aqbs.controller.ControlerLeituraEscrita;
import br.com.aqbs.controller.GerenciarRoleta;
import br.com.aqbs.controller.LeituraEscritaArquivo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private TextField txfNumeroAnt;
    @FXML
    private TextField txfNovaAposta;
    @FXML
    private HBox hbxNumeros1;
    @FXML
    private HBox hbxNumeros2;
    @FXML
    private HBox hbxNumeros3;
    @FXML
    private HBox hbxNumeros4;
    @FXML
    private HBox hbxNumeros5;
    @FXML
    private HBox hbxNumeros6;

    private FXMLConfiguracaoController fcc;
    private FXMLGraficoController gc;
    private FXMLCapturaConfiguracaoController ccc;
    private FXMLGraficoConfiguracaoController gcc;

    private GerenciarRoleta gr = new GerenciarRoleta();
    private ControlerLeituraEscrita cle;
    private CapturaController cc;
    private LeituraEscritaArquivo lea ;

    private List<String> numerosSorteados = new ArrayList<>();
    private teste t;
    @FXML
    private CheckBox chkAdd;
    @FXML
    private CheckBox chkRev;
    @FXML
    private Button btnGerarGrafico;
    @FXML
    private Button btnPararCaptura;
    @FXML
    private TextField txfDealer;
    @FXML
    private Button btnInciarCaptura;
    @FXML
    private TextArea txaNumeros;

    // private CapturaController cc;
    /**
     * Initializes the controller class.
     */
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lea =  new LeituraEscritaArquivo();
        try {
            load();
            loadNumeros();
//            loadDealer();

        } catch (IOException ex) {
            System.out.println("Erro ao iniciar servidor");
        }
    }

    private void load() throws IOException {
        this.gc = new FXMLGraficoController();
        gc.initData(gr, null);
        // this.gc2 = new FXMLGraficoController();
        //FXMLMesaController mc = new FXMLMesaController(4);
        vbxPrincipal.getChildren().addAll(gc);

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
        System.out.println(lea);
        ccc = new FXMLCapturaConfiguracaoController();
        ccc.initData(lea);
        ccc.criarJanela("FXMLCapturaConfiguracao.fxml");
        btnInciarCaptura.setDisable(false);

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLCapturaConfiguracao.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();         
//            stage.setScene(new Scene(root));
//            stage.show();
//           FXMLCapturaConfiguracaoController tt = fxmlLoader.<FXMLCapturaConfiguracaoController>getController();
////            t = new teste();
////            Thread x = new Thread(t);
//            tt.initData(cle);
////            tt.inicializarLocal(fcc.getLocalCaptura(), fcc.getLocalJAR());
//             
////            ct = tt.getCaptureTeste();
////            ct.initData(gr);
//         //   btnPararCaptura.setDisable(false);
//        } catch (IOException e) {
//            System.out.println("Erro ao gerar Frame de Captura");
//        }
        // btnPararCaptura.setDisable(false);
    }
    
     @FXML
    private void configurarGrafico(ActionEvent event) {
        gcc = new FXMLGraficoConfiguracaoController();
        gcc.criarJanela("FXMLGraficoConfiguracao.fxml");

        //FXMLGraficoConfiguracaoController tt = fxmlLoader.<FXMLGraficoConfiguracaoController>getController();
        // btnGerarGrafico.setDisable(false);
    }

    @FXML
    private void configurarLocal(ActionEvent event) {
    
        ///fcc = new FXMLConfiguracaoController(); 
        fcc.criarJanela("FXMLConfiguracao.fxml");

    }

    @FXML
    private void inserirDealer() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDealer.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Erro ao gerar Dealer");
        }
    }

    @FXML
    private void enviarNumero() {
        String text = txfNumero.getText();
        if (chkRev.isSelected()) {
            gr.reavaliarGrafico();
            numerosSorteados.set(0, text);
            chkRev.setSelected(false);
        } else if (chkAdd.isSelected()) {
            numerosSorteados.add(0, text);
            chkAdd.setSelected(false);
        }
        gc.atualizarGrafico(text);

    }

    private void loadDealer() {

//        List<Dealer> listar = gd.listar();
//        ObservableList<String> options
//                = FXCollections.observableArrayList();
//        options.clear();
//        for (int i = 0; i < listar.size(); i++) {
//            options.add(listar.get(i).getNome());
//        }
//        cmbDealer.setItems(options);
    }

    private void loadNumeros() {
        for (int i = 0; i < 24; i++) {
            NumeroController n = new NumeroController("X");
            if (i < 4) {
                hbxNumeros1.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 5, 5));
            } else if (i > 3 && i < 8) {
                hbxNumeros2.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 5, 5));
            } else if (i > 7 && i < 12) {
                hbxNumeros3.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 5, 5));
            } else if (i > 11 && i < 16) {
                hbxNumeros4.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 5, 5));
            } else if (i > 15 && i < 20) {
                hbxNumeros5.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 5, 5));
            } else if (i > 19 && i < 24) {
                hbxNumeros6.getChildren().add(n);
                HBox.setMargin(n, new Insets(0, 5, 0, 5));
            }
        }
    }

    @FXML
    private void inserirAposta(ActionEvent event) {
    }

    @FXML
    private void enviarAposta(MouseEvent event) {
    }

    @FXML
    private void loadApostas(ActionEvent event) {
    }

   

    @FXML
    private void gerarGraficoApostas(MouseEvent event) {
        gc.gerarGrafico(gcc.getApostas());
        btnGerarGrafico.setDisable(true);
    }

    @FXML
    private void pararCaptura(MouseEvent event) {
        t.cancel();
//        ct.finalizarJanela();
//        ct = null;
        btnPararCaptura.setDisable(true);
    }

    @FXML
    private void capturarDealer(ActionEvent event) {
    }

    @FXML
    private void setarDealer(MouseEvent event) {
        gr.setDealer(txfDealer.getText());
    }

    @FXML
    private void iniciarCaptura(MouseEvent event) {
        cle = new ControlerLeituraEscrita();
        cle.definirTamanho(lea.getAltura(), lea.getLargura());
        cle.setLocal(lea.getLocalImagem(), lea.getLocalJar());
        cle.iniciarLeituraEscrita();

    }

    @FXML
    private void enviarNumerosArea(MouseEvent event) {
        String text = txaNumeros.getText();
        
            System.out.println(Arrays.toString(text.split(",")));
            
        
       
    }

    private class teste extends Task<Void> {

        int contador = 4;
        boolean flag = false;
        String cor;
        String valor;

        @Override
        protected Void call() throws Exception {
            while (true) {
                try {
                    valor = cle.getValor();
                    if (!valor.equals("-1")) {
                        gc.atualizarGrafico(valor);
                        numerosSorteados.add(0, valor);
                        Thread.sleep(1000);
                        for (int i = 0; i < numerosSorteados.size(); i++) {
                            try {
                                if (i < 4) {
                                    NumeroController na = (NumeroController) hbxNumeros1.getChildren().get(i);
                                    na.setValor(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));
                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }
                                } else if (i > 3 && i < 8) {
                                    contador = 4;
                                    NumeroController na = (NumeroController) hbxNumeros2.getChildren().get(i - contador);
                                    na.setValor(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));

                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }

                                } else if (i > 7 && i < 12) {
                                    contador = 8;
                                    NumeroController na = (NumeroController) hbxNumeros3.getChildren().get(i - contador);
                                    na.setValor(numerosSorteados.get(i));
                                    System.out.println(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));

                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }

                                } else if (i > 11 && i < 16) {
                                    contador = 12;
                                    NumeroController na = (NumeroController) hbxNumeros4.getChildren().get(i - contador);
                                    na.setValor(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));

                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }

                                } else if (i > 15 && i < 20) {
                                    contador = 16;
                                    NumeroController na = (NumeroController) hbxNumeros5.getChildren().get(i - contador);
                                    na.setValor(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));

                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }

                                } else if (i > 19 && i < 24) {
                                    contador = 20;
                                    NumeroController na = (NumeroController) hbxNumeros6.getChildren().get(i - contador);
                                    na.setValor(numerosSorteados.get(i));
                                    cor = gr.pegarCor(Integer.valueOf(numerosSorteados.get(i)));
                                    if (cor.equals("Preto")) {
                                        na.setFundo(Color.BLACK);
                                    } else if (cor.equals("Vermelho")) {
                                        na.setFundo(Color.RED);
                                    } else if (cor.equals("Verde")) {
                                        na.setFundo(Color.GREEN);
                                    }

                                    if (i > 23) {
                                        numerosSorteados.remove(24);
                                        break;
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Erro na geracao da cor");
                            }
                            //   ct.setValor("-1");
                        }
                        Thread.sleep(55000);
                    }
                    Thread.sleep(3000);
                    System.out.println("lendo valores");

                } catch (InterruptedException interrupted) {

                    System.out.println("saindo break");
                    break;
                }
            }
            System.out.println("saindo return");
            return null;
        }
    }

}
