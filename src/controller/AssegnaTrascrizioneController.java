package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.OperaDAO.*;
import static DAO.PaginaDAO.getImmagine;
import static DAO.PaginaDAO.insAssTrascrizione;
import static DAO.UtenteDAO.listaTrascrittori;

public class AssegnaTrascrizioneController {
    @FXML
    private JFXButton asst;
    @FXML
    private JFXComboBox tt;
    @FXML
    private JFXComboBox opt;
    @FXML
    private JFXComboBox imt;
    @FXML
    private Label messaget;
    @FXML
    private Label nomet;

    @FXML
    void initialize() throws SQLException {
        List<String> list = new ArrayList<String>();
        list = getOperaTrascrizioniNonValidate();
        ObservableList obList = FXCollections.observableList(list);
        opt.setItems(obList);

        List<String> list1 = new ArrayList<String>();
        list1 = listaTrascrittori("");
        ObservableList obList1 = FXCollections.observableList(list1);
        tt.setItems(obList1);

        imt.setVisible(false);
    }

    @FXML
    void opta(ActionEvent event) throws SQLException {
        String a = getNomeopera_byCode((String) opt.getValue());
        nomet.setText("Nome: " + a);
        imt.setVisible(true);
        List<Integer> list2 = new ArrayList<Integer>();
        int i = getIDopera_byCode((String) opt.getValue());
        list2 = getTrascrizioniNonValidate(i);
        ObservableList obList2 = FXCollections.observableList(list2);
        imt.setItems(obList2);
        messaget.setText("");
    }

    @FXML
    void imta(ActionEvent event) throws SQLException {
        messaget.setText("");
    }

    @FXML
    void tta(ActionEvent event) throws SQLException {
        messaget.setText("");
    }

    @FXML
    void assta(ActionEvent event) throws SQLException {
        int x = getImmagine(getIDopera_byCode((String) opt.getValue()), (int)imt.getValue());
        insAssTrascrizione((String)tt.getValue(),x);
        messaget.setText("Trascrizione Assegnata!");
    }

}
