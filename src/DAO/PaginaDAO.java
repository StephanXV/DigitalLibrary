package DAO;

import model.Immagine;
import model.ImmagineTrascrizione;

import java.sql.*;
import java.util.*;

public class PaginaDAO {

    /**
     * Permette al supervisore di convalidare o meno una immagine in attesa di verifica
     *
     * @param imgId l'id dell'immagine selezionata
     * @param conv si/no
     *
     */
    public static void convalidaImmagine(int imgId, String conv) throws SQLException {
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
            connessione.close(); }


    /**
     * Inserisce una immagine acquisita nel database, e sarà in attesa di validazione
     *
     */
    public static void insImmagine(int id_opera, int nPagina, String src) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "INSERT INTO immagine(numero_pagina, sorgente, IDopera) VALUES (?, ?, ?)";
        PreparedStatement statement = connessione.prepareStatement(sql);
        statement.setInt(1, nPagina);
        statement.setString(2, src);
        statement.setInt(3, id_opera);
        statement.executeUpdate();
        connessione.close();
    }

    /**
     *  Permette al supervisore di convalidare o meno una trascrizione in attesa di verifica
     *
     * @param trId l'id della trascrizione selezionata
     * @param conv si/no
     *
     */
    public static void convalidaTrascrizione(int trId, String conv, int idImm) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
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

    /**
     * Elimina una trascrizione in attesa di verifica se rifiutata
     *
     * @param idImm l'id dell'immagine associata alla trascrizione selezionata
     *
     */
    public static void eliminaTrasc(int idImm) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "DELETE FROM assegna_trascrizione WHERE IDimmagine = ?";
            PreparedStatement preparedStatement = connessione.prepareStatement(sql);
            preparedStatement.setInt(1, idImm);
            preparedStatement.executeUpdate();
        connessione.close();
    }


    /**
     * Consente di aggiornare una trascrizione
     *
     * @param text il nuovo testo inserito
     * @param trId l'id della trascrizione
     *
     */
    public static void modTrascrizione(String text, int trId) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
            PreparedStatement pstmt = connessione.prepareStatement("UPDATE trascrizione SET testo = ? WHERE ID =  ? ");
            pstmt.setString(1, text);
            pstmt.setInt(2, trId);
            pstmt.executeUpdate();
        connessione.close();
    }

    /**
     * Assegna una immagine da trascrivere ad un trascrittore
     *
     * @param em l'email dell'utente designato
     * @param idImm l'id dell'immagine da assegnare
     *
     */
    public static void insAssTrascrizione(String em, int idImm) throws SQLException {
        Connection connessione = DatabaseConnector.connessioneDB();
        String sql = "INSERT INTO assegna_trascrizione(usern, IDimmagine) VALUES (?, ?)";
        PreparedStatement statement = connessione.prepareStatement(sql);
        statement.setString(1, em);
        statement.setInt(2, idImm);
        statement.executeUpdate();
        connessione.close();
    }

    /**
     * Prende un'immagine di un'opera dal database
     *
     * @param id l'id dell'opera
     * @param pag pagina dell'immagine
     * @return l'id dell'immagine
     */
    public static int getImmagine(int id, int pag) throws SQLException {
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


    /**
     * Prende le immagini in attesa di validazione
     *
     * @return lista delle immagini
     */
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

    /**
     * Prende le trascrizioni in attesa di validazione
     *
     * @return lista delle trascrizioni
     */
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

    /**
     * Prende le trascrizioni validate, se presenti, di un'opera
     *
     * @param idOpera l'id dell'opera
     * @return lista delle trascrizioni
     */
    public static ArrayList<ImmagineTrascrizione> getTrascrizioniConv(int idOpera) throws SQLException {
        ArrayList<ImmagineTrascrizione> pages = new ArrayList<ImmagineTrascrizione>();
        Connection connessione = DatabaseConnector.connessioneDB();
        Statement statement1 = connessione.createStatement();
        String sql1 = "SELECT * FROM opera WHERE ID =  " + idOpera + ";";
        ResultSet resultSet1 = statement1.executeQuery(sql1);
        //String imm = null;
        String tra = null;
        resultSet1.next();
        //imm = resultSet1.getString("completaIMG");
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

    /**
     * Unisce l'immagine alla trascrizione, se presente
     *
     * @return l'immagine
     */
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

    /**
     * Prende le immagini da trascrivere assegnato ad un trascrittore
     *
     * @param email l'email del trascrittore
     * @return lista delle immagini da trascrivere
     */
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

    /**
     * Consente di inserire una nuova trascrizione
     *
     */
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

    /**
     * Restituisce la trascrizione della pagina di un'opera
     *
     * @param numero numero di pagina
     * @param idOp id dell'opera
     *
     * @return la trascrizione
     */
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

    /**
     * Serve a togliere le pagine già acquisite dall'elenco di pagine
     * da acquisire in fase di acquisizione
     *
     */
    public static boolean esisteGiaPagina(Connection connessione, String codiceOpera, int pagina) throws SQLException{
        Statement statement = connessione.createStatement();
        String sql = "select * from immagine join opera where immagine.IDopera = opera.ID and opera.codice = '"+codiceOpera+"' and numero_pagina = "+pagina ;
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            return false;
        }
        return true;
    }

    static class OrdinaImm implements Comparator<ImmagineTrascrizione> {
        public int compare(ImmagineTrascrizione o1, ImmagineTrascrizione o2) {
            if (o1.getNumeroPagina() > o2.getNumeroPagina()) return 1;
            if (o1.getNumeroPagina() < o2.getNumeroPagina()) return -1;
            else return 0;
        }
    }
}
