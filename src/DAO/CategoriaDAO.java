package DAO;

import model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    /**
     * Prende le categorie di un'opera
     *
     * @param codice il codice dell'opera specificata
     * @return l'elenco delle categorie
     */
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

    /**
     * Permette l'assegnamento di una categoria ad un'opera
     *
     * @param cat la categoria scelta
     * @param codice il codice dell'opera
     *
     * @return successo/fallimento
     *
     */
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
