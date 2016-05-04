package br.com.ilegra.ilegraapp.desk;

import br.com.ilegra.ilegraapp.application.controllers.GerenciadorController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLController implements Initializable {
    
    private GerenciadorController gerenciador;
    
    @FXML
    private TextField caminho;
    
    @FXML
    private TextField tempoRe;
    
    @FXML
    private Text tempo;
    
    @FXML
    private void inicializar() {
        
        gerenciador = new GerenciadorController();
        
        if (validarCampos()) {
            gerenciador.setCaminho(caminho.getText());
            gerenciador.setTempo(Integer.parseInt(tempoRe.getText()));
            gerenciador.processar();
            tempo.setText("ATIVO!");
            
        }
    }
    
    @FXML
    private void finalizar() {
        gerenciador.finalizarExecucao();
        tempo.setText("INATIVO");
    }
    
    private Boolean validarCampos() {
        if (tempoRe.getText() == null || tempoRe.getText().isEmpty()) {
            tempo.setText("O tempo de intervalo não pode ser vazio.");
            return Boolean.FALSE;
        }
        
        try {
            Integer.parseInt(tempoRe.getText());
        } catch (Exception e) {
            tempo.setText("O tempo de intervalo não é um valor inteiro.");
            return Boolean.FALSE;
        }
        
        if (caminho.getText() == null || tempoRe.getText().isEmpty()) {
            tempo.setText("O caminho não pode ser vazio.");
            return Boolean.FALSE;
        } else {
            try {
                File file = new File(caminho.getText());
                
                if (!file.isDirectory()) {
                    tempo.setText("O caminho não é um diretório válido.");
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                tempo.setText("Ocorreu um problema ao tentar executar.");
                return Boolean.FALSE;
            }
        }
        
        return Boolean.TRUE;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tempo.setText("INATIVO!");
    }
}
