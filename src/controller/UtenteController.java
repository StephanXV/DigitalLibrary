package controller;

import model.UtenteBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import static DAO.implementation.AdminDAO.accettaPermessi;
import static DAO.implementation.AdminDAO.permessiInAttesa;
import static DAO.implementation.UtenteDAO.*;

public class UtenteController {

    private static UtenteBase utente = null;

   public static UtenteBase getUtenteLogin (String username, String password) throws SQLException {
        utente = getUser(username,password);
        return utente;
    }

    public static void setUtente(UtenteBase u){
        utente = u;
    }
    public static UtenteBase getUtente(){
        return utente;
    }

    public static ArrayList<String> getDomanda (String email) throws SQLException {
       return getDomandaSicurezza(email);
    }
    public static void updateProf(String email, String campo, String newCampo) throws SQLException {
       updateProfilo(email,campo,newCampo);
    }

    public static void registrazioneUtente(String nome, String cognome, String data_nascita, String email, String pass, String sesso, String domanda, String risposta) throws SQLException {
       registrazione(nome,cognome,data_nascita,email,pass,sesso,domanda,risposta);
    }

    public static boolean verificaEmail(String email) throws SQLException {
       return confrontaEmail(email);
    }

    public static void permessi(String email, String permesso) throws SQLException {
       richiestaPermessi(email,permesso);
    }

    public static ArrayList<String> listaTrasc(String e) throws SQLException {
       return listaTrascrittori(e);
    }

    public static TreeMap<String,String> permessiAttesa() throws SQLException {
       return permessiInAttesa();
    }

    public static void convalidaPermessi(String email, String permesso, String ris) throws SQLException {
       accettaPermessi(email,permesso,ris);
    }

    public static ArrayList<String> messaggi(String email, String letti) throws SQLException {
       if (letti.equals("no")) return messaggiNonLetti(email);
       else return messaggiLetti(email);
    }

    public static void messLetti(String testo, String utente, String letto, String up) throws SQLException {
       inserisciMessaggio(testo,utente,letto,up);
    }

    public static void eliminaMess(String testo, String utente) throws SQLException {
       eliminaMessaggio(testo,utente);
    }
}
