package model;

import java.util.List;

public class UtentePrivilegiato extends UtenteBase {
    private List<String> permessi;

    public UtentePrivilegiato() {
    }

    public UtentePrivilegiato(int ID, String nome, String cognome, String data_nascita, String data_iscrizione, String email, String pass, String sesso, List<String> permessi) {
        super(ID, nome, cognome, data_nascita, data_iscrizione, email, pass, sesso);
        this.permessi = permessi;
    }

    public List<String> getPermessi() {
        return permessi;
    }

    public void setPermessi(List<String> permessi) {
        this.permessi = permessi;
    }

    @Override
    public String toString() {
        return super.toString() + ", permessi= " + permessi;
    }
}
