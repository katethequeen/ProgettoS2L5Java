package it.epicode.catalogo;

public class Libro extends Testo{
    private String autore;
    private String genere;

    public Libro(int codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "L'autore del libro è: " + autore + " e il genere è: " + genere + "," + super.toString();
    }
}
