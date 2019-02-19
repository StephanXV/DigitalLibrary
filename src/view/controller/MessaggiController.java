package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controller.UtenteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.UtenteController.*;

public class MessaggiController {
    @FXML
    private Label messa;
    @FXML
    private JFXButton indietro;
    @FXML
    private JFXButton eliminaN;
    @FXML
    private JFXButton eliminaL;
    @FXML
    private JFXListView listNon;
    @FXML
    private JFXListView listLett;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> list1 = new ArrayList<String>();

    @FXML
    void initialize() throws SQLException {
        list.clear();
        list1.clear();
        list = UtenteController.messaggi(getUtente().getEmail(), "no");
        list1 = UtenteController.messaggi(getUtente().getEmail(), "si");
        ObservableList obList = FXCollections.observableList(list);
        ObservableList obList1 = FXCollections.observableList(list1);
        listNon.setItems(obList);
        listLett.setItems(obList1);

        listLett.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(new Image("/view/icons/icons8-new-post-100.png"));
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });

        listNon.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(new Image("/view/icons/icons8-new-post-100.png"));
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });

        for (String s: list){
            messLetti(s,getUtente().getEmail(),"si","update");
        }
    }

    public void eliminaNon(ActionEvent event) throws SQLException {
        ObservableList<String> mess;
        mess = listNon.getSelectionModel().getSelectedItems();
        int i = list.indexOf(mess.get(0));
        eliminaMess(list.get(i),getUtente().getEmail());
        messa.setText("Messaggio ELIMINATO!");
        initialize();
    }

    public void eliminaLet(ActionEvent event) throws SQLException {
        ObservableList<String> mess;
        mess = listLett.getSelectionModel().getSelectedItems();
        int i = list1.indexOf(mess.get(0));
        eliminaMess(list1.get(i),getUtente().getEmail());
        messa.setText("Messaggio ELIMINATO!");
        initialize();
    }

    @FXML
    void indietroMess(ActionEvent event) throws SQLException, IOException {
        Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current_page.hide(); // hide chiude la finestra corrente
    }
}
