package DAO.interfaces;

import java.util.ArrayList;

import model.UtenteBase;

public interface Utente {
	
	 // verifica che username e password inseriti siano nel database e restituisce il tipo di utente
    public static UtenteBase getUser(String email, String password) {
            return null;
    }

    //data l'email ci ritorna la domanda di sicurezza e la risposta
    public static ArrayList<String> getDomandaSicurezza(String email) {
            return null;
    }

    //registrazione di un nuovo utente
    public static boolean registrazione(String nome, String cognome, String data_nascita, String email, String pass, String sesso, String domanda, String risposta) {
        return false;
    }



    //modifica campi utente
    public static boolean updateProfilo(String email, String campo, String newCampo) {
        return false;
    }


    //modulo richiesta permessi
    public static void richiestaPermessi(String email, String permesso) {}

    //esperienza trascrittori
    public static ArrayList<String> listaTrascrittori(String e) {
		return null;
    }

    public static boolean confrontaEmail(String email) {
        return false;
    }

}
