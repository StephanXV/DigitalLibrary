package view.controller;

import controller.UtenteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.UtenteController.listaTrasc;
import static controller.UtenteController.updateProf;

public class AssegnaEsperienzaController {
    @FXML
    private ComboBox trascrittoreID;
    @FXML
    private ComboBox esperienzaID;
    @FXML
    private Button saveEXP;
    @FXML
    private Label espAtt;
    @FXML
    private Label messageEXP;

    @FXML
    void initialize() throws SQLException {
        List<String> list = new ArrayList<String>();
        list = listaTrasc("");
        ObservableList obList = FXCollections.observableList(list);
        trascrittoreID.setItems(obList);

        List<String> list1 = new ArrayList<String>();
        list1.add("0");
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        ObservableList obList1 = FXCollections.observableList(list1);
        esperienzaID.setItems(obList1);
    }

    @FXML
    void salvaEXP(ActionEvent event) throws SQLException {
        String x = null;
        String e = (String) esperienzaID.getValue();
        if (e.equals("0")) x = "zero";
        else if (e.equals("1")) x = "one";
        else if (e.equals("2")) x = "two";
        else if (e.equals("3")) x = "three";
        else if (e.equals("4")) x = "four";
        else if (e.equals("5")) x = "five";
        updateProf((String)trascrittoreID.getValue(),"esperienza",x);
        messageEXP.setText("Esperienza utente aggiornata!");
        espAtt.setText(e);
    }

    @FXML
    void trAct(ActionEvent event) throws SQLException {
        String a = listaTrasc((String)trascrittoreID.getValue()).get(0);
        if (a.equals("zero")) a = "0";
        else if (a.equals("one")) a = "1";
        else if (a.equals("two")) a = "2";
        else if (a.equals("three")) a = "3";
        else if (a.equals("four")) a = "4";
        else if (a.equals("five")) a = "5";
        espAtt.setText(a);
        messageEXP.setText("");
    }

    @FXML
    void esAct(ActionEvent event) throws SQLException {
        messageEXP.setText("");
    }

}
