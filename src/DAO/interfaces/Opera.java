package DAO.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public interface Opera {
	
	 //inserimento nuova opera
    public static boolean inserisciOpera(List<String> nomecat, List<Autore> autori, String codice, String titolo, String descrizione, int anno, String lingua, int pagine) {
        return false;
    }

    //dato un codice ci ritorna l'id opera
    public static int getIDopera_byCode(String codice) {
        return 0 ;
    }


    //dato un id ritorna l'opera
    public static Opera getOp(int id) {
        return null;
    }

    //ricerca opera per titolo/autore
    public static ArrayList<Opera> operaSearch(String ricerca) {
    	return null;
    }


    //dato un resultset crea e ritorna la lista delle opere con autori e categorie associate
    public static ArrayList<Opera> getOpera(Connection connessione, ResultSet rs) {
        return null; 
    }

    //ritorna le opere con tutte le immagini convalidate non acnora pubblicate
    public static ArrayList<String> getOperaInc () {
        return null;
    }

    //ritorna le opere con tutte le trascrizioni convalidate non acnora pubblicate
    public static ArrayList<String> getOperaInc1() {
        return null;
    }

    //pubblicazione opera
    public static boolean pubblicaOP(String cod) {
        return false;
    }

    //dato un codice ci ritorna il nome dell'opera
    public static String getNomeopera_byCode(String codice) {
        return null;
    }

    //dato un id ci ritorna il tiotolo opera
    public static String getNomeopera_byID(int id) {
        return null;
    }


    //ritorna tutte le opere che hannno immagini non validate (incomplete) per gli uploader
    public static ArrayList<String> getOperaImmaginiNonValidate() {
        return null;
    }

    //ritorna tutte le opere che hannno trascrizioni non validate (incomplete) per i trascriber
    public static ArrayList<String> getOperaTrascrizioniNonValidate() {
        return null;
    }

    //ritorna le pagine dell opera scelta che non hanno immagini validate e trascrizioni validate
    public static ArrayList<Integer> getTrascrizioniNonValidate(int id) {
        return null;
    }

    public static String getImmOP(int id) {
        return null;
    }

    //dato il codice di un'opera, ritorna il numero di pagine
    public static int getPagineOpera(String codice) {
        return 0;
    }

}
