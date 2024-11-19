/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import entidades.Usuario;
import java.util.List;

/**
 *
 * @author caarl
 */
public interface UsuarioDAO {
    Usuario guardar(Usuario usuario);
    Usuario buscarPorId(String id);
    List<Usuario> buscarTodos();
    void actualizar(Usuario usuario);
    void eliminar(String id);
}
