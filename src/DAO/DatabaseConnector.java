package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnector {
	public static Connection connessioneDB() {

        Connection conn = null;
        String port = null;
        String username = null;
        String password = null;
        
        String filePath = new File("").getAbsolutePath();
    	final String FILENAME = filePath + "/config.txt";

    		BufferedReader br = null;
    		FileReader fr = null;

    		try {
    			
    			fr = new FileReader(FILENAME);
    			br = new BufferedReader(fr);

                port=br.readLine();
                username=br.readLine();
                password=br.readLine();
    		} catch (IOException e) {

    			e.printStackTrace();

    		}
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/libreria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", username, password);
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ERROR:" + ex.getErrorCode());
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return conn;
    }

}
