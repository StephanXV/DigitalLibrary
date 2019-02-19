package DAO.interfaces;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.Immagine;
import model.ImmagineTrascrizione;

public interface Pagina {
	
	//convalida immagine (supervisore)
    public static void convalidaImmagine(int imgId, String conv) {}


    //inserisci immagine con validazione = 0
    public static void insImmagine(int id_opera, int nPagina, String src) {}

    //convalida trascrizione (supervisore)
    public static void convalidaTrascrizione(int trId, String conv, int idImm) {}

    public static void eliminaTrasc(int idImm) {}


    //modifica trascrizione (supervisore)
    public static void modTrascrizione(String text, int trId) {}

    //inserisci assegna_trascrizione
    public static void insAssTrascrizione(String em, int idImm) {}

    //ci torna l'immagine data opera e pagina
    public static int getImmagineOP(int id, int pag) {
		return 0;
    }


    //ci torna la lista delle immagini non convalidate
    public static ArrayList<Immagine> getImmaginiNonConvalidate() {
        return null;
    }

    //lista trasc non convalidate
    public static ArrayList<ImmagineTrascrizione> getTrascrizioniNonConvalidate() {
        return null; 
    }

    //lista trasc non convalidate
    public static ArrayList<ImmagineTrascrizione> getTrascrizioniConv(int idOpera) {
        return null;
    }

    //data una trascrizione ci unisce l'immagine corrispondente
    public static Immagine getImmaginiByTrascrizione(int idOP, int npag) {
        return null;
    }

    public static List<Immagine> getToTranscribe(String email){
        return null;
    }

    public static boolean insTrascrizione(int numero, String text, int valid, int id) {
        return false;
    }

    //ci torna l'immagine data opera e pagina
    public static String getTrans(int numero, int idOp) {
        return null;
    }


    public static boolean esisteGiaPagina(Connection connessione, String codiceOpera, int pagina) {
        return false;
    }


}
