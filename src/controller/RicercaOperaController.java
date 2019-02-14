package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import DAO.OperaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Autore;
import model.Opera;

public class RicercaOperaController {
	
	@FXML
	private Label labelTop;
	
    @FXML
    private TextField cerca;
	
    @FXML
    private JFXListView<Button> opere1;
    
    @FXML
    private JFXListView<Button> opere2;
    
    private List<Button> list1 = new ArrayList<Button>();
    
    private List<Button> list2 = new ArrayList<Button>();

    private ArrayList<Opera> lista = new ArrayList<Opera>();
    
    private String ricerca = HomeController.text;
    
    private static int idOpera;
	
    public static int getIdOpera() {
		return idOpera;
	}

	public static void setIdOpera(int idOpera) {
		RicercaOperaController.idOpera = idOpera;
	}

	@FXML
    void initialize() {
        list1.clear();
        list2.clear();
        labelTop.setText("Ecco i risultati della ricerca: " + ricerca);
        try {
            lista = OperaDAO.operaSearch(ricerca);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int i=0;
        for (Opera o:lista) {
            Image icon1 = new Image(getClass().getResourceAsStream("/view/icons/icons8-literature-100.png"));
            ImageView im = new ImageView(icon1);
            im.setFitHeight(30);
            im.setPreserveRatio(true);
        	JFXButton b = new JFXButton("",im);
        	b.setPrefWidth(395);
        	b.setPrefHeight(46);
        	b.setAlignment(Pos.CENTER_LEFT);
            b.setTextFill(Color.web("#375fc6"));
            String autori = "Autori: ";
            for (int j=0; j<o.getAutore().size(); j++){
                if(j==o.getAutore().size()-1) autori = autori + o.getAutore().get(j).getNome()+" "+o.getAutore().get(j).getCognome();
                else {autori = autori + o.getAutore().get(j).getNome()+" "+o.getAutore().get(j).getCognome() + ", ";}
            }
        	b.setText("  "+o.getTitolo()+"\n  "+autori);
        	b.setId(""+o.getID());
            b.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    b.setStyle("-fx-background-color:  #375fc6; -fx-text-fill: white");
                }
            });
            b.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    b.setStyle("-fx-background-color:   transparent; -fx-text-fill:   #375fc6");
                }
            });
        	b.setOnAction(new EventHandler<ActionEvent>() {
        		public void handle(ActionEvent e) {
        	        try{
        	            
        	                idOpera = Integer.parseInt(b.getId());
        	                VisualizzaOperaController.setId(idOpera);
        	                FXMLLoader fxmlLoader = new FXMLLoader();
        	                fxmlLoader.setLocation(getClass().getResource("/view/VisualizzaOpera.fxml"));
        	                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        	                Stage stage = new Stage();
        	                stage.setTitle("Visualizza Opera");
        	                stage.setScene(scene);
        	                stage.show();
        	            
        	        } catch (Exception ex){
        	                ex.getStackTrace();
        	        }
                       
        		}
        	});
            if (i%2==0) list1.add(b);
            else  list2.add(b);
            i++;
        }
         
            ObservableList<Button> obList = FXCollections.observableList(list1);
            ObservableList<Button> obList1 = FXCollections.observableList(list2);
            opere1.setItems(obList);
            opere2.setItems(obList1);
    


}

}

