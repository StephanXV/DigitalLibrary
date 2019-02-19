package view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Immagine;


import java.io.IOException;
import java.sql.SQLException;

import static controller.OperaController.getNomeOPID;

public class ImmagineConvalidaController {
    @FXML
    private ImageView immV;
    @FXML
    private JFXButton backbutt;
    @FXML
    private Label opera;
    @FXML
    private Label data;
    @FXML
    private Label pagina;
    private Immagine immagine = ConvalidaController.getImm();

    @FXML
    void initialize() throws SQLException {
        immV.setImage(new Image(immagine.getFonte()));
        opera.setText(getNomeOPID(immagine.getID_opera()));
        Integer i = immagine.getNumeroPagina();
        pagina.setText(i.toString());
        data.setText(immagine.getTimestamp());
    }

    @FXML
    private void back(ActionEvent event) throws SQLException, IOException {
        Stage current_page = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current_page.hide();
    }



}
