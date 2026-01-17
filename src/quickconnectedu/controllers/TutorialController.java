package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController {

    @FXML
    private Button startBtn, backBtn, homeBtn, configBtn, soundBtn;

    @FXML
    private void onStart() {
        System.out.println("¡Tutorial iniciado!");
       
    }

    @FXML
    private void onBack() {
        System.out.println("Volviendo al menú principal");
      
    }

    @FXML
    private void onHome() {
        System.out.println("Botón de casa presionado");
     
    }

    @FXML
    private void onConfig() {
        System.out.println("Botón de configuración presionado");
    
    }

    @FXML
    private void onSound() {
        System.out.println("Botón de sonido presionado");
     
    }
}
