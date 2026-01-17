package quickconnectedu.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class LevelsController {

    @FXML private Button btnLevel1, btnLevel2, btnLevel3, btnBack, btnProbar;
    @FXML private Label lblTimer;
    @FXML private ProgressBar progressTimer;
    @FXML private Rectangle dropZone;

    @FXML private ImageView opcion1, opcion2, opcion3, opcion4;

    private int timeSeconds = 120;
    private Timeline timeline;

    private final List<String> piezasColocadas = new ArrayList<>();

    private final Map<Integer, List<String>> niveles = Map.of(
            1, List.of("Interruptor", "LED Rojo", "Resistencia 220Ω", "Batería 9V"),
            2, List.of("Interruptor", "Cable", "LED Rojo"),
            3, List.of("Batería 9V", "Resistencia 220Ω", "Cable")
    );

    private int nivelActual = 1;

    @FXML
    public void initialize() {
        startTimer();
        cargarOpciones(nivelActual);
    }

    private void cargarOpciones(int nivel) {
        piezasColocadas.clear();

        switch (nivel) {
            case 1 -> {
                opcion1.setImage(new Image(getClass().getResource("/images/interruptor.png").toExternalForm()));
                opcion2.setImage(new Image(getClass().getResource("/images/ledrojo.png").toExternalForm()));
                opcion3.setImage(new Image(getClass().getResource("/images/resistencia220.png").toExternalForm()));
                opcion4.setImage(new Image(getClass().getResource("/images/bateria9v.png").toExternalForm()));

                enableDrag(opcion1, "Interruptor");
                enableDrag(opcion2, "LED Rojo");
                enableDrag(opcion3, "Resistencia 220Ω");
                enableDrag(opcion4, "Batería 9V");
            }
            case 2 -> {
                opcion1.setImage(new Image(getClass().getResource("/images/interruptor.png").toExternalForm()));
                opcion2.setImage(new Image(getClass().getResource("/images/cable.png").toExternalForm()));
                opcion3.setImage(new Image(getClass().getResource("/images/ledrojo.png").toExternalForm()));
                opcion4.setImage(null); // menos opciones

                enableDrag(opcion1, "Interruptor");
                enableDrag(opcion2, "Cable");
                enableDrag(opcion3, "LED Rojo");
            }
            case 3 -> {
                opcion1.setImage(new Image(getClass().getResource("/images/bateria9v.png").toExternalForm()));
                opcion2.setImage(new Image(getClass().getResource("/images/resistencia220.png").toExternalForm()));
                opcion3.setImage(new Image(getClass().getResource("/images/cable.png").toExternalForm()));
                opcion4.setImage(null);

                enableDrag(opcion1, "Batería 9V");
                enableDrag(opcion2, "Resistencia 220Ω");
                enableDrag(opcion3, "Cable");
            }
        }

        ((AnchorPane) dropZone.getParent()).getChildren().removeIf(node -> node instanceof ImageView && node != opcion1 && node != opcion2 && node != opcion3 && node != opcion4);
    }

    private void enableDrag(ImageView imageView, String nombre) {
        if (imageView == null) return;

        imageView.setOnDragDetected(event -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            content.putString(nombre); // Guardar nombre para validación
            db.setContent(content);
            event.consume();
        });

        dropZone.setOnDragOver(event -> {
            if (event.getGestureSource() != dropZone && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        dropZone.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                ImageView droppedImage = new ImageView(db.getImage());
                droppedImage.setFitWidth(80);
                droppedImage.setFitHeight(80);
                droppedImage.setLayoutX(dropZone.getLayoutX() + 50 + piezasColocadas.size() * 90);
                droppedImage.setLayoutY(dropZone.getLayoutY() + 100);

                ((AnchorPane) dropZone.getParent()).getChildren().add(droppedImage);

                piezasColocadas.add(db.getString());

                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    @FXML
    private void probarNivel(ActionEvent event) {
        List<String> piezasCorrectas = niveles.getOrDefault(nivelActual, new ArrayList<>());

        if (piezasColocadas.containsAll(piezasCorrectas) && piezasColocadas.size() == piezasCorrectas.size()) {
            mostrarAlerta("¡Correcto!", "✅ Has completado el nivel " + nivelActual, Alert.AlertType.INFORMATION);

            if (nivelActual < niveles.size()) {
                nivelActual++;
                cargarOpciones(nivelActual);
            } else {
                mostrarAlerta("¡Felicidades!", "Has completado todos los niveles.", Alert.AlertType.CONFIRMATION);
                backToMenu(null);
            }

        } else {
            mostrarAlerta("Intenta de nuevo", "❌ Faltan o sobran piezas.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void startTimer() {
        lblTimer.setText(formatTime(timeSeconds));
        progressTimer.setProgress(1.0);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeSeconds--;
            lblTimer.setText(formatTime(timeSeconds));
            progressTimer.setProgress((double) timeSeconds / 120.0);

            if (timeSeconds <= 0) {
                timeline.stop();
                lblTimer.setText("¡Tiempo!");
                progressTimer.setProgress(0.0);
                mostrarAlerta("Tiempo agotado", "⏳ No completaste el nivel a tiempo.", Alert.AlertType.WARNING);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    @FXML private void startLevel1(ActionEvent event) { nivelActual = 1; cargarOpciones(nivelActual); }
    @FXML private void startLevel2(ActionEvent event) { nivelActual = 2; cargarOpciones(nivelActual); }
    @FXML private void startLevel3(ActionEvent event) { nivelActual = 3; cargarOpciones(nivelActual); }
    @FXML private void backToMenu(ActionEvent event) { loadLevel("MainMenu.fxml"); }

    private void loadLevel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
