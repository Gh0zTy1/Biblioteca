/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test;

import Dao.ServicioEvaluacionLibrosDAO;
import Excepciones.ServicioNoDisponibleException;
import Interfaces.IServicioEvaluacionLibros;
import entidades.ReseñaLibro;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author gaspa
 */
public class ServicioEvaluacionLibrosDAOTest {

    private IServicioEvaluacionLibros servicioMock;
    private ServicioEvaluacionLibrosDAO servicioReal;

    @Before
    public void setUp() {
        servicioMock = Mockito.mock(IServicioEvaluacionLibros.class);
        servicioReal = new ServicioEvaluacionLibrosDAO();
    }

    @Test
    public void testObtenerEvaluacion_ValoracionExitosa() throws ServicioNoDisponibleException {
        // Configurar el comportamiento del mock
        String isbn = "1234567890";
        ReseñaLibro reseñaEsperada = new ReseñaLibro(4.5, "Un excelente libro sobre programación.");
        when(servicioMock.obtenerEvaluacion(isbn)).thenReturn(reseñaEsperada);

        // Ejecutar el método real
        ReseñaLibro resultado = servicioMock.obtenerEvaluacion(isbn);

        // Verificar el resultado
        assertEquals(reseñaEsperada.getValoracion(), resultado.getValoracion(), 0.01);
        assertEquals(reseñaEsperada.getComentario(), resultado.getComentario());
    }

    @Test(expected = ServicioNoDisponibleException.class)
    public void testObtenerEvaluacion_FalloConexion() throws ServicioNoDisponibleException {
        // Configurar el comportamiento del mock para lanzar una excepción
        String isbn = "1234567890";
        when(servicioMock.obtenerEvaluacion(isbn)).thenThrow(new ServicioNoDisponibleException("Fallo de conexión"));

        // Ejecutar el método real y esperar la excepción
        servicioMock.obtenerEvaluacion(isbn);
    }

    @Test
    public void testObtenerEvaluacion_RespuestaVacia() throws ServicioNoDisponibleException {
        // Configurar el mock para devolver una reseña sin datos
        String isbn = "1234567890";
        ReseñaLibro reseñaEsperada = new ReseñaLibro(0.0, "Sin descripción disponible.");
        when(servicioMock.obtenerEvaluacion(isbn)).thenReturn(reseñaEsperada);

        // Ejecutar el método real
        ReseñaLibro resultado = servicioMock.obtenerEvaluacion(isbn);

        // Verificar el resultado
        assertEquals(reseñaEsperada.getValoracion(), resultado.getValoracion(), 0.01);
        assertEquals(reseñaEsperada.getComentario(), resultado.getComentario());
    }
}

