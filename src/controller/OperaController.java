package controller;

import model.Autore;
import model.Opera;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.implementation.OperaDAO.*;

public class OperaController {

    private static ArrayList<Opera> opere = new ArrayList<Opera>();

    public static ArrayList<Opera> cercaOpere (String ricerca) throws SQLException {
        opere = operaSearch(ricerca);
        return opere;
    }

    public static ArrayList<String> getOPImmNonConvalidate() throws SQLException {
        return getOperaImmaginiNonValidate();
    }

    public static String getNomeOP(String code) throws SQLException {
        return getNomeopera_byCode(code);
    }

    public static String getNomeOPID(int id) throws SQLException {
        return getNomeopera_byID(id);
    }

    public static int getIDOP(String code) throws SQLException {
        return getIDopera_byCode(code);
    }

    public static ArrayList<String> opereIncompleteIMG() throws SQLException {
        return getOperaInc();
    }

    public static ArrayList<String> opereIncompleteTRS() throws SQLException {
        return getOperaInc1();
    }

    public static void pubblica(String code) throws SQLException {
        pubblicaOP(code);
    }

    public static void inserisciOp(List<String> nomecat, List<Autore> autori, String codice, String titolo, String descrizione, int anno, String lingua, int pagine) throws SQLException {
        inserisciOpera(nomecat,autori,codice,titolo,descrizione,anno,lingua,pagine);
    }

    public static ArrayList<String> getOperaTrascriNonValidate() throws SQLException {
        return getOperaTrascrizioniNonValidate();
    }

    public static ArrayList<Integer> getTrascrizioniNonValidateOpera(int id) throws SQLException {
        return getTrascrizioniNonValidate(id);
    }
    
    public static Opera tornaOpera (int id) throws SQLException {
    	return getOp(id);
    }
    
    public static String getCopertina (int id) throws SQLException {
    	return getImmOP(id);
    }

    public static ArrayList<Opera> filtri (String categoria) throws SQLException {
        return getOperaFiltri(categoria);
    }

}
