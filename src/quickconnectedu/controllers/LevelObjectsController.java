package quickconnectedu.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelObjectsController {

    @FXML private ImageView objeto1; 
    @FXML private ImageView objeto2; 
    @FXML private ImageView objeto3; 
    @FXML private ImageView objeto4;
    @FXML private ImageView objeto5; 

    @FXML
    private void initialize() {
       
        objeto1.setImage(new Image(getClass().getResource("/images/interruptor.png").toExternalForm()));
        objeto2.setImage(new Image(getClass().getResource("/images/ledrojo.png").toExternalForm()));
        objeto3.setImage(new Image(getClass().getResource("/images/resistencia220.png").toExternalForm()));
        objeto4.setImage(new Image(getClass().getResource("/images/bateria9v.png").toExternalForm()));
        objeto5.setImage(new Image(getClass().getResource("/images/cable.png").toExternalForm()));

     
        objeto1.setOnMouseClicked(e -> seleccionarObjeto("Interruptor"));
        objeto2.setOnMouseClicked(e -> seleccionarObjeto("LED Rojo"));
        objeto3.setOnMouseClicked(e -> seleccionarObjeto("Resistencia 220Ω"));
        objeto4.setOnMouseClicked(e -> seleccionarObjeto("Batería 9V"));
        objeto5.setOnMouseClicked(e -> seleccionarObjeto("Cable"));
    }

    private void seleccionarObjeto(String nombre) {
        System.out.println("Seleccionaste: " + nombre);
            }
}
