package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import model.Autore;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.OperaDAO.inserisciOpera;

public class InsOperaController {
    private ArrayList<String> cate = new ArrayList<String>();
    private ArrayList<Autore> auto = new ArrayList<Autore>();
    @FXML
    private TextField nomeAutor;
    @FXML
    private TextField cognomeAutor;
    @FXML
    private TextField nazAutor;
    @FXML
    private DatePicker dataAutor;
    @FXML
    private Button aggAutor;
    @FXML
    private Button aggCat;
    @FXML
    private Button saveOP;
    @FXML
    private Spinner pagOP;
    @FXML
    private TextField descOP;
    @FXML
    private TextField titOP;
    @FXML
    private TextField codOP;
    @FXML
    private Spinner AnnoOP;
    @FXML
    private ComboBox lingOP;
    @FXML
    private ComboBox categoriaID;
    @FXML
    private Label labINS;

    @FXML
    void initialize(){
        List<String> list = new ArrayList<String>();
        list.add("Letteratura");
        list.add("Economia");
        list.add("Mitologia");
        list.add("Guerre");
        list.add("Armi");
        list.add("Artigianato");
        list.add("Filosofia");
        list.add("Arte");
        list.add("Poesia");
        list.add("Musica");
        list.add("Epigrafi");
        list.add("Istruzione");
        ObservableList obList = FXCollections.observableList(list);
        categoriaID.setItems(obList);

        List<String> list1 = new ArrayList<String>();
        list1.add("IT");
        list1.add("EN");
        list1.add("UK");
        list1.add("TR");
        list1.add("SV");
        list1.add("FR");
        list1.add("RU");
        list1.add("GR");
        list1.add("LA");
        list1.add("JA");
        list1.add("ES");;
        ObservableList obList1 = FXCollections.observableList(list1);
        lingOP.setItems(obList1);
    }

    @FXML
    void aggiungiCategoria(ActionEvent event){
        cate.add((String) categoriaID.getValue());
        labINS.setText("Categoria Verificata!");
    }

    @FXML
    void aggiungiAutore(ActionEvent event){
        Autore A = new Autore(nomeAutor.getText(),cognomeAutor.getText(),dataAutor.getEditor().getText(),nazAutor.getText());
        auto.add(A);
        if (nomeAutor.getText().equals("")) labINS.setText("Autore Non Verificato!");
        else labINS.setText("Autore Verificato!");
    }

    @FXML
    void salvaOpera(ActionEvent event) throws SQLException {
        labINS.setText("Opera Non Inserita!");
        int a = Integer.parseInt(AnnoOP.getEditor().getText());
        int b = Integer.parseInt(pagOP.getEditor().getText());
        boolean x = inserisciOpera(cate,auto,codOP.getText(),titOP.getText(),descOP.getText(),a,(String) lingOP.getValue(),b);
        labINS.setText("Opera Inserita Correttamente!");
        String filePath = new File("").getAbsolutePath();
        File dir = new File(filePath+"\\src\\resources\\images\\"+codOP.getText());
        dir.mkdir();
        auto.clear();
        cate.clear();
    }
}
