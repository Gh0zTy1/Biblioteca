/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface PrestamoDAO {
    void registrarPrestamo(Prestamo prestamo) throws Exception;
    
    Prestamo consultarPrestamo(int idPrestamo);
    List<Prestamo> listarPrestamos();
    List<Prestamo> consultarPrestamos(Libro libro);
    List<Prestamo> consultarPrestamos();
    void devolverPrestamo(Prestamo prestamo) throws Exception; 
}
