package view.controller;

import controller.UtenteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Trascrittore;
import model.UtenteBase;
import model.UtentePrivilegiato;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.UtenteController.getDomanda;
import static controller.UtenteController.getUtente;

public class VisualizzaProfiloController {
    @FXML
    private Button modificaBut;
    @FXML
    private Button mostraButt;
    @FXML
    private Label nomePR;
    @FXML
    private Label cognomePR;
    @FXML
    private Label nascPR;
    @FXML
    private Label iscrPR;
    @FXML
    private Label emailPR;
    @FXML
    private Label passPR;
    @FXML
    private Label domandaPR;
    @FXML
    private Label rispostaPR;
    @FXML
    private Label sessoPR;
    @FXML
    private Label expPR;
    @FXML
    private Label privPR;
    @FXML
    private Label mostraPass;
    @FXML
    private Label labmess;
    @FXML
    private ImageView imageProf;
    @FXML
    private ImageView messaggi;
    @FXML
    private ImageView indicatore;
    private UtenteBase u = null;
    private int cont = 0;

    @FXML
    void initialize() throws SQLException {
        u = getUtente();
        if (u.getSesso().equals("M")){ imageProf.setImage(new Image ("/view/icons/iconfinder_3_avatar_2754579.png"));}
        else if (u.getSesso().equals("F")) { imageProf.setImage(new Image ("view/icons/iconfinder_9_avatar_2754584.png"));}
        nomePR.setText(u.getNome());
        cognomePR.setText(u.getCognome());
        nascPR.setText(u.getData_nascita());
        iscrPR.setText(u.getData_iscrizione());
        emailPR.setText(u.getEmail());
        sessoPR.setText(u.getSesso());
        if(u.getClass().toString().equals("class model.Trascrittore")) {Integer i =((Trascrittore) u).getExp(); expPR.setText(i.toString());}
        else expPR.setText("-");
        if (u.getClass().toString().equals("class model.UtenteBase")) privPR.setText("base");
        else {
            List<String> perm = (((UtentePrivilegiato)u).getPermessi());
                for (String x: perm){
                    if (x.equals(perm.get(perm.size()-1))) privPR.setText(privPR.getText() + x);
                    else privPR.setText(privPR.getText() + x + " - ");
        }}
        domandaPR.setText(getDomanda(u.getEmail()).get(0));
        rispostaPR.setText(getDomanda(u.getEmail()).get(1));

        if (UtenteController.messaggi(u.getEmail(),"no").size()==0) indicatore.setVisible(false);
        else indicatore.setVisible(true);
    }


    @FXML
    void buttMod(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/ModificaProfilo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Modifica profilo");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void mostraPass1(MouseEvent event) {
        if ((cont%2)==0) passPR.setText(u.getPass()); else passPR.setText("**************");
        cont++;
    }

    @FXML
    private void mostraMouse(MouseEvent event) {
        mostraPass.setTextFill(Color.web("#36c4af"));
    }
    @FXML
    private void esciMouse(MouseEvent event) {
        mostraPass.setTextFill(Color.web("#375fc6"));
    }

    @FXML
    private void mess(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/Messaggi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Modifica profilo");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void messEN(MouseEvent event) {
        labmess.setTextFill(Color.web("#36c4af"));
    }
    @FXML
    private void messEX(MouseEvent event) {
        labmess.setTextFill(Color.web("#375fc6"));
    }





}
