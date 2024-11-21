/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import Presentacion.DlgLibro;
import entidades.Libro;
import idao.ILibroDAO;

import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase implementa los casos de uso de la aplicacion biblioteca
 * @author Ilian Fernando Gastelum Romo
 */
public class Control {
    ILibroDAO libroDAO; // Usa la instancia única
    // Usa la instancia única
    private String nombresColumnasTablaLibros[] = {"ID", "Titulo", "Autor",
        "Disponibilidad"}; 
    /**
    * Constructor.
    */
    public Control() {
        this.libroDAO = ILibroDAO.getInstancia();
        
        
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
                tabla[i][0] = libro.getId();
                tabla[i][1] = libro.getTitulo();
                tabla[i][2] = libro.getAutor();
                tabla[i][3] = libro.isDisponible();
            }
            return new DefaultTableModel(tabla, nombresColumnasTablaLibros);
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
        libro = new Libro(Integer.parseInt(ID));
        try {
            // Obten el libro del catalogo de libros
            bLibro = libroDAO.buscarPorId(Integer.parseInt(ID));

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
            libroDAO.guardar(libro);
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
        libro = new Libro(Integer.parseInt(ID));
        try {
            libro = libroDAO.buscarPorId(Integer.parseInt(ID));
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
            libroDAO.eliminar(Integer.parseInt(ID));
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
        libro = new Libro(Integer.parseInt(ID)); 
        try {
            // Obten el libro del catalogo de libros
            libro = libroDAO.buscarPorId(Integer.parseInt(ID));
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
            libroDAO.actualizar(libro);
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
            
            listaLibros = libroDAO.buscarPorIdLista(Integer.parseInt(id));
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


    
    


}
