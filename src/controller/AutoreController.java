package controller;

import java.sql.SQLException;
import java.util.List;

import DAO.implementation.AutoreDAO;

public class AutoreController {
	
	public static List<String> autoriById (int id) throws SQLException {
		return AutoreDAO.getAut(id);
	}

}
