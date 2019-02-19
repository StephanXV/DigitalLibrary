package DAO.implementation;

import DAO.DatabaseConnector;
import DAO.interfaces.Autore;

import java.sql.*;
import java.util.ArrayList;

public class AutoreDAO implements Autore {


//controlla se un autore inserito è gia presente nel db
    public static boolean controllaAutore(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA, int id_opera) throws SQLException {

        int cont = 0;
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "SELECT COUNT(ID) AS AutorePresente FROM autore where (nome = ? and cognome = ? and data_nascita = STR_TO_DATE(?, '%d/%m/%Y') and nazionalita = ?);";
        CallableStatement stmt = connessione.prepareCall(query);

        stmt.setString(1, nomeA);
        stmt.setString(2, cognomeA);
        stmt.setString(3, data_nascitaA);
        stmt.setString(4, nazionalitaA);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            cont = rs.getInt("AutorePresente");
        }

        if (cont == 0) {insAutore(nomeA, cognomeA, data_nascitaA, nazionalitaA);}
        insStesura(nomeA, cognomeA, data_nascitaA, nazionalitaA, id_opera);
        connessione.close();
        return true;

    }

    //inserisce un autore se non è presente
    public static boolean insAutore(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String ins = "insert into autore (nome,cognome,data_nascita,nazionalita) values(?,?,STR_TO_DATE(?, '%d/%m/%Y'),?);";

        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setString(1, nomeA);
        cmd.setString(2, cognomeA);
        cmd.setString(3, data_nascitaA);
        cmd.setString(4, nazionalitaA);

        cmd.executeUpdate();


        cmd.close();
        connessione.close();
        return true;
    }

    //inserisce collegamento autore opera (stesura)
    public static boolean insStesura(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA, int id_opera) throws SQLException {
        int id = 0;

        Connection connessione = DatabaseConnector.connessioneDB();
        String sel = "select ID from autore where (nome = ? and cognome = ? and data_nascita = STR_TO_DATE(?, '%d/%m/%Y') and nazionalita = ? )";
        PreparedStatement cmdd = connessione.prepareStatement(sel, PreparedStatement.RETURN_GENERATED_KEYS);
        cmdd.setString(1, nomeA);
        cmdd.setString(2, cognomeA);
        cmdd.setString(3, data_nascitaA);
        cmdd.setString(4, nazionalitaA);

        ResultSet rss = cmdd.executeQuery();

        while (rss.next()) {
            id = rss.getInt("ID");
        }

        rss.close(); cmdd.close();

        String ins = "insert into stesura (IDautore,IDopera) values(?,?);";



        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setInt(1, id);
        cmd.setInt(2, id_opera);

        cmd.executeUpdate();


        cmd.close();
        connessione.close();
        return true;
    }

    //ci ritorna la lista di autori data un opera
    public static ArrayList<String> getAut(int id) throws SQLException {
        String nome = null;
        ArrayList<String> all = new ArrayList<String>();

        Connection connessione = DatabaseConnector.connessioneDB();

        String ver = "select autore.* from stesura inner join autore on stesura.IDautore = autore.ID   where stesura.IDopera =" + id + ";";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(ver);
        rs1 = st1.executeQuery(ver);

        while (rs1.next() == true) {
            nome = rs1.getString("nome");
            nome = nome + " " + rs1.getString("cognome");
            all.add(nome);
        }

        st1.close();
        rs1.close();
        connessione.close();
        return all;
    }

}
