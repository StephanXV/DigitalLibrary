package model;

import java.util.List;

public class Trascrittore extends UtentePrivilegiato {

    public enum Esperienza {
        zero(0), one(1), two(2), tree(3), four(4), five(5);
        private final int value;

        Esperienza(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private Esperienza exp;

    public Trascrittore() {
    }

    public Trascrittore(int ID, String nome, String cognome, String data_nascita, String data_iscrizione, String email, String pass, String sesso, List<String> permessi, Esperienza exp) {
        super(ID, nome, cognome, data_nascita, data_iscrizione, email, pass, sesso, permessi);
        this.exp = exp;
    }

    public int getExp() {
        return exp.value;
    }

    public void setExp(Esperienza exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return super.toString() + ", esperienza = " + exp.getValue();
    }
}
