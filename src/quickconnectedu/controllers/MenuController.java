package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button playBtn;

    @FXML
    private Button tutorialBtn;

    @FXML
    private Button configBtn;

    @FXML
    private Button soundBtn;

    @FXML
    private void onPlay() {
        System.out.println("Jugar presionado");
    }

    @FXML
    private void onTutorial() {
        System.out.println("Tutorial presionado");

    }

    @FXML
    private void onConfig() {
        System.out.println("Configuración presionada");

    }

    @FXML
    private void onSound() {
        System.out.println("Sonido presionado");

    }
}
