/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Representa una valoración asociada a un libro.
 * Contiene información sobre la calificación y los comentarios.
 * 
 * @author 
 */


public class Valoracion {
    private Libro libro;
    private String usuario;
    private int valoracion; // Valoración entre 1 y 5
    private String comentario;

    public Valoracion(Libro libro, String usuario, int valoracion, String comentario) {
        this.libro = libro;
        this.usuario = usuario;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    // Getters y setters
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
