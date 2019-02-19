package view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import static controller.UtenteController.getDomanda;
import static controller.UtenteController.updateProf;

public class ReimpostaPassController {
    String domanda = null;
    String risposta = null;
    static String em = "";

    @FXML
    private TextField nuovaPass;

    @FXML
    private TextField rispostaSic;

    @FXML
    private Button salvaButton1;

    @FXML
    private Label domandaSic;

    @FXML
    private Label message;

    @FXML
    private Button indietroButton1;

    @FXML
    void initialize() throws SQLException {
        domanda = getDomanda(em).get(0);
        risposta = getDomanda(em).get(1);
        domandaSic.setText(domanda);
    }


    @FXML
    void savefired(ActionEvent event){
        try {
            if (rispostaSic.getText().equals(risposta)) {
            updateProf(em, "pass", nuovaPass.getText());
            message.setText("Nuova password impostata: " + nuovaPass.getText()); }
           else {message.setText("Risposta non conforme");}
        } catch (Exception ex){
            message.setText("Errore: inserire correttamente i dati richiesti");
        }
    }

    public static void emailUT(String e){
        em = e;
    }

    @FXML
    void  indietroButton1Fired(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
            // restituisce la finestra che ha generato l'event
            Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene page = new Scene(parent);
            current_page.hide(); // hide chiude la finestra corrente
            current_page.setScene(page);
            current_page.setTitle("Login");
            current_page.show(); // apre la nuova finestra
        } catch (Exception e) {
            System.out.println("Can't load Login Page");
        }
    }

}
