package view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Opera;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static controller.OperaController.getCopertina;

import static controller.OperaController.cercaOpere;


public class PresentationController {
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView im3;
    @FXML
    private ImageView im4;
    @FXML
    private ImageView im5;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private Label vediTutte;
    private ArrayList<Opera> a = cercaOpere("");

    public PresentationController() throws SQLException {
    }


    @FXML
    void initialize() throws SQLException {
        Collections.sort(a,new OrdinaOp());
        String uno = getCopertina(a.get(0).getID());
        String due = getCopertina(a.get(1).getID());
        String tre = getCopertina(a.get(2).getID());
        String quattro = getCopertina(a.get(3).getID());
        String cinque = getCopertina(a.get(4).getID());
        im1.setImage(new Image(uno));
        im1.setPreserveRatio(false);
        im2.setImage(new Image(due));
        im2.setPreserveRatio(false);
        im3.setImage(new Image(tre));
        im3.setPreserveRatio(false);
        im4.setImage(new Image(quattro));
        im4.setPreserveRatio(false);
        im5.setImage(new Image(cinque));
        im5.setPreserveRatio(false);
        lab1.setText(a.get(0).getTitolo());
        lab2.setText(a.get(1).getTitolo());
        lab3.setText(a.get(2).getTitolo());
        lab4.setText(a.get(3).getTitolo());
        lab5.setText(a.get(4).getTitolo());

    }

    static class OrdinaOp implements Comparator<Opera> {
        public int compare(Opera o1, Opera o2) {
            if (o1.getID() > o2.getID()) return -1;
            if (o1.getID() < o2.getID()) return 1;
            else return 0;
        }
    }

    @FXML
    private void vediClick(MouseEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/RicercaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 729);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    private void vediEN(MouseEvent event) throws SQLException, IOException {vediTutte.setTextFill(javafx.scene.paint.Color.web("#19ae8b")); vediTutte.setUnderline(true);}
    @FXML
    private void vediEX(MouseEvent event) throws SQLException, IOException {vediTutte.setTextFill(javafx.scene.paint.Color.web("#a1a6a5")); vediTutte.setUnderline(false);}
    @FXML
    private void imm1(MouseEvent event) throws SQLException, IOException {
        VisualizzaOperaController.setId(a.get(0).getID());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/VisualizzaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();}
    @FXML
    private void imm2(MouseEvent event) throws SQLException, IOException {
        VisualizzaOperaController.setId(a.get(1).getID());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/VisualizzaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void imm3(MouseEvent event) throws SQLException, IOException {
        VisualizzaOperaController.setId(a.get(2).getID());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/VisualizzaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void imm4(MouseEvent event) throws SQLException, IOException {
        VisualizzaOperaController.setId(a.get(3).getID());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/VisualizzaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();
        }
    @FXML
    private void imm5(MouseEvent event) throws SQLException, IOException {
        VisualizzaOperaController.setId(a.get(4).getID());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/fxml/VisualizzaOpera.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        Stage stage = new Stage();
        stage.setTitle("Visualizza Opera");
        stage.setScene(scene);
        stage.show();
        }

    @FXML
    private void mEN1(MouseEvent event) throws SQLException, IOException {lab1.setUnderline(true);}
    @FXML
    private void mEX1(MouseEvent event) throws SQLException, IOException {lab1.setUnderline(false);}
    @FXML
    private void mEN2(MouseEvent event) throws SQLException, IOException {lab2.setUnderline(true);}
    @FXML
    private void mEX2(MouseEvent event) throws SQLException, IOException {lab2.setUnderline(false);}
    @FXML
    private void mEN3(MouseEvent event) throws SQLException, IOException {lab3.setUnderline(true);}
    @FXML
    private void mEX3(MouseEvent event) throws SQLException, IOException {lab3.setUnderline(false);}
    @FXML
    private void mEN4(MouseEvent event) throws SQLException, IOException {lab4.setUnderline(true);}
    @FXML
    private void mEX4(MouseEvent event) throws SQLException, IOException {lab4.setUnderline(false);}
    @FXML
    private void mEN5(MouseEvent event) throws SQLException, IOException {lab5.setUnderline(true);}
    @FXML
    private void mEX5(MouseEvent event) throws SQLException, IOException {lab5.setUnderline(false);}

}
