/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author caarl
 */
public class ReseñaLibro {
    private double valoracion; // Ejemplo: 4.5
    private String comentario; // Ejemplo: "Un libro muy interesante."

    // Constructor
    public ReseñaLibro(double valoracion, String comentario) {
        this.valoracion = valoracion;
        this.comentario = comentario;
    }

    // Getters y Setters
    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return String.format("Valoración: %.1f, Reseña: %s", valoracion, comentario);
    }
}

