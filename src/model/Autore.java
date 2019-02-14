package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

//Design pattern Singleton

public class Autore
{
  private int id;
  private String nome;
  private String cognome;
  private String data_nascita;
  private String nazionalita;
  
  public Autore() {
	  
  }
  
  public Autore (String nome, String cognome, String data, String nazionalita) {
	  this.nome=nome;
	  this.cognome=cognome;
	  this.data_nascita=data;
	  this.nazionalita=nazionalita;
  }

	public Autore(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getInt("ID"));
		this.setNome(resultSet.getString("nome"));
		this.setCognome(resultSet.getString("cognome"));
		this.setData_nascita(resultSet.getString("data_nascita"));
		this.setNazionalita(resultSet.getString("nazionalita"));
	}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCognome() {
	return cognome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public String getData_nascita() {
	return data_nascita;
}

public void setData_nascita(String data_nascita) {
	this.data_nascita = data_nascita;
}

public String getNazionalita() {
	return nazionalita;
}

public void setNazionalita(String nazionalita) {
	this.nazionalita = nazionalita;
}

@Override
public String toString() {
	return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data_nascita=" + data_nascita
			+ ", nazionalita=" + nazionalita + "]";
}
}

/*public class usaAutore
{
 public static void main(String args[])
 {
   Autore.getInstance().helloWorld();
 }
}*/