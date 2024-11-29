package it.epicode.catalogo;

public class Main {
    public static void main(String[] args) {
        Libro libro1 = new Libro(1, "Orgoglio e pregiudizio", 2001, 200, "Bob", "Strano");
        Rivista rivista1 = new Rivista(2, "Geolocal", 2023, 10, "mensile");

        System.out.println(libro1);
        System.out.println(rivista1);
    }
}
