/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.awt.Dimension;
import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ilian Fernando Gastelum Romo
 */
public class DlgPrestamo extends javax.swing.JDialog {
    private Prestamo prestamo;
    private DefaultComboBoxModel listaLibros;
    private DefaultComboBoxModel listaUsuarios;
    private int operacion;
    private StringBuffer respuesta;
    /**
    * Constructor que establece las características del cuadro de diálogo
    * y la operación a realizar con él
    * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
    * @param title Título del cuadro de diálogo
    * @param modal true si permite acceder fuera de los límites del cuadro
    * de diálogo, false en caso contrario
    * @param listaLibros Lista de libros del catalogo de libros.
    * @param listaUsuarios Lista de usuarios.
    * @param prestamo Prestamo que contiene la información del libro y usuario. 
    * a agregar o eliminar del inventario de libros.
    * @param operacion Operación a realizar en el cuadro de diálogo:
    * AGREGAR = 0, ACTUALIZAR = 1, ELIMINAR = 2, DESPLEGAR = 3;
    * @param respuesta Boton presionado al salir de los cuadros de
    * diálogos: ACEPTAR = "Aceptar", CANCELAR = "Cancelar".
    */ 
    public DlgPrestamo(java.awt.Frame parent, boolean modal, Prestamo prestamo, 
            DefaultComboBoxModel listaLibros,DefaultComboBoxModel listaUsuarios, int operacion, StringBuffer respuesta, String title) {
        super(parent,title, modal);
        this.prestamo=prestamo;
        this.operacion=operacion;
        this.listaLibros=listaLibros;
        this.listaUsuarios=listaUsuarios;
        this.respuesta=respuesta;
        initComponents();
        // Modifica el título del botón botonAceptar y establece si los
        // botones botonRestaurar y botonCancelar estarán habilitados.
        // Si la operación es agregar
        if(operacion == 0)
        botonAceptar.setText("Guardar");
        // Si la operación es actualizar
        else if(operacion == 1)
        botonAceptar.setText("Actualizar");
        // Si la operación es eliminar
        else if(operacion == 2) {
        botonAceptar.setText("Eliminar");
        botonRestaurar.setEnabled(false);
        }
        // Si la operación es desplegar
        else if(operacion == 3) {
        botonAceptar.setText("Continuar");
        botonRestaurar.setEnabled(false);
        botonCancelar.setEnabled(false);
        }
        cajaCombinadaUsuario.setSelectedItem(prestamo.getUsuario()); 
        cajaCombinadaLibro.setSelectedItem(prestamo.getLibro()); 
        // Si la operación es de actualizar, eliminar o desplegar,
        if((operacion == 2) ||
        (operacion == 1) ||
        (operacion == 3)) {
        // despliega el catalogo
        cajaCombinadaLibro.setSelectedItem(prestamo.getLibro()); 
        cajaCombinadaUsuario.setSelectedItem(prestamo.getUsuario()); 
        }

        // Si la operación es de eliminar o desplegar
        if((operacion == 2) ||
        (operacion == 3)) {
        // hace los campos de texto de sólo lectura
        
        //cajaCombinadaUsuario.setEnabled(false); 
        //cajaCombinadaLibro.setEnabled(false);
        
        }

        // Establece el valor por omisión para respuesta, por si se cierra el
        // cuadro de diálogo presionando el botón cerrar o el botón cancelar
        respuesta.append("Cancelar");
        // centra el cuadro de dialogo sobre la ventana de la aplicación
        centraCuadroDialogo(parent);

        // Muestra el cuadro de diálogo
        setVisible(true);
        
    }
    /**
    * Este método centra el cuadro de dialogo sobre la ventana de la
    * aplicación.
    * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
    */
    private void centraCuadroDialogo(java.awt.Frame parent) {
    // Obtiene el tamaño y posición de la ventana de la aplicación
    Dimension frameSize = parent.getSize();
    Point loc = parent.getLocation();
    // Obtiene el tamaño del cuadro de diálogo
    Dimension dlgSize = getPreferredSize();

    // Centra el cuadro de diálogo sobre la ventana padre
    setLocation( (frameSize.width - dlgSize.width) / 2 + loc.x,
    (frameSize.height - dlgSize.height) / 2 + loc.y);
    } 
    
    
    // Método para devolver el libro y agregar una valoración
public void devolverLibro() {
    if (prestamo.getFechaDevolucion() == null) {
        prestamo.setFechaDevolucion(new Date());  // Marca la fecha de devolución
        mostrarFormularioValoracion();  // Muestra el cuadro de valoración
    }
}

// Método para mostrar el formulario de valoración
private void mostrarFormularioValoracion() {
    // Aquí podrías mostrar un cuadro de diálogo o formulario
    // que permita al usuario ingresar una valoración
    int valoracionUsuario = pedirValoracion(); // Método ficticio para obtener la valoración
    String comentarioUsuario = pedirComentario(); // Método ficticio para obtener el comentario

    prestamo.setValoracion(valoracionUsuario);  // Guarda la valoración
    prestamo.setComentario(comentarioUsuario);  // Guarda el comentario
}


public int pedirValoracion() {
        // Pedir al usuario que seleccione una valoración entre 1 y 5
        String[] opciones = {"1", "2", "3", "4", "5"};
        String input = (String) JOptionPane.showInputDialog(
            null,  // Ventana padre
            "Seleccione una valoración (1 a 5)",  // Mensaje
            "Valoración del libro",  // Título de la ventana
            JOptionPane.QUESTION_MESSAGE,  // Tipo de mensaje
            null,  // Icono personalizado (usamos null para que no tenga uno)
            opciones,  // Opciones disponibles para el usuario
            opciones[0]  // Valor por defecto (opción 1)
        );

        // Convertir la selección a un número entero
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Si el usuario no selecciona una opción válida, retornar 0 o un valor predeterminado
            return 0;
        }
    }

    // Método para pedir un comentario (comentario de texto libre)
    public String pedirComentario() {
        // Pedir al usuario que ingrese un comentario
        String comentario = JOptionPane.showInputDialog(
            null,  // Ventana padre
            "Escribe un comentario sobre el libro",  // Mensaje
            "Comentario",  // Título de la ventana
            JOptionPane.QUESTION_MESSAGE  // Tipo de mensaje
        );

        return comentario != null ? comentario : "";  // Retorna el comentario o una cadena vacía si es nulo
    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cajaCombinadaUsuario = new javax.swing.JComboBox<>();
        botonAceptar = new javax.swing.JButton();
        botonRestaurar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cajaCombinadaLibro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Usuario");

        cajaCombinadaUsuario.setModel(listaUsuarios);
        cajaCombinadaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaCombinadaUsuarioActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonRestaurar.setText("Restaurar");
        botonRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRestaurarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Libro");

        cajaCombinadaLibro.setModel(listaLibros);
        cajaCombinadaLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaCombinadaLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(botonRestaurar)
                .addGap(91, 91, 91)
                .addComponent(botonCancelar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cajaCombinadaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaCombinadaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cajaCombinadaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cajaCombinadaLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar)
                    .addComponent(botonCancelar)
                    .addComponent(botonRestaurar))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaCombinadaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaCombinadaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaCombinadaUsuarioActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // Si la operación es Agregar o Actualizar
        if((operacion == 0) ||
            (operacion == 1)) {
            // Agrega el tiempo de prestamo.
            prestamo.setLibro((Libro) cajaCombinadaLibro.getSelectedItem());
            prestamo.setUsuario((Usuario) cajaCombinadaUsuario.getSelectedItem());
            prestamo.setFechaPrestamo(new Date());
            
        }
        // Borra el contenido de respuesta
        respuesta.delete(0, respuesta.length());
        // Establece que se presionó el botón botonAceptar
        respuesta.append("Aceptar");
        // Destruye el cuadro de díalogo
        dispose();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRestaurarActionPerformed
        // Restaura el contenido de los campos de texto a su valor original
        // Si la operación es Agregar
        if(operacion == 0) {
            cajaCombinadaUsuario.setSelectedItem(0);
            cajaCombinadaLibro.setSelectedItem(0);
        }

        // Si la operación es Actualizar
        if(operacion == 1) {
            cajaCombinadaUsuario.setSelectedItem(prestamo.getUsuario()); 
            cajaCombinadaLibro.setSelectedItem(prestamo.getLibro()); 
        }
    }//GEN-LAST:event_botonRestaurarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // Destruye el cuadro de díalogo
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void cajaCombinadaLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaCombinadaLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaCombinadaLibroActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonRestaurar;
    private javax.swing.JComboBox<String> cajaCombinadaLibro;
    private javax.swing.JComboBox<String> cajaCombinadaUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
