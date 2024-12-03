/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Excepciones.ServicioNoDisponibleException;
import entidades.Libro;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Interfaces.ILibroDAO;
import Interfaces.IServicioEvaluacionLibros;
import entidades.ReseñaLibro;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caarl
 */
public class LibroDAO implements ILibroDAO{
    private List<Libro> libros;
    private static LibroDAO instancia;
    private ServicioEvaluacionLibrosDAO servicioEvaluacion;

    // Constructor para inyectar el servicio de evaluación
    public static LibroDAO getInstancia(ServicioEvaluacionLibrosDAO servicioEvaluacion){
        if (instancia == null) {
            instancia = new LibroDAO(servicioEvaluacion);
        }
        return instancia;
    }
    public LibroDAO(ServicioEvaluacionLibrosDAO servicioEvaluacion) {
        this.servicioEvaluacion = servicioEvaluacion;
        this.libros = new ArrayList<>();
    }

    // Método para agregar un libro
    public void agregarLibro(Libro libro) throws ServicioNoDisponibleException {
        // Solicitar la evaluación del libro desde el servicio externo
        ReseñaLibro reseñaLibro = servicioEvaluacion.obtenerEvaluacion(libro.getIsbn());

        // Actualizar la valoración y reseña del libro
        libro.setValoracion(reseñaLibro.getValoracion());
        libro.setReseña(reseñaLibro.getComentario());
        libros.add(libro);
        
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    // Método para actualizar la información de un libro
    public void actualizarLibro(Libro libro) throws ServicioNoDisponibleException {
        // Solicitar la evaluación actualizada del libro desde el servicio externo
        ReseñaLibro reseñaLibro = servicioEvaluacion.obtenerEvaluacion(libro.getIsbn());

        // Actualizar la valoración y reseña del libro
        libro.setValoracion(reseñaLibro.getValoracion());
        libro.setReseña(reseñaLibro.getComentario());
        
        int indice = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(libro.getIsbn())) {
                indice = i;
                break;
            }
        }
        if (indice != -1) {
            libros.set(indice, libro);
        }
        System.out.println("Libro actualizado: " + libro.getTitulo());
    }

    // Método para obtener un libro por su ISBN
    public Libro obtenerLibroPorIsbn(String isbn) {
        // Aquí podrías buscar el libro en la base de datos o lista
        // Devolvemos un libro ficticio por ahora para el ejemplo
        return new Libro(isbn, "Título ficticio", "Autor ficticio");
    }
    @Override
    public Libro buscarPorId(String id) {
        return libros.stream()
                    .filter(l -> l.getIsbn().equals(id))
                    .findFirst()
                    .orElse(null);
    }
    @Override
    public void eliminar(String id) {
        libros.removeIf(l -> l.getIsbn().equals(id));
    }
    @Override
    public List<Libro> buscarTodos() {
        return new ArrayList<>(libros);
    }
    @Override
    public List<Libro> buscarPorAutor(String autor) {
        return libros.stream()
                    .filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    .collect(Collectors.toList());
    }
    public List<Libro> buscarPorIdLista(String id) {
    return libros.stream()
                 .filter(l -> l.getIsbn().equals(id)) 
                 .collect(Collectors.toList()); 
    }
    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                    .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .collect(Collectors.toList());
    }
    public List<Libro> lista(){
        return libros;
    }
    public void cargarTabla(DefaultTableModel model){
        java.util.List<Libro> listaLibros = this.buscarTodos();
        
        for (Libro libros : listaLibros) {
        model.addRow(new Object[]{
            libros.getIsbn(),
            libros.getTitulo(), 
            libros.getAutor(), 
            libros.getReseña(), 
            libros.getValoracion(), 
            });
        }
    }
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
}
