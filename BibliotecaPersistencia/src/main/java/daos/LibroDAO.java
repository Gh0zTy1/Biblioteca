/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import entidades.Libro;
import java.util.List;

/**
 *
 * @author caarl
 */
public interface LibroDAO {
   Libro guardar(Libro libro);
    Libro buscarPorId(String id);
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(String autor);
    List<Libro> buscarTodos();
    void eliminar(String id);
    void actualizar(Libro libro); 
}
