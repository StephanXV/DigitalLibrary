package view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ImmagineTrascrizione;

import java.io.IOException;
import java.sql.SQLException;

import static controller.OperaController.getNomeOPID;
import static controller.PaginaController.modificaTrascrizione;

public class TrascrizioneConvalidaController {
    @FXML
    private ImageView immV;
    @FXML
    private TextArea tras;
    @FXML
    private JFXButton backbutt;
    @FXML
    private JFXButton modbutt;
    @FXML
    private Label opera;
    @FXML
    private Label data;
    @FXML
    private Label pagina;
    @FXML
    private Label mess;
    private ImmagineTrascrizione trascrizione = ConvalidaController.getTra();

    @FXML
    void initialize() throws SQLException {
        immV.setImage(new Image(trascrizione.getIm().getFonte()));
        tras.setText(trascrizione.getFonte());
        opera.setText(getNomeOPID(trascrizione.getID_opera()));
        Integer i = trascrizione.getNumeroPagina();
        pagina.setText(i.toString());
        data.setText(trascrizione.getTimestamp());

    }

    @FXML
    private void back(ActionEvent event) throws SQLException, IOException {
        Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current_page.hide();
    }

    @FXML
    private void modbutt(ActionEvent event) throws SQLException, IOException {
        modificaTrascrizione(tras.getText(), trascrizione.getID());
        mess.setText("Modifica Effettuata!");
        trascrizione.setFonte(tras.getText());
    }

    @FXML
    private void clickt(MouseEvent event) { mess.setText("");}


}
