/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idao;

import daos.UsuarioDAO;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caarl
 */
public class IUsuarioDAO implements UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Usuario buscarPorId(String id) {
        return usuarios.stream()
                      .filter(u -> u.getId().equals(id))
                      .findFirst()
                      .orElse(null);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public void actualizar(Usuario usuario) {
        int indice = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                indice = i;
                break;
            }
        }
        if (indice != -1) {
            usuarios.set(indice, usuario);
        }
    }

    @Override
    public void eliminar(String id) {
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}