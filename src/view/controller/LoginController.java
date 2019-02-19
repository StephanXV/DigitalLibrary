package view.controller;

import com.jfoenix.controls.JFXSpinner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UtenteBase;

import java.io.IOException;
import java.sql.SQLException;

import static controller.UtenteController.getUtenteLogin;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button unknownPassLink;
    @FXML
    private JFXSpinner barCount;

    @FXML
    private Label errorsField;
    private static UtenteBase utente = null;
    private int cont = 0;

    @FXML
    void initialize(){  utente = null; barCount.setVisible(false);}

    @FXML
    void loginButtonFired(ActionEvent event) throws SQLException, IOException {
        if (cont == 2) {cont=0; loginButton.setDisable(true); barCount.setVisible(true); passwordField.setText(""); passwordField.setEditable(false); errorsField.setTextFill(Color.web("#008000")); errorsField.setText("Attendi per 1 minuto e ritenta.");
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(60000), ae -> enable()));
            timeline.play();
        }

        try {
            utente = getUtenteLogin(usernameField.getText(),passwordField.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (utente == null) {
            cont++;
           if (errorsField.getTextFill().toString().equals("0xff0000ff")) errorsField.setText("Email e Password non corretti. Ritenta l'accesso.");
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Home.fxml"));
            // restituisce la finestra che ha generato l'event
            Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene page = new Scene(parent);
            current_page.hide(); // hide chiude la finestra corrente
            current_page.setScene(page);
            current_page.setTitle("Digital Library");
            current_page.show(); // apre la nuova finestra
        }
    }

    @FXML
    void registerButtonFired(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Register.fxml"));
            // restituisce la finestra che ha generato l'event
            Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene page = new Scene(parent);
            current_page.hide(); // hide chiude la finestra corrente
            current_page.setScene(page);
            current_page.setTitle("Registrazione");
            current_page.show(); // apre la nuova finestra
        } catch (Exception e) {
            System.out.println("Can't load Register Page");
        }
    }

    @FXML
    void unknownPassLinkFired(ActionEvent event) throws IOException {
        if (usernameField.getText().isEmpty()) errorsField.setText("Errore: Inserire username per recuperare la password!");
        else {
            ReimpostaPassController.emailUT(usernameField.getText());
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/ReimpostaPass.fxml"));
            // restituisce la finestra che ha generato l'event
            Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene page = new Scene(parent);
            current_page.hide(); // hide chiude la finestra corrente
            current_page.setScene(page);
            current_page.setTitle("Reimposta Password");
            current_page.show(); // apre la nuova finestra
        }
    }


    private void enable(){
        loginButton.setDisable(false); barCount.setVisible(false); passwordField.setEditable(true); errorsField.setTextFill(Color.web("#ff0000")); errorsField.setText("");
    }

}

