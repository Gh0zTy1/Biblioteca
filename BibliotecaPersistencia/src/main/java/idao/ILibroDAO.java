/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idao;

import daos.LibroDAO;
import entidades.Libro;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author caarl
 */
public class ILibroDAO implements LibroDAO {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public Libro guardar(Libro libro) {
        libros.add(libro);
        return libro;
    }

    @Override
    public Libro buscarPorId(String id) {
        return libros.stream()
                    .filter(l -> l.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                    .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        return libros.stream()
                    .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Libro> buscarTodos() {
        return new ArrayList<>(libros);
    }

    @Override
    public void eliminar(String id) {
        libros.removeIf(l -> l.getId().equals(id));
    }

    @Override
    public void actualizar(Libro libro) {
        int indice = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(libro.getId())) {
                indice = i;
                break;
            }
        }
        if (indice != -1) {
            libros.set(indice, libro);
        }
    }
}
