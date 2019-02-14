package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Opera {
    private int ID;
    private String codice;
    private String titolo;
    private String descrizione;
    private int date;
    private String lingua;
    private int pagine;
    private List<Categoria> categoria;
    private List<Autore> autore;

    public Opera() {
    }

    public Opera(int ID, String codice, String titolo, String descrizione, int date, String lingua, int pagine, List<Categoria> categoria, List<Autore> autore) {
        this.ID = ID;
        this.codice = codice;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.date = date;
        this.lingua = lingua;
        this.pagine = pagine;
        this.categoria = categoria;
        this.autore = autore;
    }

    public Opera(ResultSet resultSet) throws SQLException {
        this.setID(resultSet.getInt("ID"));
        this.setCodice(resultSet.getString("codice"));
        this.setTitolo(resultSet.getString("titolo"));
        this.setDescrizione(resultSet.getString("descrizione"));
        this.setDate(resultSet.getInt("anno"));
        this.setLingua(resultSet.getString("lingua"));
        this.setPagine(resultSet.getInt("pagine"));
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public int getPagine() {
        return pagine;
    }

    public void setPagine(int pagine) {
        this.pagine = pagine;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public List<Autore> getAutore() {
        return autore;
    }

    public void setAutore(List<Autore> autore) {
        this.autore = autore;
    }


    @Override
    public String toString() {
        return "Opera{ codice='" + codice + '\'' +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", date=" + date +
                ", lingua='" + lingua + '\'' +
                ", n.pagine='" + pagine + '\'' +
                ", categoria='" + categoria + '\'' +
                ", autore='" + autore + '\'' +
                '}';
    }
}
