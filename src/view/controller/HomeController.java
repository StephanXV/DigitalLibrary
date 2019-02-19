package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.UtenteBase;
import model.UtentePrivilegiato;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static controller.UtenteController.getUtente;


public class HomeController {

    @FXML private BorderPane borderpane;
    @FXML private Button homeButton;
    @FXML private Button profiloButton;
    @FXML private Button acquisizioneButton;
    @FXML private Button insOperaButton;
    @FXML private Button trascrizioneButton;
    @FXML private Button revAcquisizioniButton;
    @FXML private Button assTrascrizioniButton;
    @FXML private Button assExpButton;
    @FXML private Button adminButton;
    @FXML private Button pubblicaOperaButton;
    @FXML private Button cercaOpera;
    @FXML private Label displayname;
    @FXML private TextField cerca;
    @FXML private VBox vbox;
    
    public static String text = "";

    @FXML
    void initialize(){
        UtenteBase user;
        user = getUtente();
        if(user.getClass().toString().equals("class model.UtenteBase")){
            vbox.getChildren().remove(insOperaButton);
            vbox.getChildren().remove(trascrizioneButton);
            vbox.getChildren().remove(revAcquisizioniButton);
            vbox.getChildren().remove(assTrascrizioniButton);
            vbox.getChildren().remove(pubblicaOperaButton);
            vbox.getChildren().remove(assExpButton);
            vbox.getChildren().remove(adminButton);
            vbox.getChildren().remove(acquisizioneButton);
        }
        else if (user.getClass().toString().equals("class model.UtentePrivilegiato") || user.getClass().toString().equals("class model.Trascrittore")) {
            List<String> permessi = new ArrayList();
            for (String x : ((UtentePrivilegiato) user).getPermessi()) {
                permessi.add(x);
            }
            if (!permessi.contains("amministratore"))
                vbox.getChildren().remove(adminButton);
            if (permessi.contains("amministratore")){
                permessi.add("trascrittore");
                permessi.add("acquisitore");
                permessi.add("supervisore");
            }
            if (!permessi.contains("trascrittore"))
                vbox.getChildren().remove(trascrizioneButton);
            if (!permessi.contains("acquisitore")) {
                vbox.getChildren().remove(insOperaButton);
                vbox.getChildren().remove(acquisizioneButton);
            }
            if (!permessi.contains("supervisore")) {
                vbox.getChildren().remove(assTrascrizioniButton);
                vbox.getChildren().remove(assExpButton);
                vbox.getChildren().remove(revAcquisizioniButton);
                vbox.getChildren().remove(pubblicaOperaButton);
            }
        }
        String nome = user.getNome();
        String cognome = user.getCognome();
        String toDisplay = nome.substring(0, 1).toUpperCase() + nome.substring(1) + " " +cognome.substring(0, 1).toUpperCase() + cognome.substring(1);

        displayname.setText("Benvenuto " + toDisplay);
        loadContent("/view/fxml/Presentation.fxml");
    }

    @FXML
    void homeButtonFired(ActionEvent event){
        loadContent("/view/fxml/Presentation.fxml");
    }

    @FXML
    void profiloButtonFired(ActionEvent event) { loadContent("/view/fxml/VisualizzaProfilo.fxml"); }

    @FXML
    void acquisizioneButtonFired(ActionEvent event){ loadContent("/view/fxml/UploadOpera.fxml");}

    @FXML
    void pubOperaFired(ActionEvent event){ loadContent("/view/fxml/PubblicaOpera.fxml");}

    @FXML
    void insOperaButtonFired(ActionEvent event){ loadContent("/view/fxml/InserisciOpera.fxml");}

    @FXML
    void trascrizioneButtonFired(ActionEvent event){ loadContent("/view/fxml/Trascrizione.fxml");}

    @FXML
    void revAcquisizioniButtonFired(ActionEvent event){loadContent("/view/fxml/Convalida.fxml");}

    @FXML
    void assTrascrizioniButtonFired(ActionEvent event){loadContent("/view/fxml/AssegnaTrascrizioni.fxml");}

    @FXML
    void assExpButtonFired(ActionEvent event){
        loadContent("/view/fxml/AssegnaEsperienza.fxml");
    }

    @FXML
    void adminButtonFired(ActionEvent event){loadContent("/view/fxml/GestionePrivilegi.fxml");}

    @FXML
    void logoutFired(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Sei sicuro di voler uscire?");
        ButtonType buttonTypeOne = new ButtonType("Si");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
            try{
                Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
                // restituisce la finestra che ha generato l'event
                Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene page = new Scene(parent);
                current_page.hide(); // hide chiude la finestra corrente
                current_page.setScene(page);
                current_page.setTitle("Login");
                current_page.show(); // apre la nuova finestra
            } catch (Exception ex){
                ex.getStackTrace();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void loadLogin(){}
    @FXML
    void cercaOperaIsFired(ActionEvent event){
    	
    	text=cerca.getText();
    	loadContent("/view/fxml/RicercaOpera.fxml");
    	
    }

    public void loadContent(String content){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(content));
        } catch (Exception ex){
            ex.getStackTrace();
            System.out.println("Can't load " + content);
        }
        borderpane.setCenter(root);

    }
    @FXML
    private void homeEN(MouseEvent event) { homeButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void homeEX(MouseEvent event) { homeButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void proEN(MouseEvent event) { profiloButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void proEX(MouseEvent event) { profiloButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void acqEN(MouseEvent event) { acquisizioneButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void acqEX(MouseEvent event) { acquisizioneButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void opEN(MouseEvent event) { insOperaButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void opEX(MouseEvent event) { insOperaButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void traEN(MouseEvent event) { trascrizioneButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void traEX(MouseEvent event) { trascrizioneButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void convEN(MouseEvent event) { revAcquisizioniButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void convEX(MouseEvent event) { revAcquisizioniButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void assEN(MouseEvent event) { assTrascrizioniButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void assEX(MouseEvent event) { assTrascrizioniButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void expEN(MouseEvent event) { assExpButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void expEX(MouseEvent event) { assExpButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void ammEN(MouseEvent event) { adminButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void ammEX(MouseEvent event) { adminButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void pubEN(MouseEvent event) { pubblicaOperaButton.setTextFill(javafx.scene.paint.Color.web("#2196f3"));}
    @FXML
    private void pubEX(MouseEvent event) { pubblicaOperaButton.setTextFill(Color.web("#a0a2ab")); }
    @FXML
    private void clickHome(MouseEvent event) { changeColor(); homeButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickPro(MouseEvent event) { changeColor(); profiloButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickAss(MouseEvent event) { changeColor(); assTrascrizioniButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickAmm(MouseEvent event) { changeColor(); adminButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickExp(MouseEvent event) { changeColor(); assExpButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void ClickRev(MouseEvent event) { changeColor(); revAcquisizioniButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickTra(MouseEvent event) { changeColor(); trascrizioneButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickAcq(MouseEvent event) { changeColor(); acquisizioneButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickIns(MouseEvent event) { changeColor(); insOperaButton.setStyle("-fx-background-color:   #262c3c");}
    @FXML
    private void clickPub(MouseEvent event) { changeColor(); pubblicaOperaButton.setStyle("-fx-background-color:   #262c3c");}

    private void changeColor(){
        homeButton.setStyle("-fx-background-color:   #2d3447");
        assTrascrizioniButton.setStyle("-fx-background-color:   #2d3447");
        assExpButton.setStyle("-fx-background-color:   #2d3447");
        profiloButton.setStyle("-fx-background-color:   #2d3447");
        adminButton.setStyle("-fx-background-color:   #2d3447");
        insOperaButton.setStyle("-fx-background-color:   #2d3447");
        trascrizioneButton.setStyle("-fx-background-color:   #2d3447");
        acquisizioneButton.setStyle("-fx-background-color:   #2d3447");
        revAcquisizioniButton.setStyle("-fx-background-color:   #2d3447");
        pubblicaOperaButton.setStyle("-fx-background-color:   #2d3447");
    }

}
