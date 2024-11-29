package it.epicode.catalogo;

import it.epicode.catalogo.exceptions.CodiceISBN.CodiceISBNException;
import jdk.incubator.vector.VectorOperators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio {
    private List<Testo> catalogo;

    public Archivio() {
        this.catalogo = new ArrayList<>();
    }

    //Aggiungo elemento
    public void addElement(Testo elemento) throws CodiceISBNException {
        if(catalogo.stream().anyMatch(e-> e.getCodiceISBN() == elemento.getCodiceISBN())) {
            throw new CodiceISBNException("Elemento con codice ISBN: " + elemento.getCodiceISBN() + " già esistente!!!");
        }
        catalogo.add(elemento);
    }

    //Ricerca tramite codice ISBN
    public Testo searchByISBN(int codiceISBN) throws CodiceISBNException {
        return catalogo.stream()
                .filter(e -> e.getCodiceISBN() == codiceISBN)
                .findFirst()
                .orElseThrow(() -> new CodiceISBNException("Elemento con codice ISBN " + codiceISBN + " non trovato."));
    }

    //Rimuovo elemento
    public void removeElemento(int codiceISBN) throws CodiceISBNException {
        Testo elemento = searchByISBN(codiceISBN);
        catalogo.remove(elemento);
    }

    //Ricerco tramite anno
    public List<Testo> searchByYear(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    //Ricerca per Autore
    public List<Libro> searchByAuthor(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    //Aggiorno elemento esistente
    public void updateElemento(Testo elemento) throws CodiceISBNException {
        Testo elementoEsistente = searchByISBN(elemento.getCodiceISBN());
        catalogo.remove(elementoEsistente);
        catalogo.add(elemento);
    }

    //Statistiche
    public void printStatistiche() {
        long numeroLibri = catalogo.stream()
                .filter(e -> e instanceof Libro)
                .count();
        long numeroRiviste = catalogo.stream()
                .filter(e -> e instanceof Rivista)
                .count();
        Optional<Testo> elementoConPiuPagine = catalogo.stream()
                .max((elemento1, elemento2) -> Integer.compare(elemento1.getNumeroPagine(), elemento2.getNumeroPagine()));
        double mediaPagine = catalogo.stream()
                .mapToInt(Testo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.printf("Numro totale dei libri: ", numeroLibri);
        System.out.printf("Numro totale delle riviste: ", numeroRiviste);
        elementoConPiuPagine.ifPresent(e -> System.out.println("Elemento cn piu pagine: " + e));
        System.out.printf("Media di tutte le pagine degli elementi: ", mediaPagine);
    }
}
