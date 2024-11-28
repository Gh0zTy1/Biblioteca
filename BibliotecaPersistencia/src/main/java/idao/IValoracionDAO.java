/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idao;

/**
 *
 * @author caarl
 */


import daos.ValoracionDAO;
import entidades.Libro;
import entidades.Valoracion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del DAO para gestionar las valoraciones de libros.
 */
public class IValoracionDAO implements ValoracionDAO {
    private List<Valoracion> valoraciones;
    private static IValoracionDAO instancia;

    // Patrón Singleton para obtener una única instancia del DAO
    public static IValoracionDAO getInstancia() {
        if (instancia == null) {
            instancia = new IValoracionDAO();
        }
        return instancia;
    }

    private IValoracionDAO() {
        valoraciones = new ArrayList<>();
    }

    // Método para registrar una valoración
    @Override
    public void registrarValoracion(Valoracion valoracion) throws Exception {
        if (obten(valoracion) != null) {
            throw new Exception("La valoración ya existe para este libro y usuario.");
        }
        valoraciones.add(valoracion);
        System.out.println("Valoración registrada: " + valoracion);
    }

    // Método para consultar todas las valoraciones de un libro
    @Override
    public List<Valoracion> consultarValoraciones(Libro libro) {
        return valoraciones.stream()
                           .filter(v -> v.getLibro().equals(libro))  // Filtra por libro
                           .collect(Collectors.toList());
    }

    // Método para obtener una valoración específica
    public Valoracion obten(Valoracion valoracion) {
        return valoraciones.stream()
                           .filter(v -> v.equals(valoracion))  // Busca la valoración por libro y usuario
                           .findFirst()
                           .orElse(null);
    }

    // Método para calcular el promedio de valoraciones de un libro
    public double calcularPromedioValoraciones(Libro libro) {
        List<Valoracion> valoracionesLibro = consultarValoraciones(libro);
        if (valoracionesLibro.isEmpty()) {
            return 0; // Si no hay valoraciones, el promedio es 0
        }
        int suma = valoracionesLibro.stream()
                                    .mapToInt(Valoracion::getValoracion)
                                    .sum();
        return suma / (double) valoracionesLibro.size();
    }

    // Método para listar todas las valoraciones (aunque normalmente no es necesario en este caso)
    @Override
    public List<Valoracion> listarValoraciones() {
        return new ArrayList<>(valoraciones);
    }
}
