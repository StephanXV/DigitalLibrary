package model;

public class UtenteBase {
    private int ID;
    private String nome;
    private String cognome;
    private String data_nascita;
    private String data_iscrizione;
    private String email;
    private String pass;
    private String sesso;

    public UtenteBase() {
    }

    public UtenteBase(int ID, String nome, String cognome, String data_nascita, String data_iscrizione, String email, String pass, String sesso) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.data_iscrizione = data_iscrizione;
        this.email = email;
        this.pass = pass;
        this.sesso = sesso;
    }

    public UtenteBase(String nome, String cognome, String data_nascita, String email, String pass, String sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.email = email;
        this.pass = pass;
        this.sesso = sesso;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getData_iscrizione() {
        return data_iscrizione;
    }

    public void setData_iscrizione(String data_iscrizione) {
        this.data_iscrizione = data_iscrizione;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    @Override
    public String toString() {
        return  "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data di nascita=" + data_nascita +
                ", data iscrizione=" + data_iscrizione +
                ", email='" + email + '\'' +
                ", password='" + pass + '\'' +
                ", sesso=" + sesso;
    }
}
