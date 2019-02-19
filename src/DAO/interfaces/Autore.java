package DAO.interfaces;

import java.util.ArrayList;

public interface Autore {

	public static boolean controllaAutore(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA, int id_opera) {
		return false;
    }

    public static boolean insAutore(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA) {
        return false;
    }

    public static boolean insStesura(String nomeA, String cognomeA, String data_nascitaA, String nazionalitaA, int id_opera) {
        return false;
    }

    //categorie di un opera
    public static ArrayList<String> getAut(int id) {
		return null;
    }
}

