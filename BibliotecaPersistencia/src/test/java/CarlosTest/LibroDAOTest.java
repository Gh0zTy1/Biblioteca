/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarlosTest;

/**
 *
 * @author caarl
 */
import daos.LibroDAO;
import entidades.Libro;
import idao.ILibroDAO;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LibroDAOTest {
    private LibroDAO libroDAO;
    private Libro libroEjemplo;

    @Before
    public void setUp() {
        libroDAO = new ILibroDAO();
        libroEjemplo = new Libro("L001", "Don Quijote", "Miguel de Cervantes");
    }

    @Test
    public void testGuardarLibro() {
        Libro libroGuardado = libroDAO.guardar(libroEjemplo);
        
        assertNotNull("El libro guardado no debe ser null", libroGuardado);
        assertEquals("El ID del libro debe coincidir", libroEjemplo.getId(), libroGuardado.getId());
        assertEquals("El título del libro debe coincidir", libroEjemplo.getTitulo(), libroGuardado.getTitulo());
        assertEquals("El autor del libro debe coincidir", libroEjemplo.getAutor(), libroGuardado.getAutor());
    }

    @Test
    public void testBuscarPorId() {
        libroDAO.guardar(libroEjemplo);
        
        Libro libroEncontrado = libroDAO.buscarPorId("L001");
        
        assertNotNull("El libro debe ser encontrado", libroEncontrado);
        assertEquals("El título del libro debe coincidir", "Don Quijote", libroEncontrado.getTitulo());
    }

    @Test
    public void testBuscarPorIdNoExistente() {
        Libro libroNoExistente = libroDAO.buscarPorId("L999");
        assertNull("Debe retornar null para un ID no existente", libroNoExistente);
    }

    @Test
    public void testBuscarPorTitulo() {
        libroDAO.guardar(libroEjemplo);
        libroDAO.guardar(new Libro("L002", "Don Quijote Parte II", "Miguel de Cervantes"));
        
        List<Libro> librosEncontrados = libroDAO.buscarPorTitulo("Don Quijote");
        
        assertEquals("Debe encontrar 2 libros", 2, librosEncontrados.size());
        assertTrue("Todos los títulos deben contener 'Don Quijote'", 
            librosEncontrados.stream().allMatch(l -> l.getTitulo().contains("Don Quijote")));
    }

    @Test
    public void testBuscarPorAutor() {
        libroDAO.guardar(libroEjemplo);
        
        List<Libro> librosEncontrados = libroDAO.buscarPorAutor("Cervantes");
        
        assertFalse("La lista no debe estar vacía", librosEncontrados.isEmpty());
        assertEquals("Debe encontrar 1 libro", 1, librosEncontrados.size());
    }

    @Test
    public void testActualizarLibro() {
        libroDAO.guardar(libroEjemplo);
        
        libroEjemplo.setTitulo("Don Quijote de la Mancha");
        libroDAO.actualizar(libroEjemplo);
        
        Libro libroActualizado = libroDAO.buscarPorId("L001");
        assertEquals("El título debe estar actualizado", "Don Quijote de la Mancha", libroActualizado.getTitulo());
    }

    @Test
    public void testEliminarLibro() {
        libroDAO.guardar(libroEjemplo);
        libroDAO.eliminar("L001");
        
        Libro libroEliminado = libroDAO.buscarPorId("L001");
        assertNull("El libro debe haber sido eliminado", libroEliminado);
    }

    @Test
    public void testBuscarTodos() {
        libroDAO.guardar(libroEjemplo);
        libroDAO.guardar(new Libro("L002", "Cien años de soledad", "Gabriel García Márquez"));
        
        List<Libro> todosLosLibros = libroDAO.buscarTodos();
        
        assertEquals("Deben haber 2 libros en total", 2, todosLosLibros.size());
    }
}