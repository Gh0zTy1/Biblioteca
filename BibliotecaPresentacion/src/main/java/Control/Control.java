/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Presentacion.DlgLibro;
import Presentacion.DlgPrestamo;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import Dao.LibroDAO;
import Dao.PrestamoDAO;
import Dao.ServicioEvaluacionLibrosDAO;
import Dao.UsuarioDAO;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa los casos de uso de la aplicacion biblioteca
 * @author Ilian Fernando Gastelum Romo
 */
public class Control {
    LibroDAO libroDAO; // Usa la instancia 
    PrestamoDAO prestamoDAO;
    UsuarioDAO usuarioDAO;
    ServicioEvaluacionLibrosDAO servicioEvaluacion;
    // Usa la instancia únicaúnica
    private String nombresColumnasTablaLibros[] = {"ID", "Titulo", "Autor",
        "Disponibilidad","Valoracion","Reseña"}; 
    private String nombresColumnasTablaPrestamosLibros[] = {"Usuario", "Libro", "Fecha"};
    private String nombresColumnasTablaInventarioLibros[] = {"Libro", 
        "Disponibilidad",}; 
    /**
    * Constructor.
    */
    public Control() {
        this.servicioEvaluacion = new ServicioEvaluacionLibrosDAO();
        this.libroDAO = LibroDAO.getInstancia(servicioEvaluacion);
        this.usuarioDAO = UsuarioDAO.getInstancia();
        this.prestamoDAO = PrestamoDAO.getInstancia();
        
        
    } 
    
    /**
     * convierte una lista de libros a un objeto de tipo DefaultComboBoxModel que se emplea
     * para desplegar los atributos de los libros de la lista. 
     * @param listaLibros Lista de libros a convertir
     * @return Objeto de tipo DefaultComboBoxMode con los libros
     */
    public DefaultComboBoxModel librosComboBoxModel(List<Libro> listaLibros){
        DefaultComboBoxModel<Libro> defaultComboBoxModel = new DefaultComboBoxModel<>();
        if (listaLibros != null) {
            // Para cada elemento de la Lista
            for (int i = 0; i < listaLibros.size(); i++){
                // Agregalo a la instancia de la clase DefaultComboBoxModel 
                defaultComboBoxModel.addElement(listaLibros.get(i)); 
            }
            return defaultComboBoxModel;
        }
        return null;
    }
    /**
     * convierte una lista de usuarios a un objeto de tipo DefaultComboBoxModel 
     * que se emplea para desplegar los atributos de los usuarios de la lista. 
     * @param listaUsuarios Lista de usuarios a convertir
     * @return Objeto de tipo DefaultComboBoxMode con los usuarios
     */
    DefaultComboBoxModel usuariosComboBoxModel(List<Usuario>listaUsuarios) {
        DefaultComboBoxModel<Usuario> defaultComboBoxModel = new DefaultComboBoxModel<>();
        if (listaUsuarios != null) {
            // Para cada elemento de la Lista
            for (int i = 0; i < listaUsuarios.size(); i++){
                // Agregalo a la instancia de la clase DefaultComboBoxModel 
                defaultComboBoxModel.addElement(listaUsuarios.get(i)); 
            }
            return defaultComboBoxModel;
        }
        return null;
    }
    public DefaultTableModel inventarioLibrosTableModel(List<Prestamo>listaInventarioLibros) {
        Object tabla[][];
        if (listaInventarioLibros != null) {
            tabla = new Object[listaInventarioLibros.size()][3];
            for (int i = 0; i < listaInventarioLibros.size(); i++) {
                // Obten un usuario de la lista de usuarios
                Prestamo prestamos = listaInventarioLibros.get(i);
                // Almacena sus atributos en la fila del arreglo
                tabla[i][0] = prestamos.getLibro();
                tabla[i][2] = prestamos.getLibro().getDisponible();
            }
            return new DefaultTableModel(tabla, nombresColumnasTablaInventarioLibros);
        }
        return null;
    }
    /**
    * Genera un objeto de tipo DefaultTableModel a partir de una lista de
    * libros.
    *
    * @param listaLibros Lista de libros a convertir
    * @return Objeto de tipo DefaultTableModel con los atributos de los
    * libros.
    */ 
    public DefaultTableModel librosTableModel(List<Libro> listaLibros) {
        Object tabla[][]; 
        if (listaLibros != null) {
            tabla = new Object[listaLibros.size()][6];
            for (int i = 0; i < listaLibros.size(); i++) {
                Libro libro = listaLibros.get(i);
                // Almacena sus atributos en la fila del arreglo
                tabla[i][0] = libro.getIsbn();
                tabla[i][1] = libro.getTitulo();
                tabla[i][2] = libro.getAutor();
                tabla[i][3] = libro.getDisponible();
                tabla[i][4] = libro.getValoracion();
                tabla[i][5] = libro.getReseña();
            }
            return new DefaultTableModel(tabla, nombresColumnasTablaLibros);
        }
        return null;
    }
    
    public DefaultTableModel prestamoLibrosTableModel(List<Prestamo>listaPrestamoLibros){
        Object tabla[][];
        if (listaPrestamoLibros != null) {
            tabla = new Object[listaPrestamoLibros.size()][4];
            for (int i = 0; i < listaPrestamoLibros.size(); i++) {
                // Obten un usuario de la lista de usuarios
                Prestamo prestamo = listaPrestamoLibros.get(i);
                // Almacena sus atributos en la fila del arreglo
                tabla[i][0] = prestamo.getUsuario();
                tabla[i][1] = prestamo.getLibro();
                tabla[i][2] = prestamo.getFechaPrestamo();
            }
            return new DefaultTableModel(tabla, nombresColumnasTablaPrestamosLibros);
        }
        return null;
    }
    /**
    * Agrega un libro al catalogo de libros
    * @param frame Ventana sobre la que se despliega el cuadro de dialogo para
    * capturar los datos del libro
    * @return Regresa true si se pudo agregar el libro, false en caso
    * contrario
    */ 
    public boolean agregarLibro(JFrame frame){
        Libro libro, bLibro;
        StringBuffer respuesta = new StringBuffer("");
        DlgLibro dlgLibro;
        // Captura la isbn del libro
        String ID = JOptionPane.showInputDialog(frame, "ID del libro:",
        "Agregar libro",JOptionPane.QUESTION_MESSAGE); 
        // Si el usuario presiono el boton Cancelar
        if(ID == null) return false;
        // Crea un objeto Libro con solo la isbn
        libro = new Libro(ID);
        try {
            // Obten el libro del catalogo de libros
            bLibro = libroDAO.buscarPorId(ID);

        } 
        catch (Exception e) {
            // Si ocurrio un error al leer del catalogo de libros,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        // Si el libro existe, despliega sus datos
        if(bLibro != null) {
            dlgLibro = new DlgLibro(frame,
            "El libro ya esta en el catalogo",
            true, bLibro,3, respuesta);

            return false;
        }
        // Si el libro no existe captura los datos de el nuevo libro
        dlgLibro = new DlgLibro(frame, "Captura Datos Libros", true,
        libro,0, respuesta);
        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals("Cancelar")) return false;
        try {
            libroDAO.agregarLibro(libro);
        }
        catch(Exception e) {
            // Si ocurrio un error al escribir al catalogo de libros,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; 
    }
    /**
    * Elimina un libro del catalogo de libros
    * @param frame Ventana sobre la que se despliega el cuadro de dialogo para
    * desplegar los datos de el libro
    * @return Regresa true si se pudo eliminar el libro, false en caso
    * contrario
    */ 
    public boolean eliminarLibro(JFrame frame){
        Libro libro;
        StringBuffer respuesta = new StringBuffer();
        DlgLibro dlgLibro;
        String ID = JOptionPane.showInputDialog(frame, "ID del libro:",
        "Eliminar libro",
        JOptionPane.QUESTION_MESSAGE);
        if(ID == null) return false;
        libro = new Libro(ID);
        try {
            libro = libroDAO.buscarPorId(ID);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        if(libro == null) {
            JOptionPane.showMessageDialog(frame, "El libro no existe",
            "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        dlgLibro = new DlgLibro(frame, "Libro a borrar", true, libro,
        2, respuesta);
        if(respuesta.substring(0).equals("Cancelar")) return false;
        try {
            libroDAO.eliminar(ID);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    } 
    /**
    * Actualiza un libro del catalogo de libros
    * @param frame Ventana sobre la que se despliega el cuadro de dialogo para
    * editar los datos del libro
    * @return Regresa true si se pudo actualizar el libro, false en caso
    * contrario
    */
    public boolean actualizarLibro(JFrame frame){
        Libro libro;
        StringBuffer respuesta = new StringBuffer("");
        DlgLibro dlgLibro;
        // Captura la clave del libro
        String ID = JOptionPane.showInputDialog(frame, "ID del libro:",
        "Actualizar libro",JOptionPane.QUESTION_MESSAGE); 
        // Si el usuario presiono el boton Cancelar
        if(ID == null) return false;
        // Crea un objeto Libro con solo la isbn
        libro = new Libro(ID); 
        try {
            // Obten el libro del catalogo de libros
            libro = libroDAO.buscarPorId(ID);
        }
        catch (Exception e) {
            // Si ocurrio un error al leer del catalogo de libros,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        // Si el libro no existe, despliega un mensaje de error
        if(libro == null) {
            JOptionPane.showMessageDialog(frame, "El libro no existe", "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Si el libro existe, edita los datos del libro
        dlgLibro = new DlgLibro(frame, "Edita Datos Libro", true, libro,
                1, respuesta);
        // Si el usuario presiono el boton Cancelar 
        if (respuesta.substring(0).equals("Cancelar")) return false;
        // Actualiza el libro del catalogo de libros
        try {
            libroDAO.actualizarLibro(libro);
        }
        catch(Exception e) {
            // Si ocurrio un error al escribir al catalogo de libros,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; 
    }
    /**
    * Regresa un objeto Tabla con todos los libros
    * @param frame Ventana sobre la que se despliega el mensaje de error
    * @return Objeto Tabla con todos los libros, null si hay un error
    */
    public Tabla getTablaLibros(JFrame frame){
        List<Libro> listaLibros;
        try {
            // Obtiene la lista de libros
            listaLibros = libroDAO.buscarTodos();
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Regresa el objeto Tabla con todos los libros
       
        return new Tabla("Lista de Libros",
        librosTableModel(listaLibros));  
        
    }
    public Tabla getTablaLibrosAutor(JFrame frame){
        List<Libro> listaLibros;
        // Captura la isbn del libro
        String autor = JOptionPane.showInputDialog(frame, "Nombre del autor:",
        "Buscar libro",JOptionPane.QUESTION_MESSAGE); 
        // Si el usuario presiono el boton Cancelar
        
        try {
            // Obtiene la lista de libros
            listaLibros = libroDAO.buscarPorAutor(autor);
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Regresa el objeto Tabla con todos los libros
        return new Tabla("Lista de Libros del autor "+autor,
        librosTableModel(listaLibros));  
        
    }
    public Tabla getTablaLibrosID(JFrame frame){
        
        List<Libro> listaLibros;
        // Captura la isbn del libro
        String id = JOptionPane.showInputDialog(frame, "ID del libro:",
        "Buscar libro",JOptionPane.QUESTION_MESSAGE); 
        // Si el usuario presiono el boton Cancelar
        
        try {
            // Obtiene la lista de libros
            
            listaLibros = libroDAO.buscarPorIdLista(id);
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Regresa el objeto Tabla con todos los libros
        return new Tabla("Lista de Libros del id "+id,
        librosTableModel(listaLibros));  
        
    }
    
    public Tabla getTablaLibrosTitulo(JFrame frame){
        List<Libro> listaLibros;
        // Captura la isbn del libro
        String titulo = JOptionPane.showInputDialog(frame, "Titulo del libro:",
        "Buscar libro",JOptionPane.QUESTION_MESSAGE); 
        // Si el usuario presiono el boton Cancelar
        
        try {
            // Obtiene la lista de libros
            listaLibros = libroDAO.buscarPorTitulo(titulo);
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Regresa el objeto Tabla con todos los libros
        return new Tabla("Lista de Libros con el titulo "+titulo,
        librosTableModel(listaLibros));  
        
    }
    public Tabla getTablaPrestamosLibro(JFrame frame){
        List<Prestamo> listaPrestamosLibro;
        Libro libro;
        String id = JOptionPane.showInputDialog(frame, "id del libro: ",
        "Prestamos",JOptionPane.QUESTION_MESSAGE); 
        libro = new Libro(id);
        try {
            listaPrestamosLibro = prestamoDAO.consultarPrestamos(libro);
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Tabla("Prestamos por libro",
        prestamoLibrosTableModel(listaPrestamosLibro));  
        
    }
    
    public boolean prestarLibro(JFrame frame){
        Prestamo prestamo;
        StringBuffer respuesta = new StringBuffer("");
        DlgPrestamo dlgPrestamo;
        List<Libro> listaLibros;
        List<Usuario> listaUsuarios;
        DefaultComboBoxModel<Libro> LibrosComboBoxModel; 
        DefaultComboBoxModel<Usuario> UsuariosComboBoxModel; 
        
        try {
            listaLibros = libroDAO.lista();
            
            listaUsuarios = usuarioDAO.lista();
            
        } 
        catch (NullPointerException e) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        LibrosComboBoxModel = librosComboBoxModel(listaLibros); 
        UsuariosComboBoxModel = usuariosComboBoxModel(listaUsuarios); 
        
        prestamo = new Prestamo((Usuario)UsuariosComboBoxModel.getSelectedItem(), (Libro)LibrosComboBoxModel.getSelectedItem());
        System.out.println("prestamo "+prestamo);
        System.out.println("Libro "+prestamo.getLibro());
        System.out.println("usuario "+prestamo.getUsuario());
        dlgPrestamo = new DlgPrestamo(frame, true, prestamo, LibrosComboBoxModel, 
                UsuariosComboBoxModel, 0, respuesta, "Captura prestamos"); 
        
        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals("Cancelar")) return false;
        try {
            
            prestamoDAO.registrarPrestamo(prestamo);
        }
        catch(Exception e) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; 
    }
    public boolean devolverLibro(JFrame frame){
        Prestamo prestamo;
        StringBuffer respuesta = new StringBuffer("");
        DlgPrestamo dlgPrestamo;
        List<Libro> listaLibros;
        List<Usuario> listaUsuarios;
        DefaultComboBoxModel<Libro> LibrosComboBoxModel; 
        DefaultComboBoxModel<Usuario> UsuariosComboBoxModel; 
        
        try {
            listaLibros = libroDAO.lista();
            listaUsuarios = usuarioDAO.lista();
        } 
        catch (Exception e) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        LibrosComboBoxModel = librosComboBoxModel(listaLibros); 
        UsuariosComboBoxModel = usuariosComboBoxModel(listaUsuarios); 
        
        prestamo = new Prestamo((Usuario)UsuariosComboBoxModel.getSelectedItem(), (Libro)LibrosComboBoxModel.getSelectedItem());
        
        dlgPrestamo = new DlgPrestamo(frame, true, prestamo, LibrosComboBoxModel, 
                UsuariosComboBoxModel, 2, respuesta, "devolver libro"); 
        
        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals("Cancelar")) return false;
        try {
            prestamoDAO.devolverPrestamo(prestamo);
        }
        catch(Exception e) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; 
    }
        public Tabla getTablaLibrosPrestados(JFrame frame){
            List<Prestamo> listaLibrosPrestados;
            try {
                listaLibrosPrestados = prestamoDAO.buscarPrestamosDeLibrosPrestados();
            }
            catch (Exception e) {
                // Si ocurrio un error al obtener la lista de la base de datos,
                // despliega mensaje de error
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new Tabla("Lista de Libros prestados",
            inventarioLibrosTableModel(listaLibrosPrestados));  
        }
        public Tabla getTablaPrestamosLibros(JFrame frame){
        List<Prestamo> listaPrestamos;
        try {
            listaPrestamos = prestamoDAO.consultarPrestamos();
        }
        catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Tabla("Prestamos",
        prestamoLibrosTableModel(listaPrestamos));  
        
    }
        public Tabla getTablaLibrosDisponibles(JFrame frame){
            List<Prestamo> listaLibrosDisponibles;
            try {
                listaLibrosDisponibles = prestamoDAO.buscarPrestamosDeLibrosPrestados();
            }
            catch (Exception e) {
                // Si ocurrio un error al obtener la lista de la base de datos,
                // despliega mensaje de error
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new Tabla("Lista de Libros disponibles",
            inventarioLibrosTableModel(listaLibrosDisponibles));  
        }


    
    


}
