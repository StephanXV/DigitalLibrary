package DAO.implementation;

import DAO.DatabaseConnector;
import model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    //lista di tutte le categorie
    public static ArrayList<Categoria> tutteCategorie() throws SQLException {
        int id = 0;
        String nome = null;
        ArrayList<Categoria> all = new ArrayList<Categoria>();

        Connection connessione = DatabaseConnector.connessioneDB();

        String ver = "select * from categoria;";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(ver);
        rs1 = st1.executeQuery(ver);

        while (rs1.next() == true) {
            id = rs1.getInt("ID");
            nome = rs1.getString("nome");
            Categoria a = null; 
            a.setNome(nome); 
            a.setId(id);
            all.add(a);
        }

        st1.close();
        rs1.close();
        connessione.close();
        return all;
    }

    //categorie di un opera
    public static ArrayList<String> getCategorie(String codice) throws SQLException {
        String nome = null;
        ArrayList<String> all = new ArrayList<String>();

        Connection connessione = DatabaseConnector.connessioneDB();

        String ver = "select * from categorizzazione where codiceOpera ='" + codice + "';";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(ver);
        rs1 = st1.executeQuery(ver);

        while (rs1.next() == true) {
            nome = rs1.getString("nomeCategoria");
            all.add(nome);
        }

        st1.close();
        rs1.close();
        connessione.close();
        return all;
    }


    //inserisce una categorizzazione in un opera
    public static boolean insCategorizzazione(String cat, String codice) throws SQLException {

        String ins = "insert into categorizzazione (codiceOpera,nomeCategoria) values(?,?);";

        Connection connessione = DatabaseConnector.connessioneDB();

        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setString(1, codice);
        cmd.setString(2, cat);

        cmd.executeUpdate();


        cmd.close();
        connessione.close();
        return true;
    }


}
