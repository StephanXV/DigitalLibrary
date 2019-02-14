package controller;

import DAO.PaginaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import model.Immagine;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static DAO.PaginaDAO.getTrans;

public class TEIController {

    @FXML private ImageView holder;
    @FXML private HTMLEditor editor;
    @FXML private Label alert;
    private Immagine img = TrascrizioneController.getSelected();

    @FXML void initialize() throws SQLException {
        Immagine img = TrascrizioneController.getSelected();
        holder.setImage(new Image(img.getFonte()));
        editor.setHtmlText(getTrans(img.getNumeroPagina(), img.getID_opera()));
    }

    @FXML void saveButtonFired(ActionEvent event){
        String tr = getText(editor.getHtmlText());
        try{
            PaginaDAO.insTrascrizione(img.getNumeroPagina(), tr, 0, img.getID_opera());
            alert.setText("Trascrizione salvata e in attesa di verifica!");
        } catch (Exception ex){
            ex.getStackTrace();
        }
    }

    public static String getText(String htmlText) {

        String result = "";

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer text = new StringBuffer(htmlText.length());

        while (matcher.find()) {
            matcher.appendReplacement(
                    text,
                    " ");
        }

        matcher.appendTail(text);

        result = text.toString().trim();

        return result;
    }

}
