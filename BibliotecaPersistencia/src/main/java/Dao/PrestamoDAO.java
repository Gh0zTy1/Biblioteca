/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import entidades.Libro;
import entidades.Prestamo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Interfaces.IPrestamoDAO;

/**
 *
 * @author gaspa
 */
public class PrestamoDAO implements IPrestamoDAO{
    private List<Prestamo> prestamos;
    private static PrestamoDAO instancia; 
    public static PrestamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new PrestamoDAO();
        }
        return instancia;
    }
    private PrestamoDAO(){
        prestamos = new ArrayList<>();
    }
    
    @Override
    public void registrarPrestamo(Prestamo prestamo) throws Exception {
        
        Libro libro=prestamo.getLibro();
        System.out.println(libro);
        
        if(obten(prestamo)!=null)throw new Exception("El libro ya esta prestado al usuario");
        
        prestamos.add(prestamo);
        System.out.println(prestamos);
        libro.setDisponible(false);    
        
    }

   @Override
public void devolverPrestamo(Prestamo prestamo) throws Exception {
    int pos = prestamos.indexOf(prestamo);
    if (pos < 0) {
        throw new Exception("Préstamo no encontrado");
    }

    // Marca el libro como disponible y elimina el préstamo
    Libro libro = prestamo.getLibro();
    libro.setDisponible(true);
    prestamos.remove(pos);

    System.out.println("Libro devuelto y disponible: " + libro);
}


    

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }
    
    @Override
public List<Prestamo> consultarPrestamos(Libro libro) {
    return prestamos.stream()
                    .filter(p -> p.getLibro().equals(libro))  // Filtra por libro
                    .collect(Collectors.toList());
}

public Prestamo obten(Prestamo prestamo) {
    return prestamos.stream()
                    .filter(p -> p.equals(prestamo))  // Busca el préstamo por libro y usuario
                    .findFirst()
                    .orElse(null);
}
    
    @Override
    public List<Prestamo> consultarPrestamos() {
        return new ArrayList<>(prestamos); // Devuelve una copia de la lista para evitar modificar el original
    }
    
    public List<Prestamo> buscarPrestamosDeLibrosPrestados() {
    return prestamos.stream()
                    .filter(prestamo -> prestamo.getLibro() != null && !prestamo.getLibro().getDisponible())
                    .collect(Collectors.toList());
    }

    
    
}

