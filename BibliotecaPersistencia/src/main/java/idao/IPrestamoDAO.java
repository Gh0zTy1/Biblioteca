/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idao;

import daos.PrestamoDAO;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gaspa
 */
public class IPrestamoDAO implements PrestamoDAO{
    private List<Prestamo> prestamos = new ArrayList<>();
    private static IPrestamoDAO instancia; 
    public static IPrestamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new IPrestamoDAO();
        }
        return instancia;
    }
    
    @Override
    public boolean registrarPrestamo(Prestamo prestamo) {
        return prestamos.add(prestamo);
    }

    @Override
    public boolean devolverPrestamo(int idPrestamo) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdPrestamo() == idPrestamo) {
                prestamo.setFechaDevolucion(new Date());
                return true;
            }
        }
        return false;
    }

    @Override
    public Prestamo consultarPrestamo(int idPrestamo) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdPrestamo() == idPrestamo) {
                return prestamo;
            }
        }
        return null;
    }
    

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }
    
    @Override
    public List<Prestamo> consultarPrestamos(Libro libro) {
        List<Prestamo> prestamosPorLibro = new ArrayList<>();

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getId()== libro.getId()) {
                prestamosPorLibro.add(prestamo);
            }
        }

        return prestamosPorLibro;
    }
    
    public List<Prestamo> consultarPrestamos() {
        return new ArrayList<>(prestamos); // Devuelve una copia de la lista para evitar modificar el original
    }
    
    public List<Prestamo> buscarPrestamosDeLibrosPrestados() {
    return prestamos.stream()
                    .filter(prestamo -> prestamo.getLibro() != null && !prestamo.getLibro().isDisponible())
                    .collect(Collectors.toList());
}
    
}

