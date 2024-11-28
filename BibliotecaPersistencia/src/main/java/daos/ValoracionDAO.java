/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

/**
 *
 * @author caarl
 */


import entidades.Libro;
import entidades.Valoracion;
import java.util.List;

/**
 * Interfaz para las operaciones de acceso a datos relacionadas con las valoraciones de libros.
 */
public interface ValoracionDAO {
    
    /**
     * Registra una nueva valoración para un libro.
     * 
     * @param valoracion La valoración a registrar.
     * @throws Exception Si ya existe una valoración para el libro y el usuario, o si ocurre otro error.
     */
    void registrarValoracion(Valoracion valoracion) throws Exception;

    /**
     * Consulta todas las valoraciones de un libro específico.
     * 
     * @param libro El libro para el cual se desean consultar las valoraciones.
     * @return Una lista de valoraciones del libro.
     */
    List<Valoracion> consultarValoraciones(Libro libro);
    
    /**
     * Lista todas las valoraciones registradas en el sistema.
     * 
     * @return Una lista de todas las valoraciones.
     */
    List<Valoracion> listarValoraciones();
    
    /**
     * Calcula el promedio de las valoraciones de un libro específico.
     * 
     * @param libro El libro para el cual se desea calcular el promedio de valoraciones.
     * @return El promedio de las valoraciones del libro.
     */
    double calcularPromedioValoraciones(Libro libro);
}
