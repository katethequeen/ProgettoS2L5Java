package it.epicode.catalogo;

import it.epicode.catalogo.exceptions.CodiceISBN.CodiceISBNException;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Archivio archivio = new Archivio();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continua = true;
        while (continua) {
            mostraMenu();
            int scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta) {
                case 1:
                    aggiungiElemento();
                    break;
                case 2:
                    ricercaPerISBN();
                    break;
                case 3:
                    rimuoviElemento();
                    break;
                case 4:
                    ricercaPerAnno();
                    break;
                case 5:
                    ricercaPerAutore();
                    break;
                case 6:
                    aggiornaElemento();
                    break;
                case 7:
                    stampaStatistiche();
                    break;
                case 0:
                    continua = false;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }

        }
    }

    public static void mostraMenu() {
        System.out.println("Scegli un'operazione:");
        System.out.println("1. Aggiungi un elemento");
        System.out.println("2. Ricerca per codice ISBN");
        System.out.println("3. Rimuovi un elemento");
        System.out.println("4. Ricerca per anno di pubblicazione");
        System.out.println("5. Ricerca per autore");
        System.out.println("6. Aggiorna un elemento");
        System.out.println("7. Stampa statistiche");
        System.out.println("0. Esci");
    }

    private static void aggiungiElemento() {
        System.out.println("Libro o Rivista?");
        String scelta = scanner.nextLine();
        System.out.println("Inserisci codice ISBN:");
        int codiceISBN = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci titolo");
        String titolo = scanner.nextLine();
        System.out.println("Inserisci anno di pubblicazione");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci numero di pagine:");
        int pagine = Integer.parseInt(scanner.nextLine());

        if (scelta.equalsIgnoreCase("libro")) {
            System.out.println("Inserisci autore:");
            String autore = scanner.nextLine();
            System.out.println("Inserisci genere:");
            String genere = scanner.nextLine();
            try {
                archivio.addElement(new Libro(codiceISBN, titolo, anno, pagine, autore, genere));
            } catch (CodiceISBNException e) {
                System.out.println(e.getMessage());
            }
        } else if (scelta.equalsIgnoreCase("rivista")) {
            System.out.println("Inserisci periodicità:");
            String periodicita = scanner.nextLine();
            try {
                archivio.addElement(new Rivista(codiceISBN, titolo, anno, pagine, periodicita));
            } catch (CodiceISBNException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Scelta non valida!");
        }
    }

    private static void ricercaPerISBN() {
        System.out.println("Inserisci codice ISBN:");
        int codiceISBN = Integer.parseInt(scanner.nextLine());
        try {
            Testo elemento = archivio.searchByISBN(codiceISBN);
            System.out.println("Elemento trovato: " + elemento);
        } catch (CodiceISBNException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void rimuoviElemento() {
        System.out.println("Inserisci codice ISBN per rimozione:");
        int codiceISBN = Integer.parseInt(scanner.nextLine());
        try {
            archivio.removeElemento(codiceISBN);
            System.out.println("Elemento rimosso");
        } catch (CodiceISBNException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ricercaPerAnno() {
        System.out.println("Inserisci anno di pubblicazione:");
        int anno = Integer.parseInt(scanner.nextLine());
        List<Testo> elementi = archivio.searchByYear(anno);
        if (elementi.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno: " + anno);
        } else {
            System.out.println("Elementi trovati:");
            elementi.forEach((System.out::println));
        }
    }

    private static void ricercaPerAutore() {
        System.out.println("Inserisci autore:");
        String autore = scanner.nextLine();
        List<Libro> libri = archivio.searchByAuthor(autore);
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore " + autore);
        } else {
            System.out.println("Libri trovati:");
            libri.forEach(System.out::println);
        }
    }

    private static void aggiornaElemento() {
        System.out.println("Inserisci codice ISBN dell'elemento da aggiornare");
        int codiceISBN = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci nuovo titolo:");
        String titolo = scanner.nextLine();
        System.out.println("Inserisci nuovo anno di pubblicazione:");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserisci nuovo numero di pagine:");
        int pagine = Integer.parseInt(scanner.nextLine());

        try {
            Testo elementoEsistente = archivio.searchByISBN(codiceISBN);
            if (elementoEsistente instanceof Libro) {
                System.out.println("Inserisci nuovo autore:");
                String autore = scanner.nextLine();
                System.out.println("Inserisci nuovo genere:");
                String genere = scanner.nextLine();
                archivio.updateElemento(new Libro(codiceISBN, titolo, anno, pagine, autore, genere));
            } else if (elementoEsistente instanceof Rivista) {
                System.out.println("Inserisci nuova periodicità:");
                String periodicita = scanner.nextLine();
                archivio.updateElemento(new Rivista(codiceISBN, titolo, anno, pagine, periodicita));
            }
            System.out.println("Elemento aggiornato con successo.");
        } catch (CodiceISBNException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void stampaStatistiche() {
        archivio.printStatistiche();
    }
}

