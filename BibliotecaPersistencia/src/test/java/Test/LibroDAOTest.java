/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author caarl
 */
import entidades.Libro;
import Dao.LibroDAO;
import Dao.ServicioEvaluacionLibrosDAO;
import Excepciones.ServicioNoDisponibleException;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Interfaces.ILibroDAO;
import Interfaces.IServicioEvaluacionLibros;
import entidades.ReseñaLibro;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.gen5.api.BeforeEach;
import org.mockito.*;

public class LibroDAOTest {

    private ServicioEvaluacionLibrosDAO servicioEvaluacion;
    private Libro libro;
    private LibroDAO libroDAO;

    @Before
    public void setUp() {
        // Crear una instancia real del servicio de evaluación de libros
        servicioEvaluacion = new ServicioEvaluacionLibrosDAO();

        // Crear un libro de prueba con un ISBN real
        libro = new Libro("9780262033848", "Introduction to Algorithms", "Thomas H. Cormen"); // Ejemplo con ISBN real
        libroDAO = new LibroDAO(servicioEvaluacion);
    }

    @Test
    public void testAgregarOActualizarLibro_ConEvaluacionExitosa() {
        try {
            servicioEvaluacion = new ServicioEvaluacionLibrosDAO();
            libroDAO = new LibroDAO(servicioEvaluacion);
            libro = new Libro("9780262033848", "Introduction to Algorithms", "Thomas H. Cormen");
            libroDAO.agregarLibro(libro);
            
            // Verificar que el libro haya sido actualizado correctamente
            assertNotNull("La valoración no puede ser nula.", libro.getValoracion());
            assertNotNull("La reseña no puede ser nula.", libro.getReseña());
            assertTrue("La valoración debería ser positiva.", libro.getValoracion() > 0.0);
            System.out.println("Valoración: " + libro.getValoracion());
            System.out.println("Reseña: " + libro.getReseña());
        } catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarLibro_ServicioNoDisponible() {
        // Creamos un libro con un ISBN inválido para simular la falla del servicio
        servicioEvaluacion = new ServicioEvaluacionLibrosDAO();
            libroDAO = new LibroDAO(servicioEvaluacion);
        Libro libro = new Libro("INVALID_ISBN", "Libro Inexistente", "Autor Desconocido");

        try {
            // Intentamos agregar un libro con ISBN inválido, lo que debería causar una excepción
            libroDAO.agregarLibro(libro);
            fail("Se esperaba una excepción ServicioNoDisponibleException");
        } catch (ServicioNoDisponibleException e) {
            // Verificamos que la excepción haya sido lanzada y su mensaje
            System.out.println(e.getMessage());
            assertEquals("No se pudo conectar al servicio externo: No se encontró información para el ISBN proporcionado.", e.getMessage());
        }
    }
    @Test
    public void testBuscarPorId() throws ServicioNoDisponibleException {
        // Agregar un libro a la lista
        Libro libro = new Libro("9780134685991", "Libro de Prueba", "Autor de Prueba");
        libroDAO.agregarLibro(libro);

        // Buscar por ID
        Libro resultado = libroDAO.buscarPorId("9780134685991");

        // Validar el resultado
        assertNotNull("El libro no debe ser nulo.", resultado);
        assertEquals("El ISBN del libro no coincide.", "9780134685991", resultado.getIsbn());
    }
    @Test
    public void testEliminar() throws ServicioNoDisponibleException {
        // Agregar un libro a la lista
        Libro libro = new Libro("9780134685991", "Effective Java (3rd Edition)", "Joshua Bloch");
        libroDAO.agregarLibro(libro);

        // Eliminar el libro
        libroDAO.eliminar("9780134685991");

        // Validar que el libro ya no existe
        assertNull("El libro debería haber sido eliminado.", libroDAO.buscarPorId("9780134685991"));
    }
    @Test
    public void testBuscarTodos() throws ServicioNoDisponibleException {
        // Agregar libros a la lista
        Libro libro1 = new Libro("9780132350884", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin");
        Libro libro2 = new Libro("9781492054506", "Kubernetes: Up and Running", "Brendan Burns, Joe Beda, Kelsey Hightower");
        libroDAO.agregarLibro(libro1);
        libroDAO.agregarLibro(libro2);

        // Obtener todos los libros
        List<Libro> libros = libroDAO.buscarTodos();

        // Validar el tamaño de la lista
        assertEquals("El número de libros es incorrecto.", 2, libros.size());
    }
    @Test
    public void testBuscarPorAutor() throws ServicioNoDisponibleException {
        // Agregar libros a la lista
        Libro libro1 = new Libro("9781617294945", "Spring in Action", "Craig Walls");
        Libro libro2 = new Libro("9780132350884", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin");
        libroDAO.agregarLibro(libro1);
        libroDAO.agregarLibro(libro2);

        // Buscar libros por autor
        List<Libro> resultado = libroDAO.buscarPorAutor("Craig Walls");

        // Validar los resultados
        assertEquals("Debe haber un libro con ese autor.", 1, resultado.size());
        assertEquals("El autor no coincide.", "Craig Walls", resultado.get(0).getAutor());
    }
    @Test
    public void testBuscarPorTitulo() throws ServicioNoDisponibleException {
        // Agregar libros a la lista
        Libro libro1 = new Libro("9781491918663", "Learning Python (5th Edition)", "Mark Lutz");
        Libro libro2 = new Libro("9780134494166", "Introduction to Programming with C++", "Y. Daniel Liang");
        libroDAO.agregarLibro(libro1);
        libroDAO.agregarLibro(libro2);

        // Buscar libros por título
        List<Libro> resultado = libroDAO.buscarPorTitulo("Learning Python (5th Edition)");

        // Validar los resultados
        assertEquals("Debe haber un libro con ese título.", 1, resultado.size());
        assertEquals("El título no coincide.", "Learning Python (5th Edition)", resultado.get(0).getTitulo());
    }
    @Test
    public void testBuscarPorIdLista() throws ServicioNoDisponibleException {
        // Agregar libros a la lista
        Libro libro1 = new Libro("9781491903995", "Libro 1", "Autor 1");
        Libro libro2 = new Libro("9781491903995", "Libro 2", "Autor 2");
        libroDAO.agregarLibro(libro1);
        libroDAO.agregarLibro(libro2);

        // Buscar libros por ID
        List<Libro> resultado = libroDAO.buscarPorIdLista("9781491903995");

        // Validar los resultados
        assertEquals("Debe haber dos libros con el mismo ID.", 2, resultado.size());
    }
    @Test
    public void testLista() throws ServicioNoDisponibleException {
        // Agregar libros a la lista
        Libro libro1 = new Libro("9780134685991", "Libro 1", "Autor 1");
        Libro libro2 = new Libro("9781491918663", "Libro 2", "Autor 2");
        libroDAO.agregarLibro(libro1);
        libroDAO.agregarLibro(libro2);

        // Obtener la lista
        List<Libro> libros = libroDAO.lista();

        // Validar la lista
        assertEquals("El número de libros es incorrecto.", 2, libros.size());
    }
    
}
