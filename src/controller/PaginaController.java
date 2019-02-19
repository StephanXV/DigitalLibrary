package controller;

import model.Immagine;
import model.ImmagineTrascrizione;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.implementation.PaginaDAO;

import static DAO.implementation.OperaDAO.getPagineOpera;
import static DAO.implementation.PaginaDAO.*;

public class PaginaController {

    private static Immagine i = null;

    public static int getPagOp(String code) throws SQLException {
        return getPagineOpera(code);
    }

    public static boolean esiste(Connection connessione, String codiceOpera, int pagina) throws SQLException {
        return esisteGiaPagina(connessione, codiceOpera, pagina);
    }

    public static void inserisci(int id_opera, int nPagina, String src, String utente) throws SQLException {
        insImmagine(id_opera, nPagina, src, utente);
    }

    public static List<Immagine> pagineDaTrascrivere(String email){
        return getToTranscribe(email);
    }

    public static void setImmagine(Immagine imm){
        i = imm;
    }

    public static Immagine getImmagine() {
        return i;
    }

    public static ArrayList<Immagine> getImmNonConvalidate() throws SQLException {
        return getImmaginiNonConvalidate();
    }

    public static ArrayList<ImmagineTrascrizione> getTrascNonConvalidate() throws SQLException {
        return getTrascrizioniNonConvalidate();
    }

    public static void convalidaImm(int imgId, String conv, Immagine i) throws SQLException {
        convalidaImmagine(imgId,conv, i);
    }

    public static void convalidaTras(int trId, String conv, int idImm, ImmagineTrascrizione t) throws SQLException {
        convalidaTrascrizione(trId,conv,idImm, t);
    }

    public static String getTranscription(int numero, int idOp) throws SQLException {
        return getTrans(numero,idOp);
    }

    public static void inserisciTrascrizione(int numero, String text, int valid, int id) throws SQLException {
        insTrascrizione(numero,text,valid,id);
    }

    public static void assegnaTrascrizione(String em, int idImm) throws SQLException {
        insAssTrascrizione(em,idImm);
    }

    public static int getImmagineByOp(int id, int pag) throws SQLException {
        return getImmagineOP(id,pag);
    }

    public static void modificaTrascrizione(String text, int trId) throws SQLException {
        modTrascrizione(text,trId);
    }
    
    public static ArrayList<ImmagineTrascrizione> getTrascrizioni(int id) throws SQLException {
    	return PaginaDAO.getTrascrizioniConv(id);
    }


}
