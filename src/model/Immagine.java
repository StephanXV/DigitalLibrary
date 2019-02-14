package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Immagine implements Pagina {
    private int id;
    private int numeroPagina;
    private int id_opera;
    private int validazione;
    private String fonte;
    private String timestamp;
    private String lingua;
    private String nomeOpera;


    public Immagine(int n_pagina, String nomeOpera, String lingua, String src, int id) {
        this.numeroPagina = n_pagina;
        this.nomeOpera = nomeOpera;
        this.lingua = lingua;
        this.fonte = src;
        this.id_opera = id;
    }

    public Immagine(ResultSet resultSet) throws SQLException {
        this.setID(resultSet.getInt("ID"));
        this.setNumeroPagina(resultSet.getInt("numero_pagina"));
        this.setID_opera(resultSet.getInt("IDopera"));
        this.setValidazione(resultSet.getInt("validazione"));
        this.setFonte(resultSet.getString("sorgente"));
        this.setTimestamp(resultSet.getString("time_stamp"));
    }

    @Override
    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    @Override
    public int getNumeroPagina() {
        return numeroPagina;
    }

    @Override
    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    @Override
    public int getID_opera() {
        return id_opera;
    }

    @Override
    public void setID_opera(int ID_opera) {
        this.id_opera = ID_opera;
    }

    @Override
    public int getValidazione() {
        return validazione;
    }

    @Override
    public void setValidazione(int validazione) {
        this.validazione = validazione;
    }

    @Override
    public String getFonte() {
        return fonte;
    }

    @Override
    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(String t) {
        this.timestamp = t;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getNomeOpera() {
        return nomeOpera;
    }

    public void setNomeOpera(String nomeOpera) {
        this.nomeOpera = nomeOpera;
    }

    @Override
    public String toString() {
        return "Immagine: " + id;
    }
}
