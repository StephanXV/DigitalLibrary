package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static controller.UtenteController.registrazioneUtente;
import static controller.UtenteController.verificaEmail;

public class RegisterController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cognomeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField rispostaField;

    @FXML
    private CheckBox maleCheck;

    @FXML
    private CheckBox femaleCheck;

    @FXML
    private DatePicker dateField;

    @FXML
    private ComboBox<String> domandaChoice;

    @FXML
    private Button registerButton;

    @FXML
    private Button indietroButton;

    @FXML private Label nomeError;
    @FXML private Label cognomeError;
    @FXML private Label emailError;
    @FXML private Label passwordError;
    @FXML private Label sexError;
    @FXML private Label dateError;
    @FXML private Label domandaError;
    @FXML private Label rispostaError;

    @FXML
    void initialize() {
        domandaChoice.getItems().addAll("Quale è il cognome di tua madre da nubile?", "Quale è il nome del tuo primo animale domestico?");
    }

    @FXML
    void  maleChecked(ActionEvent event){
        femaleCheck.selectedProperty().set(false);
    }

    @FXML
    void  femaleChecked(ActionEvent event){
        maleCheck.selectedProperty().set(false);
    }

    @FXML
    void  registerButtonFired(ActionEvent event){
        try {
            if (nomeField.getText().equals("")) {
                nomeError.setText("Inserire nome");
            }
            else {
                nomeError.setText("");
            }

            if (cognomeField.getText().equals("")) {
                cognomeError.setText("Inserire cognome");
            }
            else {
                cognomeError.setText("");
            }

            if (emailField.getText().equals("")) {
                emailError.setText("Inserire email");
            }
            else {
                emailError.setText("");
            }

            if (verificaEmail(emailField.getText())) {
                emailError.setText("Email già registrata");
            }

            if (passwordField.getText().equals("")) {
                passwordError.setText("Inserire password");
            }
            else {
                passwordError.setText("");
            }

            if (!(femaleCheck.isSelected() || maleCheck.isSelected())) {
                sexError.setText("Selezionare sesso");
            }
            else {
                sexError.setText("");
            }

            if (dateField.getEditor().getText().equals("")) {
                dateError.setText("Selezionare data di nascita");
            }
            else {
                dateError.setText("");
            }

            if (domandaChoice.getValue()==null) {
                domandaError.setText("Seleziona una domanda");
            }
            else {
                domandaError.setText("");
            }

            if (rispostaField.getText().equals("")) {
                rispostaError.setText("Inserire risposta");
            }
            else {
                rispostaError.setText("");
            }

            if(nomeError.getText().equals("")&&cognomeError.getText().equals("")&&emailError.getText().equals("")
                    &&passwordError.getText().equals("")&&sexError.getText().equals("")&&dateError.getText().equals("")
                    &&domandaError.getText().equals("")&&rispostaError.getText().equals("")){
                String data = dateField.getEditor().getText();
                String giorno = data.substring(0,2);
                String mese = data.substring(3,5);
                String anno = data.substring(6,10);
                String parseData = anno +"-"+mese+"-"+giorno;
                if (femaleCheck.isSelected()) {
                    registrazioneUtente(nomeField.getText(), cognomeField.getText(), parseData, emailField.getText(),
                            passwordField.getText(), "F", domandaChoice.getValue(), rispostaField.getText());
                }
                else
                    registrazioneUtente(nomeField.getText(), cognomeField.getText(), parseData, emailField.getText(),
                            passwordField.getText(), "M", domandaChoice.getValue(), rispostaField.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registrazione");
                alert.setHeaderText(null);
                alert.setContentText("Registrazione effettuata con successo!");
                alert.showAndWait();

                Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
                // restituisce la finestra che ha generato l'event
                Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene page = new Scene(parent);
                current_page.hide(); // hide chiude la finestra corrente
                current_page.setScene(page);
                current_page.setTitle("Login");
                current_page.show(); // apre la nuova finestra
            }


        } catch (Exception ex){
            ex.getStackTrace();
        }
    }

    @FXML
    void  indietroButtonFired(ActionEvent event){
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
