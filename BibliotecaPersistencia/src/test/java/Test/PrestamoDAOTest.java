/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import Dao.PrestamoDAO;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias para la implementación de IPrestamoDAO.
 */
public class PrestamoDAOTest {
    private PrestamoDAO prestamoDAO;
    private Usuario usuarioEjemplo;
    private Libro libroEjemplo;

    @Before
    public void setUp() {
        prestamoDAO =  PrestamoDAO.getInstancia(); // Obtener la instancia del Singleton
        usuarioEjemplo = new Usuario("U001", "Juan Pérez", "juan@email.com");
        libroEjemplo = new Libro("1", "Don Quijote", "Miguel de Cervantes");
        libroEjemplo.setDisponible(true);

        // Limpia la lista de préstamos antes de cada prueba
        prestamoDAO.listarPrestamos().clear();
    }
    
    @Test
    public void testRegistrarPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo(usuarioEjemplo, libroEjemplo, new Date(), null);

        prestamoDAO.registrarPrestamo(prestamo);

        List<Prestamo> prestamos = prestamoDAO.listarPrestamos();
        assertEquals("Debe haber un préstamo registrado", 1, prestamos.size());
        assertFalse("El libro debe marcarse como no disponible", libroEjemplo.getDisponible());
    }

    @Test(expected = Exception.class)
    public void testRegistrarPrestamoDuplicado() throws Exception {
        Prestamo prestamo = new Prestamo(usuarioEjemplo, libroEjemplo, new Date(), null);
        prestamoDAO.registrarPrestamo(prestamo);

        // Intentar registrar el mismo préstamo nuevamente
        prestamoDAO.registrarPrestamo(prestamo);
    }

    @Test
public void testDevolverPrestamo() throws Exception {
    // Configuración de los objetos necesarios para el test
    Libro libro = new Libro("Don Quijote", "Miguel de Cervantes", "12345");
    Usuario usuario = new Usuario("Juan Pérez", "1234");
    Prestamo prestamo = new Prestamo(usuario, libro, new Date(), null);
    
    // Registrar el préstamo
    PrestamoDAO.getInstancia().registrarPrestamo(prestamo);

    // Verificar que el libro esté marcado como no disponible
    assertFalse(libro.getDisponible());

    // Devolver el préstamo
    PrestamoDAO.getInstancia().devolverPrestamo(prestamo);
    
    // Verificar que el libro esté ahora disponible
    assertTrue(libro.getDisponible());
}


    @Test
    public void testConsultarPrestamosPorLibro() throws Exception {
        Prestamo prestamo = new Prestamo(usuarioEjemplo, libroEjemplo, new Date(), null);
        prestamoDAO.registrarPrestamo(prestamo);

        List<Prestamo> prestamosPorLibro = prestamoDAO.consultarPrestamos(libroEjemplo);

        assertEquals("Debe haber un préstamo registrado para el libro", 1, prestamosPorLibro.size());
        assertEquals("El préstamo registrado debe coincidir", prestamo, prestamosPorLibro.get(0));
    }

    @Test
    public void testBuscarPrestamosDeLibrosPrestados() throws Exception {
        Prestamo prestamo = new Prestamo(usuarioEjemplo, libroEjemplo, new Date(), null);
        prestamoDAO.registrarPrestamo(prestamo);

        List<Prestamo> prestamosDeLibrosPrestados = prestamoDAO.buscarPrestamosDeLibrosPrestados();

        assertEquals("Debe haber un préstamo en la lista de libros prestados", 1, prestamosDeLibrosPrestados.size());
        assertEquals("El préstamo debe coincidir con el registrado", prestamo, prestamosDeLibrosPrestados.get(0));
    }

    @Test
    public void testListarPrestamos() throws Exception {
        Prestamo prestamo1 = new Prestamo(usuarioEjemplo, libroEjemplo, new Date(), null);
        Libro otroLibro = new Libro("2", "Cien años de soledad", "Gabriel García Márquez");
        otroLibro.setDisponible(true);
        Prestamo prestamo2 = new Prestamo(new Usuario("U002", "Ana López", "ana@email.com"), otroLibro, new Date(), null);

        prestamoDAO.registrarPrestamo(prestamo1);
        prestamoDAO.registrarPrestamo(prestamo2);

        List<Prestamo> prestamos = prestamoDAO.listarPrestamos();
        assertEquals("Debe haber dos préstamos registrados", 2, prestamos.size());
    }
}