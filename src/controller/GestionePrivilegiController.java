package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.*;
import javafx.fxml.Initializable;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static DAO.AdminDAO.accettaPermessi;
import static DAO.AdminDAO.permessiInAttesa;

public class GestionePrivilegiController extends ListView<String> {
    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    @FXML
    private JFXListView<String> privID;
    @FXML
    private JFXListView<String> privID1;
    @FXML
    private JFXButton but1;
    @FXML
    private JFXRadioButton permID;
    @FXML
    private JFXRadioButton permID1;
    String messaggio = null;
    Image profile = new Image("/view/icons/icons8-user-100.png");
    Image profile1 = new Image("/view/icons/quality.png");



    @FXML
    void initialize() throws SQLException {
        list.clear();
        list1.clear();
        TreeMap<String,String> mappa = permessiInAttesa();
        for (Map.Entry<String, String> entry : mappa.entrySet()) {
            list.add(entry.getKey());
            list1.add(entry.getValue());
        }
        ObservableList obList = FXCollections.observableList(list);
        ObservableList obList1 = FXCollections.observableList(list1);
        privID.setItems(obList);
        privID1.setItems(obList1);
        privID1.setMouseTransparent( true );
        privID1.setFocusTraversable( false );


        privID.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(profile);
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });

        privID1.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(profile1);
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        }); }


    public void buttonPriv1(ActionEvent event) throws SQLException {
        ObservableList<String> email;
        email = privID.getSelectionModel().getSelectedItems();
        int i = list.indexOf(email.get(0));
        accettaPermessi(email.get(0), list1.get(i), messaggio);
        initialize();
    }

    public void permselect (ActionEvent event) throws SQLException {
        if (permID1.isSelected()) messaggio = "si";
        if (permID.isSelected()) messaggio = "no";
    }

}
