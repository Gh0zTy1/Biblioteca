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
public class Valoration {

    private double calificacion; // Rango de 0 a 5
    private String comentario;

    /**
     * Constructor por defecto.
     */
    public Valoration() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param calificacion La calificación del libro (0 a 5).
     * @param comentario El comentario asociado a la valoración.
     */
    public Valoration(double calificacion, String comentario) {
        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5.");
        }
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    /**
     * Obtiene la calificación de la valoración.
     * 
     * @return La calificación como un valor de 0 a 5.
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Establece la calificación de la valoración.
     * 
     * @param calificacion La calificación del libro (0 a 5).
     */
    public void setCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5.");
        }
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el comentario asociado a la valoración.
     * 
     * @return El comentario de la valoración.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario de la valoración.
     * 
     * @param comentario El comentario de la valoración.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Representación en cadena de la valoración.
     * 
     * @return Una representación en cadena de la calificación y el comentario.
     */
    @Override
    public String toString() {
        return "Valoration{" + 
               "calificacion=" + calificacion + 
               ", comentario='" + comentario + '\'' + 
               '}';
    }

    /**
     * Compara esta valoración con otra.
     * 
     * @param obj El objeto a comparar.
     * @return true si las valoraciones son iguales; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Valoration that = (Valoration) obj;
        return Double.compare(that.calificacion, calificacion) == 0 &&
               comentario.equals(that.comentario);
    }

    /**
     * Genera el código hash para la valoración.
     * 
     * @return El código hash de la valoración.
     */
    @Override
    public int hashCode() {
        int result = Double.hashCode(calificacion);
        result = 31 * result + (comentario != null ? comentario.hashCode() : 0);
        return result;
    }
}
