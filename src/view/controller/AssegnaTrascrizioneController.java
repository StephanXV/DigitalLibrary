package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.OperaController.*;
import static controller.PaginaController.assegnaTrascrizione;
import static controller.PaginaController.getImmagineByOp;
import static controller.UtenteController.listaTrasc;

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
        list = getOperaTrascriNonValidate();
        ObservableList obList = FXCollections.observableList(list);
        opt.setItems(obList);

        List<String> list1 = new ArrayList<String>();
        list1 = listaTrasc("");
        ObservableList obList1 = FXCollections.observableList(list1);
        tt.setItems(obList1);

        imt.setVisible(false);
    }

    @FXML
    void opta(ActionEvent event) throws SQLException {
        String a = getNomeOP((String) opt.getValue());
        nomet.setText("Nome: " + a);
        imt.setVisible(true);
        List<Integer> list2 = new ArrayList<Integer>();
        int i = getIDOP((String) opt.getValue());
        list2 = getTrascrizioniNonValidateOpera(i);
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
        int x = getImmagineByOp(getIDOP((String) opt.getValue()), (int)imt.getValue());
        assegnaTrascrizione((String)tt.getValue(),x);
        messaget.setText("Trascrizione Assegnata!");
    }

}
