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
    private static ILibroDAO instancia; // Instancia única
    private List<Libro> libros = new ArrayList<>();
    

    private ILibroDAO() {
    } // Constructor privado para evitar instanciación directa
    
    public static ILibroDAO getInstancia() {
        if (instancia == null) {
            instancia = new ILibroDAO();
        }
        return instancia;
    }
    
    @Override
    public Libro guardar(Libro libro) {
        int nuevoId = libros.isEmpty() ? 1 : libros.get(libros.size() - 1).getId() + 1;
        libro.setId(nuevoId); // Asigna el nuevo ID
        libros.add(libro);
        return libro;
    }

    @Override
    public Libro buscarPorId(int id) {
        return libros.stream()
                    .filter(l -> l.getId() == (id))
                    .findFirst()
                    .orElse(null);
    }
    
    public List<Libro> buscarPorIdLista(int id) {
    return libros.stream()
                 .filter(l -> l.getId() == id) 
                 .collect(Collectors.toList()); 
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
    public void eliminar(int id) {
        libros.removeIf(l -> l.getId() == (id));
    }

    @Override
    public void actualizar(Libro libro) {
        int indice = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == (libro.getId())) {
                indice = i;
                break;
            }
        }
        if (indice != -1) {
            libros.set(indice, libro);
        }
    }
}
