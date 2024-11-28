/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package valoration;

import com.valorationService.exceptions.SystemNotAvailableException;
import com.valorationService.exceptions.ValorationNotFoundException;
import entidades.Libro;
import entidades.ReseñaLibro;
import entidades.Valoration;
import interfaces.IValorate;
import java.util.ArrayList;
import java.util.List;

public  class Valorate implements IValorate {

    private static final List<Libro> libros = new ArrayList<>();

    public Valorate() {
    }

    @Override
    public Valoration getValoration(String title, String author) 
            throws SystemNotAvailableException, ValorationNotFoundException {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(title) &&
                    libro.getAutor().equalsIgnoreCase(author)) {
                if (libro.getReseña() != null) {
                    ReseñaLibro reseña = libro.getReseña();
                    return new Valoration(reseña.getCalificacion(), reseña.getReseña());
                }
            }
        }
        throw new ValorationNotFoundException("No se encontró el libro con el título y autor proporcionados.");
    }

    public void addBook(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> getBooksToValorate() {
        return new ArrayList<>(libros);
    }
}
