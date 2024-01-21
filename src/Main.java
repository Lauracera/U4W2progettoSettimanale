public class Main {
    public static void main(String[] args) {
        ArchivioCatalogo archivio = new ArchivioCatalogo();

        Libro libro1 = new Libro("ISBN123", "Titolo Libro 1", 2022, 300, "Autore 1", "Genere 1");
        Libro libro2 = new Libro("ISBN456", "Titolo Libro 2", 2020, 250, "Autore 2", "Genere 2");

        Rivista rivista1 = new Rivista("ISBN789", "Titolo Rivista 1", 2021, 50, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("ISBN012", "Titolo Rivista 2", 2022, 40, Periodicita.SETTIMANALE);

        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(rivista1);
        archivio.aggiungiElemento(rivista2);

        eseguiOperazioni(archivio);

        try {
            salvaECaricaSuDisco(archivio);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void eseguiOperazioni(ArchivioCatalogo archivio) {

        archivio.ricercaPerISBN("ISBN123").ifPresent(System.out::println);
        archivio.ricercaPerAnnoPubblicazione(2022).forEach(System.out::println);
        archivio.ricercaPerAutore("Autore 1").forEach(System.out::println);
    }

    private static void salvaECaricaSuDisco(ArchivioCatalogo archivio) throws IOException, ClassNotFoundException {

        archivio.salvaSuDisco("archivio.dat");

        ArchivioCatalogo archivioCaricato = new ArchivioCatalogo();
        archivioCaricato.caricaDaDisco("archivio.dat");

        archivioCaricato.ricercaPerISBN("ISBN789").ifPresent(System.out::println);
        archivioCaricato.ricercaPerAnnoPubblicazione(2020).forEach(System.out::println);
    }
}