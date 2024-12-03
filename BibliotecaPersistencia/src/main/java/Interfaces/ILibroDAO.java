/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Libro;
import java.util.List;

/**
 *
 * @author caarl
 */
public interface ILibroDAO {
    Libro buscarPorId(String id);
    List<Libro> buscarPorTitulo(String titulo);
    List<Libro> buscarPorAutor(String autor);
    List<Libro> buscarTodos();
    void eliminar(String id);
}
