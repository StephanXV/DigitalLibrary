package controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import model.*;
import DAO.PaginaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

import static DAO.AutoreDAO.getAut;
import static DAO.CategoriaDAO.getCategorie;
import static DAO.OperaDAO.getNomeopera_byID;
import static DAO.OperaDAO.getOp;
import static controller.LoginController.getUtente;
import static java.lang.Thread.sleep;

public class VisualizzaOperaController {
	
	@FXML
	private ImageView immagine;
	
	@FXML
	private Label trascrizione;
	@FXML
	private Label down;
	@FXML
	private Label tit;
	@FXML
	private Label aut;
	@FXML
	private Label cat;
	@FXML
	private Label pag;
	@FXML
	private Label anno;
	
	@FXML
	private Polygon back;
	
	@FXML
	private Polygon forward;
	
	@FXML
	private JFXListView<String> miniature;
	
	@FXML
	private JFXButton download;
	
	private static int id;
	
	private ArrayList<String> list = new ArrayList<String>();
	
	private int i=-1;
	
	private int z;
	
	public int getId() {
		return id;
	}

	public static void setId(int id) {
		VisualizzaOperaController.id = id;
	}

	ArrayList<ImmagineTrascrizione> pagine = new ArrayList<ImmagineTrascrizione>();
	
	@FXML
	void initialize() throws SQLException {
		UtenteBase u = getUtente();
		if (u.getClass().toString().equals("class model.UtenteBase")) download.setDisable(true);
		else { List<String> priv = ((UtentePrivilegiato)getUtente()).getPermessi();
		if (!priv.contains("privilegiato") && !priv.contains("amministratore")) download.setDisable(true);}
		pagine = PaginaDAO.getTrascrizioniConv(id);

		Opera a = getOp(id);
		tit.setText(a.getTitolo());
		anno.setText(""+a.getDate());
		pag.setText("1");
		List<String> cate = getCategorie(a.getCodice());
		for(String i: cate) {
			if (i.equals(cate.get(cate.size()-1))) cat.setText(cat.getText() + i);
			else cat.setText(cat.getText() + i + ", "); }
		List<String> autori = getAut(id);
		for(String i: autori) {
			if (i.equals(autori.get(autori.size()-1))) aut.setText(aut.getText() + i);
			else aut.setText(aut.getText() + i + ", "); }

	
		immagine.setImage(new Image(pagine.get(0).getIm().getFonte()));
		trascrizione.setText(pagine.get(0).getFonte());
		

        for (ImmagineTrascrizione i : pagine) {
            list.add(""+i.getNumeroPagina());
        }
        ObservableList<String> obList = FXCollections.observableList(list);
        miniature.setItems(obList);  
		z=list.size();

        
        miniature.setCellFactory(param -> new ListCell<String>(){
            private ImageView img = new ImageView();
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if (empty){
                    setText(null);
                    setGraphic(null);
                }
                else {
                	if (i!=-1) {
                    setText(name);
                    img.setImage(new Image(pagine.get(i).getIm().getFonte()));
                    img.setFitHeight(120);
                    img.setPreserveRatio(true);
                    setGraphic(img);
                    if (i<z-1) i++;
                	}
                	else i++;
                }
                
            }
        });
        

        
        
	}
	
	int p=0;
	
	@FXML
	void backIsFired (MouseEvent event) {
		down.setText("");
		if (p>0) {
			p--;
			immagine.setImage(new Image(pagine.get(p).getIm().getFonte()));
			trascrizione.setText(pagine.get(p).getFonte());
		}
		pag.setText(""+pagine.get(p).getNumeroPagina());
	}
	
	@FXML
	void forwardIsFired (MouseEvent event) {
		down.setText("");
		if (p<z-1) {
			p++;
			immagine.setImage(new Image(pagine.get(p).getIm().getFonte()));
			trascrizione.setText(pagine.get(p).getFonte());
		}
		pag.setText(""+pagine.get(p).getNumeroPagina());
	}
	
    @FXML
    private void listClick(MouseEvent event) {
		down.setText("");
        int f = miniature.getSelectionModel().getSelectedIndex();  
        immagine.setImage(new Image(pagine.get(f).getIm().getFonte()));
        trascrizione.setText(pagine.get(f).getFonte());
        p = miniature.getSelectionModel().getSelectedIndex();
        pag.setText(""+pagine.get(p).getNumeroPagina());
    }

	@FXML
	void downloadIsFired(ActionEvent event) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				String sourcePath="";
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					sourcePath = chooser.getSelectedFile().getPath();
				}
				String filePath = new File("").getAbsolutePath();
				Path path1 = Paths.get(filePath + "/src/resources/PDF/"+id+".pdf");
				String path2 = Paths.get(sourcePath).toString();
				try {
					Files.copy(path1 , (new File(path2 + "/" + id +".pdf")).toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


		}); 
	}

	@FXML
	private void frecciaEN(MouseEvent event) {
		forward.setStyle("-fx-fill: #2a2d57");
	}

	@FXML
	private void frecciaEX(MouseEvent event) {
		forward.setStyle("-fx-fill: #1e90ff");
	}

	@FXML
	private void freccia1EN(MouseEvent event) {
		back.setStyle("-fx-fill: #2a2d57");
	}

	@FXML
	private void freccia1EX(MouseEvent event) {
		back.setStyle("-fx-fill: #1e90ff");
	}

}
