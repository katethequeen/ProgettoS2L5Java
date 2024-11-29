package it.epicode.catalogo;

public class Rivista extends Testo{
    private String periodicita;

    public Rivista(int codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public String getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "La rivista è: " + periodicita + ", " + super.toString();
    }
}
