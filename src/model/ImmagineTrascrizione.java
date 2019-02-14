package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImmagineTrascrizione implements Pagina { //pagina con immagine piu trascrizione(eventualmente assente)
    private int id;
    private int numeroPagina;
    private int id_opera;
    private int validazione;
    private String fonte;
    private Immagine im;
    private String timestamp;

    public ImmagineTrascrizione(int ID, int numeroPagina, int ID_opera, int validazione, String fonte, Immagine im) {
        this.id = ID;
        this.numeroPagina = numeroPagina;
        this.id_opera = ID_opera;
        this.validazione = validazione;
        this.fonte = fonte;
        this.im = im;
    }

    public ImmagineTrascrizione(Immagine im) {
        this.im = im;
    }

    public ImmagineTrascrizione(ResultSet resultSet) throws SQLException {
        this.setID(resultSet.getInt("ID"));
        this.setID_opera(resultSet.getInt("IDopera"));
        this.setValidazione(resultSet.getInt("validazione"));
        this.setFonte(resultSet.getString("testo"));
        this.setNumeroPagina(resultSet.getInt("numero_pagina"));
        this.setTimestamp(resultSet.getString("time_stamp"));
    }

    public ImmagineTrascrizione() {
		// TODO Auto-generated constructor stub
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

    public Immagine getIm() {
        return im;
    }

    public void setIm(Immagine im) {
        this.im = im;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(String t) {
        this.timestamp = t;
    }

    @Override
    public String toString() {
        return "Trascrizione: " + id;
    }
}

