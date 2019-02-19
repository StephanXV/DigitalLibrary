package DAO.interfaces;

import java.util.TreeMap;

public interface Admin {

	    //admin che accetta o meno i permessi richiesti
	    public static void accettaPermessi(String email, String permesso, String ris) {
;
	    }

	    //admin che visualizza i permessi richiesti dagli utenti
	    public static TreeMap<String,String> permessiInAttesa() {
			return null;
        }


}
