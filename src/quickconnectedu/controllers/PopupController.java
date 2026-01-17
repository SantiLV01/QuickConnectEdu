package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupController {

    @FXML private Label lblMensaje;
    @FXML private Button btnRetry, btnExit;

    public void setMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    @FXML
    private void initialize() {
        btnRetry.setOnAction(e -> {
            System.out.println("Reiniciando nivel...");
            cerrarVentana();
        });

        btnExit.setOnAction(e -> {
            System.out.println("Saliendo del juego...");
            cerrarVentana();
        });
    }

    private void cerrarVentana() {
        Stage stage = (Stage) lblMensaje.getScene().getWindow();
        stage.close();
    }
}
