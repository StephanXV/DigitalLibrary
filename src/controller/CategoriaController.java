package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.implementation.CategoriaDAO;

public class CategoriaController {

    public static List<String> cateOpera (String codice) throws SQLException {
    	return CategoriaDAO.getCategorie(codice);
    }
	
}
