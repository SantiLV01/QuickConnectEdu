package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConfigController {

    @FXML private Button idiomaHeader;
    @FXML private Button temaHeader;

    @FXML private VBox idiomaContent;
    @FXML private VBox temaContent;

    @FXML private Label idiomaArrow;
    @FXML private Label temaArrow;

    @FXML private ComboBox<String> langCombo;
    @FXML private ComboBox<String> themeCombo;

    @FXML
    public void initialize() {
        // que el layout se reajuste cuando cambiamos visible
        idiomaContent.managedProperty().bind(idiomaContent.visibleProperty());
        temaContent.managedProperty().bind(temaContent.visibleProperty());

        // valores de ejemplo
        langCombo.getItems().setAll("Español", "Inglés", "Francés");
        themeCombo.getItems().setAll("Claro", "Oscuro");

        // empezamos cerrados
        idiomaContent.setVisible(false);
        temaContent.setVisible(false);
        updateArrows();
    }

    @FXML
    private void toggleIdioma() {
        boolean now = !idiomaContent.isVisible();
        idiomaContent.setVisible(now);
        updateArrows();
    }

    @FXML
    private void toggleTema() {
        boolean now = !temaContent.isVisible();
        temaContent.setVisible(now);
        updateArrows();
    }

    private void updateArrows() {
        idiomaArrow.setText(idiomaContent.isVisible() ? "▲" : "▼");
        temaArrow.setText(temaContent.isVisible() ? "▲" : "▼");
    }
}
