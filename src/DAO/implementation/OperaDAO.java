package DAO.implementation;

import DAO.DatabaseConnector;
import model.Autore;
import model.Categoria;
import model.Opera;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperaDAO implements DAO.interfaces.Opera {
    //inserimento nuova opera
    public static boolean inserisciOpera(List<String> nomecat, List<Autore> autori, String codice, String titolo, String descrizione, int anno, String lingua, int pagine) throws SQLException {
        int id_opera = 0;
        Connection connessione = DatabaseConnector.connessioneDB();
        String ins = "insert into opera (codice,titolo,descrizione,anno,lingua,pagine) values(?,?,?,?,?,?);";

        PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
        cmd.setString(1, codice);
        cmd.setString(2, titolo);
        cmd.setString(3, descrizione);
        cmd.setInt(4, anno);
        cmd.setString(5, lingua);
        cmd.setInt(6, pagine);

        cmd.executeUpdate();
        cmd.close();

        id_opera = getIDopera_byCode(codice);

        for (Autore y: autori) { AutoreDAO.controllaAutore(y.getNome(), y.getCognome(), y.getData_nascita(), y.getNazionalita(), id_opera); }
        for (String x: nomecat) { CategoriaDAO.insCategorizzazione(x,codice); }
        return true;
    }

    //dato un codice ci ritorna l'id opera
    public static int getIDopera_byCode(String codice) throws SQLException {
        int id_opera = 0;
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "select ID from opera where codice= '" + codice +"';";

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next() == true) {
            id_opera=rs1.getInt("ID");
        }
        st1.close();
        rs1.close();
        connessione.close();
        return id_opera;
    }


    //dato un id ritorna l'opera
    public static Opera getOp(int id) throws SQLException {
        Opera a = new Opera();
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "select * from opera where id= " + id +";";

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next() == true) {
            a = new Opera(rs1);
        }
        st1.close();
        rs1.close();
        connessione.close();
        return a;
    }

    //ricerca opera per titolo/autore
    public static ArrayList<Opera> operaSearch(String ricerca) throws SQLException {
        ArrayList<Opera> listOpere = new ArrayList<Opera>();

        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "{CALL ricerca_opera(?)}";
        CallableStatement stmt = connessione.prepareCall(query);
        stmt.setString(1, ricerca);
        ResultSet rs = stmt.executeQuery();
        listOpere = getOpera(connessione, rs);
        connessione.close();

        return listOpere;
    }


    //dato un resultset crea e ritorna la lista delle opere con autori e categorie associate
    public static ArrayList<Opera> getOpera(Connection connessione, ResultSet rs) throws SQLException {
        ArrayList<Opera> listOpere = new ArrayList<Opera>();
        ArrayList<Categoria> listCategoria = new ArrayList<Categoria>();
        Opera op = null;
        //Connection connessione = DatabaseConnector.connessioneDB();
        while (rs.next()) {
        	ArrayList<Autore> listAutore = new ArrayList<Autore>();
            op = new Opera(rs); //creo opera prendendola dal db
            String query1 = "{CALL autore_opera(?)}"; //procedura che ritorna gli autori di un opera dato l'idOpera
            String query2 = "{CALL categoria_opera(?)}"; //procedura che ritorna le categorie di un opera dato il codice
            CallableStatement stmtt = connessione.prepareCall(query1);
            CallableStatement stmtx = connessione.prepareCall(query2);
            stmtt.setInt(1, op.getID());
            stmtx.setString(1, op.getCodice());
            ResultSet rst = stmtt.executeQuery();
            while (rst.next()) {  //creo gli autori dell opera e li assegno ad una lista di autori
                Autore a = new Autore(rst);
                listAutore.add(a);
            }
            ResultSet rsx = stmtx.executeQuery();
            while (rsx.next()) { //creo le categorie dell opera e le assegno ad una lista di categorie
                int idC = rsx.getInt("ID");
                String nomeC= rsx.getString("nome");
                Categoria cat = new Categoria(); cat.setNome(nomeC); cat.setId(idC);
                listCategoria.add(cat);
            }
            //inserisco nell'opera creata la lista di autori e di categorie
            op.setCategoria(listCategoria);
            op.setAutore(listAutore);
            listOpere.add(op);//creo la lista completa di opere
        }
        return listOpere; }

    //ritorna le opere con tutte le immagini convalidate non acnora pubblicate
    public static ArrayList<String> getOperaInc () throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT codice FROM opera WHERE completaIMG = 'si' and pubblicazione = 'no'";
        ArrayList<String> operaInc = new ArrayList<String>();
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);
        while (rs1.next() == true) {
            operaInc.add(rs1.getString("codice"));
        }
        st1.close();
        rs1.close();
        connessione.close();
        return operaInc;
    }

    //ritorna le opere con tutte le trascrizioni convalidate non acnora pubblicate
    public static ArrayList<String> getOperaInc1() throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT codice FROM opera WHERE completaTRS = 'si' and pubblicazione = 'no'";
        ArrayList<String> operaInc = new ArrayList<String>();
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);
        while (rs1.next() == true) {
            operaInc.add(rs1.getString("codice"));
        }
        st1.close();
        rs1.close();
        connessione.close();
        return operaInc;
    }

    //pubblicazione opera
    public static boolean pubblicaOP(String cod) throws SQLException {
        String up = "Update opera Set pubblicazione = 'si' where codice = '" + cod + "';";
        Connection connessione = DatabaseConnector.connessioneDB();
        PreparedStatement cmd = connessione.prepareStatement(up);
        cmd.executeUpdate();
        cmd.close();
        connessione.close();
        return true;
    }

    //dato un codice ci ritorna il nome dell'opera
    public static String getNomeopera_byCode(String codice) throws SQLException {
        String nom = null;
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "select titolo from opera where codice= '" + codice +"';";

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next() == true) {
            nom =rs1.getString("titolo");
        }
        st1.close();
        rs1.close();
        connessione.close();
        return nom;
    }

    //dato un id ci ritorna il tiotolo opera
    public static String getNomeopera_byID(int id) throws SQLException {
        String nom = null;
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "select titolo from opera where ID= " + id +";";

        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next() == true) {
            nom =rs1.getString("titolo");
        }
        st1.close();
        rs1.close();
        connessione.close();
        return nom;
    }


    //ritorna tutte le opere che hannno immagini non validate (incomplete) per gli uploader
    public static ArrayList<String> getOperaImmaginiNonValidate() throws SQLException {
        ArrayList<String> t = new ArrayList<String>();
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT * FROM opera where completaIMG = 'no'";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);

        while (rs1.next() == true) {
            String cod = rs1.getString("codice");
            t.add(cod);
        }
        st1.close();
        rs1.close();
        return t;
    }

    //ritorna tutte le opere che hannno trascrizioni non validate (incomplete) per i trascriber
    public static ArrayList<String> getOperaTrascrizioniNonValidate() throws SQLException {
        ArrayList<String> t = new ArrayList<String>();
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT codice FROM opera where completaTRS = 'no'";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);
        while (rs1.next() == true) {
            String cod = rs1.getString("codice");
            t.add(cod);

        }
        st1.close();
        rs1.close();
        return t;
    }

    //ritorna le pagine dell opera scelta che non hanno immagini validate e trascrizioni validate
    public static ArrayList<Integer> getTrascrizioniNonValidate(int id) throws SQLException {
        ArrayList<Integer> pagi = new ArrayList<Integer>();
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT * FROM immagine WHERE (validazione = 1 and IDopera = " + id + ");";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);

        while (rs1.next() == true) {
            int pag = rs1.getInt("numero_pagina");
            String sql1 = "select tras(" + id + "," + pag + ") as N;";
            PreparedStatement st2;
            ResultSet rs2;
            st2 = connessione.prepareStatement(sql1);
            rs2 = st2.executeQuery(sql1);
            while (rs2.next() == true) {if (rs2.getString("N").equals("false")) pagi.add(pag) ;}
            st2.close();
            rs2.close();
        }
        st1.close();
        rs1.close();
        connessione.close();
        return pagi;
    }

    //data un opera ci ritorna la immagini validate
    public static String getImmOP(int id) throws SQLException {
        String src = null;
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "SELECT sorgente FROM immagine where (numero_pagina = 1 and IDopera = " + id +");";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(sql);
        rs1 = st1.executeQuery(sql);
        while (rs1.next() == true) {
            src = rs1.getString("sorgente");
        }
        st1.close();
        rs1.close();
        return src;
    }

    //dato il codice di un'opera, ritorna il numero di pagine
    public static int getPagineOpera(String codice) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String query = "select pagine from opera where codice= '" + codice +"';";
        int i = 0;
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        while (rs1.next() == true) {
            i = rs1.getInt("pagine");
        }
        st1.close();
        rs1.close();
        connessione.close();
        return i;
    }


    //dato categoria ritorna opere
    public static ArrayList<Opera> getOperaFiltri(String categoria) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        ArrayList<Opera> op = new ArrayList<Opera>();
        String query = "select opera.* from opera join categorizzazione where categorizzazione.nomeCategoria = '" + categoria + "' and opera.codice = categorizzazione.codiceOpera and opera.pubblicazione='si' ";
        PreparedStatement st1;
        ResultSet rs1;
        st1 = connessione.prepareStatement(query);
        rs1 = st1.executeQuery(query);

        op = getOpera(connessione, rs1);
        st1.close();
        rs1.close();
        connessione.close();
        return op;
    }

    public  static void main (String[] args) throws SQLException {
        System.out.println(getOperaFiltri("Mitologia"));
    }


}
