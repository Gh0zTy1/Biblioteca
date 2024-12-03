/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.ServicioNoDisponibleException;
import entidades.ReseñaLibro;

/**
 *
 * @author gaspa
 */
public interface IServicioEvaluacionLibros {
    ReseñaLibro obtenerEvaluacion(String isbn) throws ServicioNoDisponibleException;
}
