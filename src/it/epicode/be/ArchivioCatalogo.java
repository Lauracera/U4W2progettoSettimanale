package it.epicode.be;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ArchivioCatalogo {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public Optional<ElementoCatalogo> ricercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst();
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        return catalogo.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(elemento -> elemento instanceof Libro && ((Libro) elemento).getAutore().equals(autore))
                .collect(Collectors.toList());
    }

    public void salvaSuDisco(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(catalogo);
        }
    }

    public void caricaDaDisco(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            catalogo = (List<ElementoCatalogo>) ois.readObject();
        }
    }
}
