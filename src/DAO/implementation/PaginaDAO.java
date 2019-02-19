package DAO.implementation;

import DAO.DatabaseConnector;
import DAO.interfaces.Pagina;
import model.Immagine;
import model.ImmagineTrascrizione;

import java.sql.*;
import java.util.*;

import static controller.OperaController.getNomeOPID;

public class PaginaDAO implements Pagina {

    //convalida immagine (supervisore)
    public static void convalidaImmagine(int imgId, String conv, Immagine i) throws SQLException {
        if (conv.equals("si")) UtenteDAO.inserisciMessaggio("L'immagine da lei acquisita dell'opera: " + getNomeOPID(i.getID_opera()) + ", pagina: " + i.getNumeroPagina() + " è stata convalidata!",i.getUser(), "no", "inserimento" );
        else UtenteDAO.inserisciMessaggio("L'immagine da lei acquisita dell'opera: " + getNomeOPID(i.getID_opera()) + ", pagina: " + i.getNumeroPagina() + " NON è stata convalidata!",i.getUser(), "no", "inserimento" );

        Connection connessione = DatabaseConnector.connessioneDB();
        if (conv.equals("si")) {
        PreparedStatement pstmt = connessione.prepareStatement("UPDATE immagine SET validazione = ? WHERE ID =  ? ");
        pstmt.setInt(1, 1);
        pstmt.setInt(2, imgId);
        pstmt.executeUpdate(); }
        else if (conv.equals("no")) {String sql = "DELETE FROM immagine WHERE ID = ?";
            PreparedStatement preparedStatement = connessione.prepareStatement(sql);
            preparedStatement.setInt(1, imgId);
            preparedStatement.executeUpdate(); }
            connessione.close();
    }


    //inserisci immagine con validazione = 0
    public static void insImmagine(int id_opera, int nPagina, String src, String utente) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "INSERT INTO immagine(numero_pagina, sorgente, IDopera, utente) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connessione.prepareStatement(sql);
        statement.setInt(1, nPagina);
        statement.setString(2, src);
        statement.setInt(3, id_opera);
        statement.setString(4, utente);
        statement.executeUpdate();
        connessione.close();
    }

    //convalida trascrizione (supervisore)
    public static void convalidaTrascrizione(int trId, String conv, int idImm, ImmagineTrascrizione t) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql1 = "SELECT * FROM assegna_trascrizione WHERE IDimmagine=" + t.getIm().getID() +";";
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()) {
            String email = resultSet.getString("usern");
            if (conv.equals("si")) UtenteDAO.inserisciMessaggio("La trascrizione da lei acquisita dell'opera: " + getNomeOPID(t.getIm().getID_opera()) + ", pagina: " + t.getNumeroPagina() + " è stata convalidata!",email, "no", "inserimento" );
            else UtenteDAO.inserisciMessaggio("La trascrizione da lei acquisita dell'opera: " + getNomeOPID(t.getIm().getID_opera()) + ", pagina: " + t.getNumeroPagina() + " NON è stata convalidata!",email, "no", "inserimento" );
        }
        resultSet.close();
        statement.close();

        if (conv.equals("si")){
        PreparedStatement pstmt = connessione.prepareStatement("UPDATE trascrizione SET validazione = ? WHERE ID =  ? ");
        pstmt.setInt(1, 1);
        pstmt.setInt(2, trId);
        pstmt.executeUpdate(); eliminaTrasc(idImm);}
        else if (conv.equals("no")) {String sql = "DELETE FROM trascrizione WHERE ID = ?";
        PreparedStatement preparedStatement = connessione.prepareStatement(sql);
        preparedStatement.setInt(1, trId);
        preparedStatement.executeUpdate(); }

        connessione.close();
    }

    //elimina trascrizione se non convalidata
    public static void eliminaTrasc(int idImm) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "DELETE FROM assegna_trascrizione WHERE IDimmagine = ?";
            PreparedStatement preparedStatement = connessione.prepareStatement(sql);
            preparedStatement.setInt(1, idImm);
            preparedStatement.executeUpdate();
        connessione.close();
    }


    //modifica trascrizione (supervisore)
    public static void modTrascrizione(String text, int trId) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
            PreparedStatement pstmt = connessione.prepareStatement("UPDATE trascrizione SET testo = ? WHERE ID =  ? ");
            pstmt.setString(1, text);
            pstmt.setInt(2, trId);
            pstmt.executeUpdate();
        connessione.close();
    }

    //assegna_trascrizione all'utente
    public static void insAssTrascrizione(String em, int idImm) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "INSERT INTO assegna_trascrizione(usern, IDimmagine) VALUES (?, ?)";
        PreparedStatement statement = connessione.prepareStatement(sql);
        statement.setString(1, em);
        statement.setInt(2, idImm);
        statement.executeUpdate();
        connessione.close();
    }

    //ci torna l'immagine data opera e pagina
    public static int getImmagineOP(int id, int pag) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql = "SELECT * FROM immagine WHERE (IDopera = " + id + " and numero_pagina = " + pag + ");";
        ResultSet resultSet = statement.executeQuery(sql);
        int i = 0;
        while (resultSet.next()) {
            i = resultSet.getInt("ID");
        }
        connessione.close();
        return i;
    }


    //ci torna la lista di tutte le immagini non convalidate
    public static ArrayList<Immagine> getImmaginiNonConvalidate() throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql = "SELECT * FROM immagine WHERE validazione = 0;";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Immagine> pages = new ArrayList<Immagine>();
        while (resultSet.next()) {
            Immagine img = new Immagine(resultSet);
            pages.add(img);
        }
        connessione.close();
        return pages;
    }

    //lista di tutte le trasczioni non convalidate
    public static ArrayList<ImmagineTrascrizione> getTrascrizioniNonConvalidate() throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql = "SELECT * FROM trascrizione WHERE validazione = 0;";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<ImmagineTrascrizione> pages = new ArrayList<ImmagineTrascrizione>();
        while (resultSet.next()) {
            ImmagineTrascrizione tr = new ImmagineTrascrizione(resultSet);
            tr.setIm(getImmaginiByTrascrizione(tr.getID_opera(),tr.getNumeroPagina()));
            pages.add(tr);
        }
        connessione.close();
        return pages; }

    //lista trascrizioni non convalidate di una data opera
    public static ArrayList<ImmagineTrascrizione> getTrascrizioniConv(int idOpera) throws SQLException {
        ArrayList<ImmagineTrascrizione> pages = new ArrayList<ImmagineTrascrizione>();
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement1 = connessione.createStatement();
        String sql1 = "SELECT * FROM opera WHERE ID =  " + idOpera + ";";
        ResultSet resultSet1 = statement1.executeQuery(sql1);
        String tra = null;
        resultSet1.next();
        tra = resultSet1.getString("completaTRS");

        if (tra.equals("si")) {
            Statement statement = connessione.createStatement();
            String sql = "SELECT * FROM trascrizione WHERE (validazione = 1 and IDopera =  " + idOpera + ");";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ImmagineTrascrizione tr = new ImmagineTrascrizione(resultSet);
                tr.setIm(getImmaginiByTrascrizione(tr.getID_opera(), tr.getNumeroPagina()));
                pages.add(tr);
            } }
        else {
            Statement statement = connessione.createStatement();
            String sql = "SELECT * FROM immagine WHERE (validazione = 1 and IDopera =  " + idOpera + ");";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ImmagineTrascrizione tr = new ImmagineTrascrizione();
                int x = resultSet.getInt("numero_pagina");
                tr.setIm(getImmaginiByTrascrizione(idOpera,x));
                tr.setNumeroPagina(x);
                tr.setFonte("Trascrizione non disponibile");
                pages.add(tr);
            } }
        connessione.close();
        Collections.sort(pages,new OrdinaImm());
        return pages;
    }

    //data una trascrizione ci unisce l'immagine corrispondente
    public static Immagine getImmaginiByTrascrizione(int idOP, int npag) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql = "SELECT * FROM immagine WHERE (validazione = 1 and IDopera = " + idOP + " and numero_pagina = " + npag + ");";
        ResultSet resultSet = statement.executeQuery(sql);
        Immagine i = null;
        while (resultSet.next()) {
            i = new Immagine(resultSet);
        }
        connessione.close();
        return i;
    }

    //trascrizioni a me(trascrittore) assegnate
    public static List<Immagine> getToTranscribe(String email){
        try{
            String query ="select numero_pagina, opera.titolo, lingua, sorgente, opera.ID from assegna_trascrizione join utente join immagine join opera where assegna_trascrizione.usern = utente.email and utente.email = '"+email+"' and immagine.ID = assegna_trascrizione.IDimmagine and immagine.IDopera = opera.ID";
            Connection connessione = DatabaseConnector.connessioneDB();
            Immagine i;
            List<Immagine> list= new ArrayList<>();
            PreparedStatement st1;
            ResultSet rs1;
            st1 = connessione.prepareStatement(query);rs1 = st1.executeQuery(query);

            while (rs1.next()==true) {
                i = new Immagine(rs1.getInt("numero_pagina"), rs1.getString("titolo"), rs1.getString("lingua"), rs1.getString("sorgente"), rs1.getInt("ID"));
                list.add(i);
            }
            st1.close();
            rs1.close();
            connessione.close();
            return list;
        } catch (Exception ex){
            ex.getStackTrace();
        }
        return null;
    }

    //inserisce una trascrizione effettuata o eventualmente la modifca se già esistente
    public static boolean insTrascrizione(int numero, String text, int valid, int id) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "select count(ID) as cont from trascrizione where (numero_pagina = " + numero + " and IDopera = " + id + ");";
        PreparedStatement st2; ResultSet rs2;
        st2 = connessione.prepareStatement(sql); rs2 = st2.executeQuery(sql); int c = 0;
        while (rs2.next() == true) {
            c=rs2.getInt("cont");
        }
        if (c == 0){
            String ins = "insert into trascrizione (numero_pagina, testo, validazione, IDopera) values (?,?,?,?)";
            PreparedStatement cmd = connessione.prepareStatement(ins, PreparedStatement.RETURN_GENERATED_KEYS);
            cmd.setInt(1, numero);
            cmd.setString(2, text);
            cmd.setInt(3, valid);
            cmd.setInt(4, id);
            cmd.executeUpdate();
            cmd.close();
            connessione.close();
        }
        else if (c == 1){
            String ins1 = "update trascrizione set testo = ? where numero_pagina = ? and IDopera = ?";
            PreparedStatement cmd = connessione.prepareStatement(ins1, PreparedStatement.RETURN_GENERATED_KEYS);
            cmd.setString(1, text);
            cmd.setInt(2, numero);
            cmd.setInt(3, id);
            cmd.executeUpdate();
            cmd.close();
            connessione.close();
        }
        return true;
    }

    //ci torna la trascrizione data opera e pagina
    public static String getTrans(int numero, int idOp) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement = connessione.createStatement();
        String sql = "SELECT * FROM trascrizione WHERE (IDopera = " + idOp + " and numero_pagina = " + numero + ");";
        ResultSet resultSet = statement.executeQuery(sql);
        String i = null;
        while (resultSet.next()) {
            i = resultSet.getString("testo");
        }
        connessione.close();
        return i;
    }

    //controlla se una opagina esiste già
    public static boolean esisteGiaPagina(Connection connessione, String codiceOpera, int pagina) throws SQLException{
        Statement statement = connessione.createStatement();
        String sql = "select * from immagine join opera where immagine.IDopera = opera.ID and opera.codice = '"+codiceOpera+"' and numero_pagina = "+pagina ;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            return false;
        }
        return true;
    }

    //ordina la lista di opera per ordine di inserimento nel sistema. dalla piu recente
    static class OrdinaImm implements Comparator<ImmagineTrascrizione> {
        public int compare(ImmagineTrascrizione o1, ImmagineTrascrizione o2) {
            if (o1.getNumeroPagina() > o2.getNumeroPagina()) return 1;
            if (o1.getNumeroPagina() < o2.getNumeroPagina()) return -1;
            else return 0;
        }
    }


}
