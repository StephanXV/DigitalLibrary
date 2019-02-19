package DAO.implementation;

import DAO.DatabaseConnector;
import DAO.interfaces.Admin;

import java.sql.*;
import java.util.TreeMap;

public class AdminDAO implements Admin {

    //admin che accetta o meno i permessi richiesti
    public static void accettaPermessi(String email, String permesso, String ris) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        //gli passerà si se accetta, no se non accetta
        String acc = null;
        String proc = null;
        if (ris.equals("si")) {
            acc = "update privilegio set assegnato = 'si' where (email = '" + email + "' and tipo = '" + permesso + "');";
            proc = "{CALL eliminaPrivilegio(?)}";
            CallableStatement stmtr = connessione.prepareCall(proc);
            stmtr.setString(1, email);
            ResultSet rsr = stmtr.executeQuery();
        }
        else if (ris.equals("no")) {acc = "delete from privilegio where (email = '" + email + "' and tipo = '" + permesso + "');";}

        PreparedStatement cmdx = connessione.prepareStatement(acc, PreparedStatement.RETURN_GENERATED_KEYS);

        cmdx.executeUpdate();


        cmdx.close();
        connessione.close();
        if (ris.equals("si")) UtenteDAO.inserisciMessaggio("Il privilegio da lei richiesto è stato accettato! Ora è un: " + permesso + ".",email,"no","inserimento");
        else UtenteDAO.inserisciMessaggio("Il privilegio da lei richiesto NON è stato accettato!",email,"no","inserimento");
    }

    //admin che visualizza i permessi richiesti dagli utenti
    public static TreeMap<String,String> permessiInAttesa() throws SQLException {
        TreeMap<String,String> a = new TreeMap<String,String>();
        String email = null;
        String permesso = null;

        String att = "select * from privilegio where assegnato = 'no';";

        Connection connessione = DatabaseConnector.connessioneDB();

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(att);
        rs1 = st1.executeQuery(att);

        while (rs1.next() == true) {
            email=rs1.getString("email");
            permesso=rs1.getString("tipo");

            a.put(email,permesso);
        }

        st1.close();
        rs1.close();
        connessione.close();
        return a;
    }
}
