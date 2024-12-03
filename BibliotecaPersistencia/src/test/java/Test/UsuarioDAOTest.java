/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import entidades.Usuario;
import Dao.UsuarioDAO;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import Interfaces.IUsuarioDAO;


/**
 *
 * @author caarl
 */
public class UsuarioDAOTest {
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioEjemplo;

    @Before
    public void setUp() {
        usuarioDAO = UsuarioDAO.getInstancia();
        usuarioDAO.buscarTodos().forEach(usuario -> usuarioDAO.eliminar(usuario.getId()));
        usuarioEjemplo = new Usuario("U001", "Juan Pérez", "juan@email.com");
    }

    @Test
    public void testGuardarUsuario() {
        Usuario usuarioGuardado = usuarioDAO.guardar(usuarioEjemplo);
        
        assertNotNull("El usuario guardado no debe ser null", usuarioGuardado);
        assertEquals("El ID del usuario debe coincidir", usuarioEjemplo.getId(), usuarioGuardado.getId());
        assertEquals("El nombre del usuario debe coincidir", usuarioEjemplo.getNombre(), usuarioGuardado.getNombre());
    }

    @Test
    public void testBuscarPorId() {
        usuarioDAO.guardar(usuarioEjemplo);
        
        Usuario usuarioEncontrado = usuarioDAO.buscarPorId("U001");
        
        assertNotNull("El usuario debe ser encontrado", usuarioEncontrado);
        assertEquals("El nombre del usuario debe coincidir", "Juan Pérez", usuarioEncontrado.getNombre());
    }

    @Test
    public void testBuscarPorIdNoExistente() {
        Usuario usuarioNoExistente = usuarioDAO.buscarPorId("U999");
        assertNull("Debe retornar null para un ID no existente", usuarioNoExistente);
    }

    @Test
    public void testActualizarUsuario() {
        usuarioDAO.guardar(usuarioEjemplo);
        
        usuarioEjemplo.setNombre("Juan Pablo Pérez");
        usuarioDAO.actualizar(usuarioEjemplo);
        
        Usuario usuarioActualizado = usuarioDAO.buscarPorId("U001");
        assertEquals("El nombre debe estar actualizado", "Juan Pablo Pérez", usuarioActualizado.getNombre());
    }

    @Test
    public void testEliminarUsuario() {
        usuarioDAO.guardar(usuarioEjemplo);
        usuarioDAO.eliminar("U001");
        
        Usuario usuarioEliminado = usuarioDAO.buscarPorId("U001");
        assertNull("El usuario debe haber sido eliminado", usuarioEliminado);
    }

    @Test
    public void testBuscarTodos() {
        usuarioDAO.guardar(usuarioEjemplo);
        usuarioDAO.guardar(new Usuario("U002", "María López", "maria@email.com"));
        
        List<Usuario> todosLosUsuarios = usuarioDAO.buscarTodos();
        
        assertEquals("Deben haber 2 usuarios en total", 2, todosLosUsuarios.size());
    }
}