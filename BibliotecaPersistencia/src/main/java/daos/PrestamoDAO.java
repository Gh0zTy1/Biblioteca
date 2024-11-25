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
    boolean registrarPrestamo(Prestamo prestamo);
    boolean devolverPrestamo(int idPrestamo);
    Prestamo consultarPrestamo(int idPrestamo);
    List<Prestamo> listarPrestamos();
    public List<Prestamo> consultarPrestamos(Libro libro);
    public List<Prestamo> consultarPrestamos();
}
