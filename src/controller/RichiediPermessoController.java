package controller;

import DAO.DatabaseConnector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.UtenteDAO.richiestaPermessi;
import static controller.ModificaProfiloController.emailutente;

public class RichiediPermessoController {
    @FXML
    private JFXButton SalvaR;
    @FXML
    private JFXComboBox permessoR;
    @FXML
    private Label labelPermesso;
    @FXML
    private JFXButton buttind;


    @FXML
    void initialize() throws SQLException {
        List<String> list = new ArrayList<>();
        list.add("Privilegiato");
        list.add("Acquisitore");
        list.add("Trascrittore");
        ObservableList obList = FXCollections.observableList(list);
        permessoR.setItems(obList);
    }

    @FXML
    void bottonePermesso(ActionEvent event) throws SQLException {
        String www = emailutente();
        String xy = (String) permessoR.getValue();
        if(checkPerm(www,xy) == 0) {richiestaPermessi(www,xy); labelPermesso.setTextFill(Color.web("#4bc436")); labelPermesso.setText("Privilegio richiesto con successo!");}
        else {labelPermesso.setTextFill(Color.web("#ff0000")); labelPermesso.setText("Hai già richiesto un privilegio ed è in attesa di validazione. Attendi prima di richiederne un altro!");}
    }

    @FXML
    void messAct(ActionEvent event) throws SQLException {
        labelPermesso.setTextFill(Color.web("#375fc6"));
        if (permessoR.getValue().toString().equals("Privilegiato")) {labelPermesso.setText("L'utente PRIVILEGIATO gode del permesso di scaricare un'opera completa ed averla sempre disponibile sul proprio dispositivo.");}
        else if (permessoR.getValue().toString().equals("Acquisitore")) {labelPermesso.setText("L'utente ACQUISITORE può vedere in bacheca anche le opere non complete e gode del permesso di fare l'upload di immagini di pagine in un'opera, per poi essere convalidate da un supervisore.");}
        else if (permessoR.getValue().toString().equals("Trascrittore")) {labelPermesso.setText("L'utente TRASCRITTORE può vedere in bacheca anche le opere non complete e gode del permesso di fare la trascrizione di immagini appartententi a pagine in un'opera, per poi essere convalidate da un trascrittore.");}
    }

    public int checkPerm(String email, String permesso) throws SQLException {
        int cont = 0;
        Connection connessione = DatabaseConnector.connessioneDB();
        String ver = "select count(email) as verifica from privilegio where (assegnato = 'no' and email = '" + email + "');";
        CallableStatement stmt = connessione.prepareCall(ver);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            cont = rs.getInt("verifica");
        }
        connessione.close();
        if (cont == 0) return 0;
        else return 1;
    }

    @FXML
    void buttModInd(ActionEvent event) throws SQLException, IOException {
        Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current_page.hide(); // hide chiude la finestra corrente
    }






}
