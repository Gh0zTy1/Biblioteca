/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idao;

import daos.PrestamoDAO;
import entidades.Libro;
import entidades.Prestamo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author gaspa
 */
public class IPrestamoDAO implements PrestamoDAO{
    private List<Prestamo> prestamos;
    private static IPrestamoDAO instancia; 
    public static IPrestamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new IPrestamoDAO();
        }
        return instancia;
    }
    private IPrestamoDAO(){
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
    int posIsbn = prestamos.indexOf(prestamo);
    System.out.println(posIsbn);
    System.out.println(prestamos);
    int posNumCred = prestamos.indexOf(prestamo);
    if (posIsbn != posNumCred) throw new Exception("Los parametros no coinciden");
    if (posIsbn < 0 || posNumCred < 0) throw new Exception("Parametro inexistente");
    
    // Primero, obtenemos el libro y lo marcamos como disponible
    Libro libro = prestamo.getLibro();
    libro.setDisponible(true);
    
    // Luego, eliminamos el préstamo de la lista
    prestamos.remove(prestamo);
    
    System.out.println("Libro devuelto y disponible: " + libro);
}

    

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }
    
    public Prestamo obten(Prestamo prestamo){
        int posIsbn=prestamos.indexOf(prestamo);
        int posNumCred=prestamos.indexOf(prestamo);
        if(posIsbn==posNumCred&&posIsbn>=0&&posNumCred>=0)return prestamos.get(posIsbn);
        return null;
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
    
    @Override
    public List<Prestamo> consultarPrestamos() {
        return new ArrayList<>(prestamos); // Devuelve una copia de la lista para evitar modificar el original
    }
    
    public List<Prestamo> buscarPrestamosDeLibrosPrestados() {
    return prestamos.stream()
                    .filter(prestamo -> prestamo.getLibro() != null && !prestamo.getLibro().isDisponible())
                    .collect(Collectors.toList());
    }

    @Override
    public Prestamo consultarPrestamo(int idPrestamo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

