/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ValorationMapper;

import entidades.ReseñaLibro;
import entidades.Valoration;

/**
 *
 * @author caarl
 */


public class ValorationMapper {

    /**
     * Convierte un objeto Valoration a ReseñaLibro.
     * 
     * @param valoration El objeto Valoration a convertir.
     * @return Un objeto ReseñaLibro.
     */
    public static ReseñaLibro toReseñaLibro(Valoration valoration) {
        if (valoration == null) {
            return null; // Maneja valores nulos si es necesario
        }
        return new ReseñaLibro(valoration.getCalificacion(), valoration.getComentario());
    }

    /**
     * Convierte un objeto ReseñaLibro a Valoration.
     * 
     * @param reseñaLibro El objeto ReseñaLibro a convertir.
     * @return Un objeto Valoration.
     */
    public static Valoration toValoration(ReseñaLibro reseñaLibro) {
        if (reseñaLibro == null) {
            return null; // Maneja valores nulos si es necesario
        }
        return new Valoration(reseñaLibro.getCalificacion(), reseñaLibro.getReseña());
    }
}
