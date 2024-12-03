/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IPrestamoDAO {
    void registrarPrestamo(Prestamo prestamo) throws Exception;
    
  
    List<Prestamo> listarPrestamos();
    List<Prestamo> consultarPrestamos(Libro libro);
    List<Prestamo> consultarPrestamos();
    void devolverPrestamo(Prestamo prestamo) throws Exception; 
}
