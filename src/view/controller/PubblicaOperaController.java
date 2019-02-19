package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static controller.OperaController.*;

public class PubblicaOperaController {
    @FXML
    private JFXListView listOpinc;
    @FXML
    private Label complIm;
    @FXML
    private Label complTras;
    @FXML
    private Label mess;
    @FXML
    private JFXButton pubbBut;
    private List<String> list = new ArrayList<String>();
    private Image oper = new Image("/view/icons/books-stack-of-three.png");
    ObservableList obList = null;

    @FXML
    void initialize() throws SQLException {}

    @FXML
    private void immEn(MouseEvent event) {
        complIm.setTextFill(javafx.scene.paint.Color.web("#36c4af"));
    }
    @FXML
    private void immEs(MouseEvent event) {
        complIm.setTextFill(Color.web("#375fc6"));
    }
    @FXML
    private void trasEn(MouseEvent event) {
        complTras.setTextFill(Color.web("#36c4af"));
    }
    @FXML
    private void trasEx(MouseEvent event) {
        complTras.setTextFill(Color.web("#375fc6"));
    }

    @FXML
    private void immClick(MouseEvent event) throws SQLException {
        complIm.setUnderline(true); complTras.setUnderline(false);
        list.clear();
        ArrayList<String> opI = opereIncompleteIMG();
        for (String x : opI) {
            list.add(x);
        }
        ObservableList obList = FXCollections.observableList(list);
        listOpinc.setItems(obList);
        listOpinc.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(oper);
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });
    }

    @FXML
    private void trasClick(MouseEvent event) throws SQLException {
        complIm.setUnderline(false); complTras.setUnderline(true);
        list.clear();
        ArrayList<String> opT = opereIncompleteTRS();
        for (String x : opT) {
            list.add(x);
        }
        obList = FXCollections.observableList(list);
        listOpinc.setItems(obList);
        listOpinc.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(oper);
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });
    }

    @FXML
    private void listClick(MouseEvent event) throws SQLException {
        ObservableList<String> codice;
        codice = listOpinc.getSelectionModel().getSelectedItems();
        mess.setTextFill(Color.web("#375fc6"));
        mess.setText("Titolo Opera: " + getNomeOP(codice.get(0)));
    }

    @FXML
    private void pubbMod(ActionEvent event) throws SQLException {
        ObservableList<String> codice;
        codice = listOpinc.getSelectionModel().getSelectedItems();
        pubblica(codice.get(0));
        mess.setTextFill(Color.web("#11d31b"));
        mess.setText("Opera pubblicata!");
        list.remove(codice.get(0));
        obList = FXCollections.observableList(list);
        listOpinc.setItems(obList);
        listOpinc.setCellFactory(param -> new ListCell<String>(){
            ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                    setText(name);
                    img.setImage(oper);
                    img.setFitWidth(20);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                }
            }
        });



}
}
