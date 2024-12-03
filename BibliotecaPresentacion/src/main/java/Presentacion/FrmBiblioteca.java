/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;


import Control.Control;
import Control.ControlUsuarios;
import Control.Tabla;
import Dao.LibroDAO;
import Dao.ServicioEvaluacionLibrosDAO;
import Interfaces.IServicioEvaluacionLibros;
import java.awt.Dimension;
import java.awt.Toolkit;



/**
 * Esta es clase es la clase principal de la aplicación biblioteca. También
 * es la ventana principal de la aplicacion 
 * @author Ilian Fernando Gastelum Romo
 */
public class FrmBiblioteca extends javax.swing.JFrame {
    
    
    /**
     * Creates new form FrmBiblioteca
     */
    public FrmBiblioteca() {
        initComponents();
        
    }
    public void despliegaTabla(Tabla tabla) {
    // Crea la tabla a partir del modelo de la tabla con los valores
    // de los titulos de las columnas y los valores de las celdas
    jtabla = new javax.swing.JTable(tabla.getModeloTabla());
    // Establece el título de la tabla
    tituloTabla.setText(tabla.getTitulo());
    // Hace que el control del tamaño de la tabla y la porción visible
    // lo tenga la barra de deslizamiento y no la tabla
    jtabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jtabla.setAutoscrolls(false);
    // Hace visible la tabla dentro del panel con barras de
    // deslizamiento
    jScrollPane1.setViewportView(jtabla);
    } 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloTabla = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        menuCatalogos = new javax.swing.JMenu();
        menuCatalogoLibros = new javax.swing.JMenu();
        opcionMenuAgregarLibro = new javax.swing.JMenuItem();
        opcionMenuActualizarLibro = new javax.swing.JMenuItem();
        opcionMenuEliminarLibro = new javax.swing.JMenuItem();
        menuCatalogoUsuarios = new javax.swing.JMenu();
        opcionMenuAgregarUsuario = new javax.swing.JMenuItem();
        opcionMenuActualizarUsuario = new javax.swing.JMenuItem();
        opcionMenuEliminarUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        opcionMenuSalir = new javax.swing.JMenuItem();
        menuPrestamos = new javax.swing.JMenu();
        menuPrestamosLibros = new javax.swing.JMenu();
        opcionMenuPrestarLibro = new javax.swing.JMenuItem();
        opcionMenuDevolverLibro = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuConsultaLibros = new javax.swing.JMenu();
        opcionMenuConsultaLibros = new javax.swing.JMenuItem();
        opcionMenuConsultaLibrosAutor = new javax.swing.JMenuItem();
        opcionMenuConsultaLibrosTitulo = new javax.swing.JMenuItem();
        opcionMenuConsultaLibrosID = new javax.swing.JMenuItem();
        opcionMenuConsultaUsuarios = new javax.swing.JMenuItem();
        menuConsultaPrestamosLibros = new javax.swing.JMenu();
        opcionMenuConsultaPrestamosLibros = new javax.swing.JMenuItem();
        opcionMenuConsultarValoraciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Biblioteca");

        tituloTabla.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jScrollPane1.setBorder(null);

        menuCatalogos.setMnemonic('f');
        menuCatalogos.setText("Catálogos");

        menuCatalogoLibros.setText("Libros");

        opcionMenuAgregarLibro.setText("Agregar");
        opcionMenuAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuAgregarLibroActionPerformed(evt);
            }
        });
        menuCatalogoLibros.add(opcionMenuAgregarLibro);

        opcionMenuActualizarLibro.setText("Actualizar");
        opcionMenuActualizarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuActualizarLibroActionPerformed(evt);
            }
        });
        menuCatalogoLibros.add(opcionMenuActualizarLibro);

        opcionMenuEliminarLibro.setText("Eliminar");
        opcionMenuEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuEliminarLibroActionPerformed(evt);
            }
        });
        menuCatalogoLibros.add(opcionMenuEliminarLibro);

        menuCatalogos.add(menuCatalogoLibros);

        menuCatalogoUsuarios.setText("Usuarios");

        opcionMenuAgregarUsuario.setText("Agregar");
        opcionMenuAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuAgregarUsuarioActionPerformed(evt);
            }
        });
        menuCatalogoUsuarios.add(opcionMenuAgregarUsuario);

        opcionMenuActualizarUsuario.setText("Actualizar");
        opcionMenuActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuActualizarUsuarioActionPerformed(evt);
            }
        });
        menuCatalogoUsuarios.add(opcionMenuActualizarUsuario);

        opcionMenuEliminarUsuario.setText("Eliminar");
        opcionMenuEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuEliminarUsuarioActionPerformed(evt);
            }
        });
        menuCatalogoUsuarios.add(opcionMenuEliminarUsuario);

        menuCatalogos.add(menuCatalogoUsuarios);
        menuCatalogos.add(jSeparator1);

        opcionMenuSalir.setMnemonic('o');
        opcionMenuSalir.setText("Salir");
        opcionMenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuSalirActionPerformed(evt);
            }
        });
        menuCatalogos.add(opcionMenuSalir);

        menuBar.add(menuCatalogos);

        menuPrestamos.setMnemonic('h');
        menuPrestamos.setText("Préstamos");

        menuPrestamosLibros.setText("Libros");

        opcionMenuPrestarLibro.setText("Prestar");
        opcionMenuPrestarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuPrestarLibroActionPerformed(evt);
            }
        });
        menuPrestamosLibros.add(opcionMenuPrestarLibro);

        opcionMenuDevolverLibro.setText("Devolver");
        opcionMenuDevolverLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuDevolverLibroActionPerformed(evt);
            }
        });
        menuPrestamosLibros.add(opcionMenuDevolverLibro);

        menuPrestamos.add(menuPrestamosLibros);

        menuBar.add(menuPrestamos);

        menuConsultas.setMnemonic('h');
        menuConsultas.setText("Consultas");

        menuConsultaLibros.setText("Libros");

        opcionMenuConsultaLibros.setText("Todos");
        opcionMenuConsultaLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaLibrosActionPerformed(evt);
            }
        });
        menuConsultaLibros.add(opcionMenuConsultaLibros);

        opcionMenuConsultaLibrosAutor.setText("Por Autor");
        opcionMenuConsultaLibrosAutor.setToolTipText("");
        opcionMenuConsultaLibrosAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaLibrosAutorActionPerformed(evt);
            }
        });
        menuConsultaLibros.add(opcionMenuConsultaLibrosAutor);

        opcionMenuConsultaLibrosTitulo.setText("Por Titulo");
        opcionMenuConsultaLibrosTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaLibrosTituloActionPerformed(evt);
            }
        });
        menuConsultaLibros.add(opcionMenuConsultaLibrosTitulo);

        opcionMenuConsultaLibrosID.setText("Por ID");
        opcionMenuConsultaLibrosID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaLibrosIDActionPerformed(evt);
            }
        });
        menuConsultaLibros.add(opcionMenuConsultaLibrosID);

        menuConsultas.add(menuConsultaLibros);

        opcionMenuConsultaUsuarios.setText("Usuarios");
        opcionMenuConsultaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaUsuariosActionPerformed(evt);
            }
        });
        menuConsultas.add(opcionMenuConsultaUsuarios);

        menuConsultaPrestamosLibros.setText("Préstamos Libros");

        opcionMenuConsultaPrestamosLibros.setText("Todos");
        opcionMenuConsultaPrestamosLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultaPrestamosLibrosActionPerformed(evt);
            }
        });
        menuConsultaPrestamosLibros.add(opcionMenuConsultaPrestamosLibros);

        menuConsultas.add(menuConsultaPrestamosLibros);

        opcionMenuConsultarValoraciones.setText("Valoraciones");
        opcionMenuConsultarValoraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionMenuConsultarValoracionesActionPerformed(evt);
            }
        });
        menuConsultas.add(opcionMenuConsultarValoraciones);

        menuBar.add(menuConsultas);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(tituloTabla))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(tituloTabla)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 
     * @param evt 
     */
    private void opcionMenuActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuActualizarUsuarioActionPerformed
// Actualiza el libro
        if (controlUSU.actualizarUsuario(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = controlUSU.getTablaUsuarios(this);
            // Despliega la lista de libro
            despliegaTabla(tablaLibros);
        }
    }//GEN-LAST:event_opcionMenuActualizarUsuarioActionPerformed
    /**
     * 
     * @param evt 
     */
    private void opcionMenuAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuAgregarLibroActionPerformed
        // Agrega el nuevo libro
        if (control.agregarLibro(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = control.getTablaLibros(this);
            // Despliega la lista de libros
            despliegaTabla(tablaLibros);
        } 
    }//GEN-LAST:event_opcionMenuAgregarLibroActionPerformed
    /**
    * Metodo oyente que actualiza un libro del catalogo de libros
    *
    * @param evt Evento al que escucha
    */ 
    private void opcionMenuActualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuActualizarLibroActionPerformed
        // Actualiza el libro
        if (control.actualizarLibro(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = control.getTablaLibros(this);
            // Despliega la lista de libro
            despliegaTabla(tablaLibros);
        }
    }//GEN-LAST:event_opcionMenuActualizarLibroActionPerformed
    /**
    * Metodo oyente que elimina un libro del catalogo de libros
    *
    * @param evt Evento al que escucha
    */
    private void opcionMenuEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuEliminarLibroActionPerformed
        if (control.eliminarLibro(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = control.getTablaLibros(this);
            despliegaTabla(tablaLibros); 
        } 

    }//GEN-LAST:event_opcionMenuEliminarLibroActionPerformed

    private void opcionMenuAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuAgregarUsuarioActionPerformed
         // Agrega el nuevo libro
        if (controlUSU.agregarUsuario(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = controlUSU.getTablaUsuarios(this);
            // Despliega la lista de libros
            despliegaTabla(tablaLibros);
        } 
    }//GEN-LAST:event_opcionMenuAgregarUsuarioActionPerformed

    private void opcionMenuEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuEliminarUsuarioActionPerformed
        if (controlUSU.eliminarUsuario(this)) {
            // Obtiene la lista de libros
            Tabla tablaLibros = controlUSU.getTablaUsuarios(this);
            despliegaTabla(tablaLibros); 
        } 
    }//GEN-LAST:event_opcionMenuEliminarUsuarioActionPerformed

    private void opcionMenuPrestarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuPrestarLibroActionPerformed
        if (control.prestarLibro(this)) {
            // Obtiene la lista de libros
            Tabla tablaPrestamos = control.getTablaPrestamosLibros(this);
            // Despliega la lista de libros
            despliegaTabla(tablaPrestamos);
        }
    }//GEN-LAST:event_opcionMenuPrestarLibroActionPerformed

    private void opcionMenuDevolverLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuDevolverLibroActionPerformed
        if (control.devolverLibro(this)) {
            // Obtiene la lista de libros
            Tabla tablaPrestamos = control.getTablaPrestamosLibros(this);
            // Despliega la lista de libros
            despliegaTabla(tablaPrestamos);
        }
    }//GEN-LAST:event_opcionMenuDevolverLibroActionPerformed
    /**
    * Metodo oyente que obtiene y despliega la lista de libros
    *
    * @param evt Evento al que escucha
    */
    private void opcionMenuConsultaLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaLibrosActionPerformed
        // Obtiene la lista de libros
        ServicioEvaluacionLibrosDAO servicioEvaluacion = new ServicioEvaluacionLibrosDAO();
        LibroDAO iLibroDao = LibroDAO.getInstancia(servicioEvaluacion); // Usa la instancia única
        Tabla tablaLibros = control.getTablaLibros(this);
        // Despliega la lista de libros
        if (tablaLibros != null) {
            despliegaTabla(tablaLibros);
        } 
    }//GEN-LAST:event_opcionMenuConsultaLibrosActionPerformed

    private void opcionMenuConsultaLibrosAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaLibrosAutorActionPerformed
        // Obtiene la lista de libros
        Tabla tablaLibrosAutor = control.getTablaLibrosAutor(this);
        // Despliega la lista de libros
        if (tablaLibrosAutor != null) {
            despliegaTabla(tablaLibrosAutor);
        } 
    }//GEN-LAST:event_opcionMenuConsultaLibrosAutorActionPerformed

    private void opcionMenuConsultaLibrosTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaLibrosTituloActionPerformed
// Obtiene la lista de libros
        Tabla tablaLibrosTitulo = control.getTablaLibrosTitulo(this);
        // Despliega la lista de libros
        if (tablaLibrosTitulo != null) {
            despliegaTabla(tablaLibrosTitulo);
        }        
    }//GEN-LAST:event_opcionMenuConsultaLibrosTituloActionPerformed

    private void opcionMenuConsultaLibrosIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaLibrosIDActionPerformed
        // Obtiene la lista de libros
        Tabla tablaLibrosID = control.getTablaLibrosID(this);
        // Despliega la lista de libros
        if (tablaLibrosID != null) {
            despliegaTabla(tablaLibrosID);
        } 
        
    }//GEN-LAST:event_opcionMenuConsultaLibrosIDActionPerformed

    private void opcionMenuConsultaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaUsuariosActionPerformed
        Tabla tablaUsuarios = controlUSU.getTablaUsuarios(this);
        // Despliega la lista de libros
        if (tablaUsuarios != null) {
            despliegaTabla(tablaUsuarios);
        }
    }//GEN-LAST:event_opcionMenuConsultaUsuariosActionPerformed

    private void opcionMenuConsultaPrestamosLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultaPrestamosLibrosActionPerformed
        // Obtiene la lista de libros
        Tabla tablaLibrosPrestados = control.getTablaPrestamosLibros(this);
        // Despliega la lista de libros
        if (tablaLibrosPrestados != null) {
            despliegaTabla(tablaLibrosPrestados);
        } 
    }//GEN-LAST:event_opcionMenuConsultaPrestamosLibrosActionPerformed

    private void opcionMenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuSalirActionPerformed
        dispose();
    }//GEN-LAST:event_opcionMenuSalirActionPerformed

    private void opcionMenuConsultarValoracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionMenuConsultarValoracionesActionPerformed
        
    }//GEN-LAST:event_opcionMenuConsultarValoracionesActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCatalogoLibros;
    private javax.swing.JMenu menuCatalogoUsuarios;
    private javax.swing.JMenu menuCatalogos;
    private javax.swing.JMenu menuConsultaLibros;
    private javax.swing.JMenu menuConsultaPrestamosLibros;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuPrestamos;
    private javax.swing.JMenu menuPrestamosLibros;
    private javax.swing.JMenuItem opcionMenuActualizarLibro;
    private javax.swing.JMenuItem opcionMenuActualizarUsuario;
    private javax.swing.JMenuItem opcionMenuAgregarLibro;
    private javax.swing.JMenuItem opcionMenuAgregarUsuario;
    private javax.swing.JMenuItem opcionMenuConsultaLibros;
    private javax.swing.JMenuItem opcionMenuConsultaLibrosAutor;
    private javax.swing.JMenuItem opcionMenuConsultaLibrosID;
    private javax.swing.JMenuItem opcionMenuConsultaLibrosTitulo;
    private javax.swing.JMenuItem opcionMenuConsultaPrestamosLibros;
    private javax.swing.JMenuItem opcionMenuConsultaUsuarios;
    private javax.swing.JMenuItem opcionMenuConsultarValoraciones;
    private javax.swing.JMenuItem opcionMenuDevolverLibro;
    private javax.swing.JMenuItem opcionMenuEliminarLibro;
    private javax.swing.JMenuItem opcionMenuEliminarUsuario;
    private javax.swing.JMenuItem opcionMenuPrestarLibro;
    private javax.swing.JMenuItem opcionMenuSalir;
    private javax.swing.JLabel tituloTabla;
    // End of variables declaration//GEN-END:variables
    
    ControlUsuarios controlUSU = new ControlUsuarios();
    Control control = new Control();
    private javax.swing.JTable jtabla; 
    

}
