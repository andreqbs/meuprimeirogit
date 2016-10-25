/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.view;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.IntBuffer;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author andreqbs
 */
public class CapturaController implements Initializable {

    @FXML
    private Pane pnPrincipal;

    private double xOffset = 0;
    private double yOffset = 0;
    private int x = 0;
    private int y = 0;
    private int largura = 0;
    private int altura = 0;
    Stage primaryStage;

    public static final int BYTE_BUFFER_BYTES_PER_COMPONENT = 1;
    public static final int INT_BUFFER_BYTES_PER_COMPONENT = 4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mousePressioanado() {

        pnPrincipal.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node source = (Node) event.getSource();
                primaryStage = (Stage) source.getScene().getWindow();
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
    }

    @FXML
    private void mouseNaoPressioanado() {

        pnPrincipal.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node source = (Node) event.getSource();
                primaryStage = (Stage) source.getScene().getWindow();
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    @FXML
    private void capturar() {
        pnPrincipal.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node source = (Node) event.getSource();
                primaryStage = (Stage) source.getScene().getWindow();
                xOffset = primaryStage.getX();
                yOffset = primaryStage.getY();
                x = (int) xOffset;
                y = (int) yOffset;
                largura = (int) primaryStage.getWidth();
                altura = (int) primaryStage.getHeight();
                Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
                Pixels glassPixels = robot.getScreenCapture(x, y, largura, altura);
                Image image = convertFromGlassPixels(glassPixels);

                File file = new File("foto.png");

                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    // TODO: handle exception here
                }
            }
        });
    }

    private Image convertFromGlassPixels(Pixels glassPixels) {
        int width = glassPixels.getWidth();
        int height = glassPixels.getHeight();
        WritableImage image = new WritableImage(width, height);

        int bytesPerComponent = glassPixels.getBytesPerComponent();
        if (bytesPerComponent == INT_BUFFER_BYTES_PER_COMPONENT) {
            IntBuffer intBuffer = (IntBuffer) glassPixels.getPixels();
            writeIntBufferToImage(intBuffer, image);
        } else if (bytesPerComponent == BYTE_BUFFER_BYTES_PER_COMPONENT) {
            ByteBuffer byteBuffer = (ByteBuffer) glassPixels.getPixels();
            writeByteBufferToImage(byteBuffer, image);
        }

        return image;
    }

    private void writeIntBufferToImage(IntBuffer intBuffer,
            WritableImage image) {
        PixelWriter pixelWriter = image.getPixelWriter();
        double width = image.getWidth();
        double height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = intBuffer.get();
                pixelWriter.setArgb(x, y, argb);
            }
        }
    }

    private void writeByteBufferToImage(ByteBuffer byteBuffer,
            WritableImage image) {
        throw new UnsupportedOperationException("Writing from byte buffer is not supported.");
    }

//Now this is the region which we are going to capture              
}
