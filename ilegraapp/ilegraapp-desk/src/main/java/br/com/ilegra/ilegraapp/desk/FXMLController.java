package br.com.ilegra.ilegraapp.desk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField caminho;
    
    @FXML
    private TextField tempoRe;
    
    @FXML
    private Button iniciar;
    
    @FXML
    private Button parar;
    
    @FXML
    private void inicializar() {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void finalizar() {
        System.out.println("You clicked me2222!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
