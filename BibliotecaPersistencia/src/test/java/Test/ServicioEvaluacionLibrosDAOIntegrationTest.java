package Test;
import Dao.ServicioEvaluacionLibrosDAO;
import Excepciones.ServicioNoDisponibleException;
import entidades.ReseñaLibro;
import static org.junit.Assert.*;

import org.junit.Test;

public class ServicioEvaluacionLibrosDAOIntegrationTest {

    @Test
    public void testObtenerEvaluacion_ServicioReal() {
        ServicioEvaluacionLibrosDAO servicio = new ServicioEvaluacionLibrosDAO();

        try {
            String isbn = "9780134685991"; // Un ISBN válido
            ReseñaLibro resultado = servicio.obtenerEvaluacion(isbn);

            assertNotNull("La reseña no debe ser nula", resultado);
            assertTrue("La valoración debe ser mayor o igual a 0", resultado.getValoracion() >= 0);
            assertNotNull("El comentario no debe ser nulo", resultado.getComentario());

        } catch (ServicioNoDisponibleException e) {
            fail("El servicio no debería lanzar una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testObtenerEvaluacion_ISBNNoEncontrado() {
        ServicioEvaluacionLibrosDAO servicio = new ServicioEvaluacionLibrosDAO();

        try {
            String isbn = "1"; // Un ISBN inexistente
            servicio.obtenerEvaluacion(isbn);
            fail("Se esperaba una excepción ServicioNoDisponibleException");

        } catch (ServicioNoDisponibleException e) {
            assertTrue(e.getMessage().contains("No se encontró información"));
        }
    }
}
