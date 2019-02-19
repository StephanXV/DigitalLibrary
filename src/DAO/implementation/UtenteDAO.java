package DAO.implementation;

import DAO.DatabaseConnector;
import DAO.interfaces.Utente;
import model.Trascrittore;
import model.UtenteBase;
import model.UtentePrivilegiato;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO implements Utente {

    // verifica che username e password inseriti siano nel database e restituisce il tipo di utente
    public static UtenteBase getUser(String email, String password) throws SQLException {
        ArrayList<String> tipo = new ArrayList<>();
        int id=0;
        String nome=null;
        String cognome=null;
        String data_nascita=null;
        String data_iscrizione=null;
        String emailu=null;
        String pass=null;
        String sesso=null;
        String esp=null;
        Trascrittore.Esperienza exp = null;
        UtenteBase u = null;

            Connection connessione = DatabaseConnector.connessioneDB();
            String query = "{CALL verifica_utente(?,?)}";
            CallableStatement stmt = connessione.prepareCall(query);

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tipo.add(rs.getString("tipo"));
            }

            String ut = "select *,DATE_FORMAT(data_nascita,'%d/%m/%Y') as dataNasc, DATE_FORMAT(data_iscrizione,'%d/%m/%Y') as dataIscr from utente where email = '" + email + "';";
            PreparedStatement stx;
            ResultSet rsx;
            stx = connessione.prepareStatement(ut);
            rsx = stx.executeQuery(ut);

        while (rsx.next() == true) {
            id = rsx.getInt("ID");
            nome = rsx.getString("nome");
            cognome = rsx.getString("cognome");
            data_nascita = rsx.getString("dataNasc");
            data_iscrizione = rsx.getString("dataIscr");
            emailu = rsx.getString("email");
            pass = rsx.getString("pass");
            sesso = rsx.getString("sesso");
            esp = rsx.getString("esperienza");
            exp = Trascrittore.Esperienza.valueOf(esp);
        }

            if (tipo.contains("base")) { u = new UtenteBase(id,nome,cognome,data_nascita,data_iscrizione,emailu,pass,sesso); }
            else if (tipo.contains("trascrittore")) { u = new Trascrittore(id, nome, cognome, data_nascita, data_iscrizione, email, pass, sesso, tipo, exp); }
            else {u = new UtentePrivilegiato(id, nome, cognome, data_nascita, data_iscrizione, email, pass, sesso, tipo);}

            stmt.close();
            rs.close();
            connessione.close();

            return u;
    }

    //data l'email ci ritorna la domanda di sicurezza e la risposta
    public static ArrayList<String> getDomandaSicurezza(String email) throws SQLException {
        ArrayList<String> domanda = new ArrayList<String>();

            Connection connessione = DatabaseConnector.connessioneDB();
            String query = "select * from utente where email= '" + email + "';";

            PreparedStatement st1;
            ResultSet rs1;
            st1 = connessione.prepareStatement(query);
            rs1 = st1.executeQuery(query);

            while (rs1.next() == true) {
                domanda.add(rs1.getString("domanda_sicurezza"));
                domanda.add(rs1.getString("risposta_sicurezza"));
            }

            st1.close();
            rs1.close();
            connessione.close();
            return domanda;
    }

    //registrazione di un nuovo utente
    public static boolean registrazione(String nome, String cognome, String data_nascita, String email, String pass, String sesso, String domanda, String risposta) throws SQLException {
        UtenteBase u = new UtenteBase(nome, cognome, data_nascita, email, pass, sesso);
        Connection connessione = DatabaseConnector.connessioneDB();

        String ins = "insert utente (nome,cognome,data_nascita,email,pass,sesso,domanda_sicurezza,risposta_sicurezza) values(?,?,?,?,?,?,?,?);";
        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setString(1, u.getNome());
        cmd.setString(2, u.getCognome());
        cmd.setString(3, u.getData_nascita());
        cmd.setString(4, u.getEmail());
        cmd.setString(5, u.getPass());
        cmd.setString(6, u.getSesso());
        cmd.setString(7, domanda);
        cmd.setString(8, risposta);
        cmd.executeUpdate();
        cmd.close();

        String priv = "insert into privilegio (email,tipo,assegnato) values (?,'base','si');";
        PreparedStatement cmdx = connessione.prepareStatement(priv, PreparedStatement.RETURN_GENERATED_KEYS);
        cmdx.setString(1, u.getEmail());
        cmdx.executeUpdate();
        cmdx.close();

        connessione.close();
        return true;
    }



    //modifica campi utente
    public static boolean updateProfilo(String email, String campo, String newCampo) throws SQLException {
        String up = null;

        if (campo.equals("data_nascita")) {up = "Update utente Set " + campo + " = STR_TO_DATE('" + newCampo + "', '%d/%m/%Y')  where email = '" + email + "';";}

        else {up = "Update utente Set " + campo + " = '" + newCampo + "' where email = '" + email + "';";}

        Connection connessione = DatabaseConnector.connessioneDB();

        PreparedStatement cmd = connessione.prepareStatement(up);

        cmd.executeUpdate();
        cmd.close();

        connessione.close();

        return true;
    }


    //modulo richiesta permessi
    public static void richiestaPermessi(String email, String permesso) throws SQLException {
        String perm = "insert into privilegio (email,tipo) values (?,?);";
        Connection connessione = DatabaseConnector.connessioneDB();
        PreparedStatement cmdx = connessione.prepareStatement(perm, PreparedStatement.RETURN_GENERATED_KEYS);
        cmdx.setString(1, email);
        cmdx.setString(2, permesso);
        cmdx.executeUpdate();
        cmdx.close();
        connessione.close();
    }

    //esperienza trascrittori
    public static ArrayList<String> listaTrascrittori(String e) throws SQLException {
        ArrayList<String> a = new ArrayList<String>();
        String email = null;

        if (e.equals("")) {
            String query = "SELECT DISTINCT utente.* FROM utente INNER JOIN privilegio on utente.email = privilegio.email WHERE privilegio.tipo = 'trascrittore' and privilegio.assegnato = 'si'";
            Connection connessione = DatabaseConnector.connessioneDB();
            PreparedStatement st2;
            ResultSet rs2;
            st2 = connessione.prepareStatement(query);
            rs2 = st2.executeQuery(query);
            while (rs2.next() == true) {
                email=rs2.getString("email");
                a.add(email);
            }
            st2.close();
            rs2.close();
            connessione.close();
            return a;
        }
        else {
            String query = "SELECT * FROM utente WHERE email = '" + e + "';";
            Connection connessione = DatabaseConnector.connessioneDB();
            PreparedStatement st1;
            ResultSet rs1;
            st1 = connessione.prepareStatement(query);
            rs1 = st1.executeQuery(query);
            while (rs1.next() == true) {
                email=rs1.getString("esperienza");
                a.add(email);
            }
            st1.close();
            rs1.close();
            connessione.close();
            return a;
         }
    }

    //vede se l'email è nel db
    public static boolean confrontaEmail(String email) throws SQLException {
        if (email.equals(""))
            return false;
        String query = "select email from utente where utente.email = '"+email+"';";
        String result="";
        Connection connessione = DatabaseConnector.connessioneDB();

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next()==true)
            result = rs1.getString("email");
        st1.close();
        rs1.close();
        connessione.close();

        if (result.equals(email))
            return true;
        else
            return false;
    }

    //ritorna i messaggi letti
    public static ArrayList<String> messaggiLetti(String email) throws SQLException {
        String query = "select testo from messaggio where (email = '"+email+"' and letto = 'si');";
        ArrayList<String> result= new ArrayList<String>();
        Connection connessione = DatabaseConnector.connessioneDB();
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);
        while (rs1.next()==true){
            result.add(rs1.getString("testo"));}
        st1.close();
        rs1.close();
        connessione.close();

        return result;
    }

    //ritorna i messaggi non letti
    public static ArrayList<String> messaggiNonLetti(String email) throws SQLException {
        String query = "select testo from messaggio where (email = '"+email+"' and letto = 'no');";
        ArrayList<String> result= new ArrayList<String>();
        Connection connessione = DatabaseConnector.connessioneDB();
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);
        while (rs1.next()==true){
            result.add(rs1.getString("testo"));}
        st1.close();
        rs1.close();
        connessione.close();

        return result;
    }

    //inserisci messaggio
    public static void inserisciMessaggio(String testo, String utente, String letto, String up) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();

        String ins;
        if (up.equals("update")){
            ins = "update messaggio set letto ='si' where (email= ? and testo = ?);";
            PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
            cmd.setString(1, utente);
            cmd.setString(2, testo);
            cmd.executeUpdate();
            cmd.close(); }
        else {
            ins = "insert messaggio (email,testo,letto) values(?,?,?);";
        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setString(1, utente);
        cmd.setString(2, testo);
        cmd.setString(3, letto);
        cmd.executeUpdate();
        cmd.close(); }

        connessione.close();
    }

    //elimina messaggio
    public static void eliminaMessaggio(String testo, String utente) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String ins = "DELETE FROM messaggio WHERE (email= ? and testo = ?);";
            PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
            cmd.setString(1, utente);
            cmd.setString(2, testo);
            cmd.executeUpdate();
            cmd.close();
    }

    public  static void main (String[] args) throws SQLException {
        System.out.println(messaggiNonLetti("step@ok.it"));
    }
}
