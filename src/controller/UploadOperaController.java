package controller;

import DAO.DatabaseConnector;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.*;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static DAO.OperaDAO.*;
import static DAO.PaginaDAO.esisteGiaPagina;
import static DAO.PaginaDAO.insImmagine;

public class UploadOperaController {

    private String sourcePath;

    @FXML
    private JFXComboBox opere;
    @FXML
    private JFXComboBox pagine;
    @FXML
    private Label nomeOpera;
    @FXML
    private Label pagLabel;
    @FXML
    private Label img;
    @FXML
    private ImageView imgView;
    @FXML
    private Label done;
    private String file = "null";

    @FXML
    void initialize(){
        try{
            List<String> list = getOperaImmaginiNonValidate();
            ObservableList obList = FXCollections.observableList(list);
            opere.setItems(obList);
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }

    @FXML
    void opSelect(ActionEvent event){
        try{
            done.setText("");
            pagLabel.setText("Seleziona una pagina non ancora acquisita");
            String a = getNomeopera_byCode((String) opere.getValue());
            nomeOpera.setText("Titolo: " + a);
            pagine.setItems(null);
            Connection connessione = DatabaseConnector.connessioneDB();
            List<Integer> list = new ArrayList<Integer>();
            int numero = getPagineOpera((String) opere.getValue());
            for (int i = 1; i<=numero; i++){
                if (esisteGiaPagina(connessione, (String) opere.getValue(), i))
                    list.add(i);
            }
            ObservableList obList = FXCollections.observableList(list);
            pagine.setItems(obList);

        } catch (Exception ex){
            ex.getStackTrace();
        }
    }

    @FXML
    void pagSelect(ActionEvent event){
        pagLabel.setText("");
        done.setText("");
    }

    @FXML
    void imgSelect(ActionEvent event) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif","png");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    file="File: " + fc.getSelectedFile().getName();
                    sourcePath = fc.getSelectedFile().getPath();
                    try{
                        imgView.setImage(new Image(fc.getSelectedFile().toURI().toURL().toString()));
                        imgView.setPreserveRatio(false);
                        imgView.setStyle("-fx-border-color: black");
                    } catch (Exception ex){
                        ex.getStackTrace();
                    }
                }
            }
        });
    }


    @FXML
    void uploadFired(ActionEvent event){
        try{
            img.setText(file);
            File source = new File(sourcePath);
            String filePath = new File("").getAbsolutePath();
            File destination = new File(filePath +"/src/resources/images/"+opere.getValue()+"/"+pagine.getValue()+".jpg");
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            int id = getIDopera_byCode((String)opere.getValue());
            insImmagine(id, (int) pagine.getValue(), "/resources/images/"+opere.getValue()+"/"+pagine.getValue()+".jpg");
            done.setText("Acquisizione in attesa di validazione!");
        } catch (Exception ex){
            ex.getStackTrace();
        }
    }
}
