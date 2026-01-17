package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class LevelCompletedController {

    @FXML
    private Button btnBack;

    @FXML
    private void onBack() {
        System.out.println("Volviendo al tutorial/menú anterior");
      
    }

    @FXML
    private Button btnNextLevel, btnExit, homeBtn, configBtn, soundBtn;

    @FXML
    private void goToNextLevel() {
        System.out.println("Siguiente nivel");
       
    }

    @FXML
    private void backToMenu() {
        System.out.println("Volviendo al menú");
      
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

    @FXML
    private Label lblMessage;

   
    public void setMessage(String message) {
        lblMessage.setText(message);
    }


    @FXML
    private ProgressBar progressBar;

   
    public void updateProgress(int currentLevel, int totalLevels) {
        if (totalLevels > 0) {
            double progress = (double) currentLevel / totalLevels;
            progressBar.setProgress(progress);
        } else {
            progressBar.setProgress(0);
        }
    }
}
