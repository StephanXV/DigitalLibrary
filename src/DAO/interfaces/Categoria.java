package DAO.interfaces;

import java.util.ArrayList;

public interface Categoria {
	
	//lista di tutte le categorie
    public static ArrayList<Categoria> tutteCategorie() {
		return null;
    }

    //categorie di un opera
    public static ArrayList<String> getCategorie(String codice) {
        return null;
    }

    public static boolean insCategorizzazione(String cat, String codice) {
        return false;
    }

}
