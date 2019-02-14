package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Immagine;
import model.UtenteBase;

import static DAO.PaginaDAO.getToTranscribe;
import static controller.LoginController.getUtente;

public class TrascrizioneController {

    private static Immagine selected = null;

    @FXML private TableView<Immagine> table;
    @FXML private TableColumn<Immagine, Integer> colNumero;
    @FXML private TableColumn<Immagine,String> colNome;
    @FXML private TableColumn<Immagine,String> colLingua;
    @FXML private JFXButton trascriviButton;

    @FXML void initialize(){
        UtenteBase user;
        user = getUtente();

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numeroPagina"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeOpera"));
        colLingua.setCellValueFactory(new PropertyValueFactory<>("lingua"));
        ObservableList <Immagine> list = FXCollections.observableArrayList(getToTranscribe(user.getEmail()));
        table.setItems(list);


    }

    public static Immagine getSelected() {
        return selected;
    }

    @FXML void trascriviButtonFired(){
        try{
            if (table.getSelectionModel().getSelectedItem() != null) {
                selected = table.getSelectionModel().getSelectedItem();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/TEI.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                Stage stage = new Stage();
                stage.setTitle("Modifica profilo");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ex){
                ex.getStackTrace();
        }

    }
}
