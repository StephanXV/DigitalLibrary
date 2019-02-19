package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Immagine;
import model.ImmagineTrascrizione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.PaginaController.*;

public class ConvalidaController {
    @FXML
    private JFXListView immtra;
    @FXML
    private Label immC;
    @FXML
    private Label traC;
    @FXML
    private Label mess1;
    @FXML
    private JFXButton convBut;
    @FXML
    private JFXRadioButton yesButt;
    @FXML
    private JFXRadioButton noButt;
    String mess = "";
    private int id = 0;
    static Immagine i;
    static ImmagineTrascrizione t;
    private List<Immagine> list = new ArrayList<Immagine>();
    private List<ImmagineTrascrizione> list1 = new ArrayList<ImmagineTrascrizione>();
    private ObservableList obList1;
    private ObservableList obList;

    @FXML
    void initialize() throws SQLException {}

    @FXML
    private void immEn(MouseEvent event) {
        immC.setTextFill(javafx.scene.paint.Color.web("#36c4af"));
    }
    @FXML
    private void immEs(MouseEvent event) {
        immC.setTextFill(Color.web("#375fc6"));
    }
    @FXML
    private void trasEn(MouseEvent event) {
        traC.setTextFill(Color.web("#36c4af"));
    }
    @FXML
    private void trasEx(MouseEvent event) {
        traC.setTextFill(Color.web("#375fc6"));
    }

    @FXML
    private void immClick(MouseEvent event) throws SQLException {
        immC.setUnderline(true); traC.setUnderline(false); mess1.setText("");
        list.clear();
        list1.clear();
        ArrayList<Immagine> Im = getImmNonConvalidate();
        obList = FXCollections.observableList(Im);
        immtra.setItems(obList);
    }

    @FXML
    private void trasClick(MouseEvent event) throws SQLException {
        immC.setUnderline(false); traC.setUnderline(true); mess1.setText("");
        list.clear();
        list1.clear();
        ArrayList<ImmagineTrascrizione> Tr = getTrascNonConvalidate();
        obList1 = FXCollections.observableList(Tr);
        immtra.setItems(obList1);
    }

    @FXML
    private void convselect(ActionEvent event) throws SQLException {
        if (yesButt.isSelected()) mess = "si";
        if (noButt.isSelected()) mess = "no";
    }

    @FXML
    private void listClick(MouseEvent event) throws SQLException, IOException {
        if (list1.isEmpty()) {int i = immtra.getSelectionModel().getSelectedIndex(); id = list.get(i).getID(); setImm(list.get(i)); viewIm();}
        else if (list.isEmpty()) {int i = immtra.getSelectionModel().getSelectedIndex(); id = list1.get(i).getID(); setTra(list1.get(i)); viewTR();}
    }

    @FXML
    private void convMod(ActionEvent event) throws SQLException {
        if (list1.isEmpty()) {convalidaImm(id,mess,i); if (yesButt.isSelected()) mess1.setText("Immagine Convalidata"); else mess1.setText("Immagine Non Convalidata"); list.remove((Immagine) immtra.getSelectionModel().getSelectedItems().get(0));  obList = FXCollections.observableList(list); immtra.setItems(obList);}
        else if (list.isEmpty()) {convalidaTras(id,mess,t.getIm().getID(),t); if (yesButt.isSelected()) mess1.setText("Trascrizione Convalidata"); else mess1.setText("Trascrizione Non Convalidata"); list1.remove((ImmagineTrascrizione) immtra.getSelectionModel().getSelectedItems().get(0)); obList1 = FXCollections.observableList(list1); immtra.setItems(obList1);}

    }

    static void setImm(Immagine im){
        i = im;
    }

    static Immagine getImm(){
        return i;
    }

    static void setTra(ImmagineTrascrizione tr){
        t = tr;
    }

    static ImmagineTrascrizione getTra(){
        return t;
    }

    private void viewIm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/ImmagineConvalida.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Immagine");
        stage.setScene(scene);
        stage.show();
    }

    private void viewTR() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/TrascrizioneConvalida.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Trascrizione");
        stage.setScene(scene);
        stage.show();
    }
}
