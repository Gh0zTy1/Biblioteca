/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;


import Presentacion.DlgUsuario;
import entidades.Usuario;
import idao.IUsuarioDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.util.List;


import javax.swing.JOptionPane;

/**
 * Esta clase implementa los casos de uso de la aplicacion biblioteca para usuarios
 * @author CarlosDamian
 */
public class ControlUsuarios {
    private IUsuarioDAO usuarioDAO;
    private String nombresColumnasTablaUsuarios[] = {"ID", "Nombre", "Correo"};

    /**
     * Constructor.
     */
    public ControlUsuarios() {
        this.usuarioDAO = new IUsuarioDAO();
    }

    /**
     * Genera un objeto de tipo DefaultTableModel a partir de una lista de usuarios.
     *
     * @param listaUsuarios Lista de usuarios a convertir
     * @return Objeto de tipo DefaultTableModel con los atributos de los usuarios.
     */
    public DefaultTableModel usuariosTableModel(List<Usuario> listaUsuarios) {
        Object tabla[][];
        if (listaUsuarios != null) {
            tabla = new Object[listaUsuarios.size()][3];
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario usuario = listaUsuarios.get(i);
                tabla[i][0] = usuario.getId();
                tabla[i][1] = usuario.getNombre();
                tabla[i][2] = usuario.getCorreo();
            }
            return new DefaultTableModel(tabla, nombresColumnasTablaUsuarios);
        }
        return null;
    }

    /**
     * Agrega un usuario al sistema
     * @param frame Ventana sobre la que se despliega el cuadro de dialogo
     * @return Regresa true si se pudo agregar el usuario, false en caso contrario
     */
public boolean agregarUsuario(JFrame frame) {
    Usuario usuario, bUsuario;
    StringBuffer respuesta = new StringBuffer("");
    DlgUsuario dlgUsuario;
    
    // Captura el ID del usuario
    String ID = JOptionPane.showInputDialog(frame, "ID del usuario:",
            "Agregar usuario", JOptionPane.QUESTION_MESSAGE);
    // Si el usuario presionó el botón Cancelar
    if (ID == null) return false;
    
    // Crea un objeto Usuario con solo el ID
    usuario = new Usuario(ID, "", "");
    
    try {
        // Obtén el usuario de la base de datos
        bUsuario = usuarioDAO.buscarPorId(ID);
    } catch (Exception e) {
        // Si ocurrió un error al leer de la base de datos,
        // despliega mensaje de error
        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    // Si el usuario existe, despliega sus datos
    if (bUsuario != null) {
        dlgUsuario = new DlgUsuario(frame,
                "El usuario ya existe en el sistema",
                true, bUsuario, 3, respuesta);
        return false;
    }
    
    // Si el usuario no existe captura los datos del nuevo usuario
    dlgUsuario = new DlgUsuario(frame, "Captura Datos Usuario", true,
            usuario, 0, respuesta);
    
    // Si el usuario presionó el botón Cancelar
    if (respuesta.substring(0).equals("Cancelar")) return false;
    
    try {
        usuarioDAO.guardar(usuario);
    } catch (Exception e) {
        // Si ocurrió un error al escribir en la base de datos,
        // despliega mensaje de error
        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    return true;
}

    /**
     * Elimina un usuario del sistema
     * @param frame Ventana sobre la que se despliega el cuadro de dialogo
     * @return Regresa true si se pudo eliminar el usuario, false en caso contrario
     */
    public boolean eliminarUsuario(JFrame frame) {
        String id = JOptionPane.showInputDialog(frame, "ID del usuario:",
                "Eliminar usuario", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return false;

        try {
            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                JOptionPane.showMessageDialog(frame, "El usuario no existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            int confirmacion = JOptionPane.showConfirmDialog(frame,
                    "¿Está seguro de eliminar al usuario " + usuario.getNombre() + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                usuarioDAO.eliminar(id);
                return true;
            }
            return false;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Actualiza un usuario del sistema
     * @param frame Ventana sobre la que se despliega el cuadro de dialogo
     * @return Regresa true si se pudo actualizar el usuario, false en caso contrario
     */
    public boolean actualizarUsuario(JFrame frame) {
        String id = JOptionPane.showInputDialog(frame, "ID del usuario:",
                "Actualizar usuario", JOptionPane.QUESTION_MESSAGE);
        if (id == null) return false;

        try {
            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                JOptionPane.showMessageDialog(frame, "El usuario no existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String nombre = (String) JOptionPane.showInputDialog(frame, "Nuevo nombre del usuario:",
                    "Actualizar usuario", JOptionPane.QUESTION_MESSAGE, null, null,
                    usuario.getNombre());
            if (nombre == null) return false;

            String correo = (String) JOptionPane.showInputDialog(frame, "Nuevo correo del usuario:",
                    "Actualizar usuario", JOptionPane.QUESTION_MESSAGE, null, null,
                    usuario.getCorreo());
            if (correo == null) return false;

            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuarioDAO.actualizar(usuario);
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Regresa un objeto Tabla con todos los usuarios
     * @param frame Ventana sobre la que se despliega el mensaje de error
     * @return Objeto Tabla con todos los usuarios, null si hay un error
     */
    public Tabla getTablaUsuarios(JFrame frame) {
        try {
            List<Usuario> listaUsuarios = usuarioDAO.buscarTodos();
            return new Tabla("Lista de Usuarios",
                    usuariosTableModel(listaUsuarios));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Busca y muestra usuarios por nombre
     * @param frame Ventana sobre la que se despliega el cuadro de dialogo
     * @return Objeto Tabla con los usuarios encontrados, null si hay un error
     */
    public Tabla getTablaUsuariosNombre(JFrame frame) {
        String nombre = JOptionPane.showInputDialog(frame, "Nombre a buscar:",
                "Buscar usuario", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) return null;

        try {
            List<Usuario> listaUsuarios = usuarioDAO.buscarTodos();
            List<Usuario> usuariosEncontrados = new ArrayList<>();
            
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    usuariosEncontrados.add(usuario);
                }
            }

            return new Tabla("Usuarios con nombre: " + nombre,
                    usuariosTableModel(usuariosEncontrados));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}