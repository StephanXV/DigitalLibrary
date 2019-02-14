package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Trascrittore;
import model.UtenteBase;
import model.UtentePrivilegiato;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static DAO.UtenteDAO.getDomandaSicurezza;
import static DAO.UtenteDAO.updateProfilo;

public class ModificaProfiloController {
    @FXML
    private JFXTextField nomePR1;
    @FXML
    private JFXTextField cognomePR1;
    @FXML
    private JFXTextField nascPR1;
    @FXML
    private JFXTextField iscrPR1;
    @FXML
    private JFXTextField emailPR1;
    @FXML
    private JFXTextField passPR1;
    @FXML
    private JFXComboBox domPR1;
    @FXML
    private JFXTextField rispPR1;
    @FXML
    private JFXComboBox sessoPR1;
    @FXML
    private JFXTextField expPR1;
    @FXML
    private JFXTextField privPR1;
    @FXML
    private JFXButton buttNome;
    @FXML
    private JFXButton buttCognome;
    @FXML
    private JFXButton buttEmail;
    @FXML
    private JFXButton buttPass;
    @FXML
    private JFXButton buttDom;
    @FXML
    private JFXButton buttRisp;
    @FXML
    private JFXButton buttSex;
    @FXML
    private JFXButton buttNasc;
    @FXML
    private JFXButton buttind;
    @FXML
    private ImageView imageProf1;
    @FXML
    private Label labelMod;
    @FXML
    private Label text;

    private static UtenteBase u = LoginController.getUtente();

    @FXML
    void initialize() throws SQLException {
        imageProf1.setImage(new Image ("/view/icons/iconfinder_3_avatar_2754579.png"));
        if (u.getSesso().equals("M")){ imageProf1.setImage(new Image("/view/icons/iconfinder_3_avatar_2754579.png"));}
        else if (u.getSesso().equals("F")) { imageProf1.setImage(new Image ("view/icons/iconfinder_9_avatar_2754584.png"));}
        nomePR1.setPromptText(u.getNome());
        cognomePR1.setPromptText(u.getCognome());
        nascPR1.setPromptText(u.getData_nascita());
        iscrPR1.setPromptText(u.getData_iscrizione());
        emailPR1.setPromptText(u.getEmail());
        passPR1.setPromptText(u.getPass());
        sessoPR1.getItems().addAll("M", "F");
        sessoPR1.setPromptText(u.getSesso());
        if(u.getClass().toString().equals("class model.Trascrittore")) {Integer i =((Trascrittore) u).getExp(); expPR1.setText(i.toString());}
        else expPR1.setText("-");
        if (u.getClass().toString().equals("class model.UtenteBase")) privPR1.setText("base");
        else {
            List<String> perm = (((UtentePrivilegiato)u).getPermessi());
            for (String x: perm){
                if (x.equals(perm.get(perm.size()-1))) privPR1.setText(privPR1.getText() + x);
                else privPR1.setText(privPR1.getText() + x + " - ");
            }}
        domPR1.getItems().addAll("Quale è il cognome di tua madre da nubile?", "Quale è il nome del tuo primo animale domestico?");
        domPR1.setPromptText(getDomandaSicurezza(u.getEmail()).get(0));
        rispPR1.setPromptText(getDomandaSicurezza(u.getEmail()).get(1));
    }

    @FXML
    void buttModNome(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"nome",nomePR1.getText()); u.setNome(nomePR1.getText()); updateUtente();}
    @FXML
    void buttModCognome(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"cognome",cognomePR1.getText()); u.setCognome(cognomePR1.getText()); updateUtente();}
    @FXML
    void buttModEmail(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"email",emailPR1.getText()); u.setEmail(emailPR1.getText()); updateUtente();}
    @FXML
    void buttModPass(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"pass",passPR1.getText()); u.setPass(passPR1.getText()); updateUtente();}
    @FXML
    void buttModDom(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"domanda_sicurezza",(String)domPR1.getValue()); updateUtente();}
    @FXML
    void buttModRisp(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"risposta_sicurezza",rispPR1.getText()); updateUtente();}
    @FXML
    void buttModSex(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"sesso",(String)sessoPR1.getValue()); u.setSesso((String)sessoPR1.getValue()); updateUtente();}
    @FXML
    void buttModNasc(ActionEvent event) throws SQLException { updateProfilo(u.getEmail(),"data_nascita",nascPR1.getText()); u.setData_nascita(nascPR1.getText()); updateUtente();}
    @FXML
    void buttModInd(ActionEvent event) throws SQLException, IOException {
        Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current_page.hide(); // hide chiude la finestra corrente
    }

    private void updateUtente(){
       LoginController.setUtente(u);
       labelMod.setText("Campo Modificato");
    }

    @FXML
    private void textEN(MouseEvent event) {
        text.setTextFill(javafx.scene.paint.Color.web("#36c4af"));
    }
    @FXML
    private void textEX(MouseEvent event) {
        text.setTextFill(Color.web("#375fc6"));
    }
    @FXML
    private void textClick(MouseEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/RichiediPermesso.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Richiedi Privilegi");
        stage.setScene(scene);
        stage.show();
    }

    static String emailutente(){
       return u.getEmail();
    }





}
