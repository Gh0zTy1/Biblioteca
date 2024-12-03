/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Excepciones.ServicioNoDisponibleException;
import Interfaces.IServicioEvaluacionLibros;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entidades.ReseñaLibro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author gaspa
 */
public class ServicioEvaluacionLibrosDAO implements IServicioEvaluacionLibros {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:{ISBN}&key={API_KEY}";
    private static final String API_KEY = "AIzaSyC8MBgf0TD88wv4J0GUhgS30Pw9O5hiU-4"; 

    @Override
    public ReseñaLibro obtenerEvaluacion(String isbn) throws ServicioNoDisponibleException {
        try {
            // Construcción de la URL con el ISBN
            String urlString = BASE_URL.replace("{ISBN}", isbn).replace("{API_KEY}", API_KEY);
            URL url = new URL(urlString);

            // Configuración de la conexión HTTP
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET"); // Método HTTP GET
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setConnectTimeout(5000); // Timeout de conexión en milisegundos
            conexion.setReadTimeout(5000);    // Timeout de lectura en milisegundos

            // Verifica si la conexión fue exitosa
            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta JSON
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    respuesta.append(linea);
                }
                reader.close();

                // Procesa el JSON recibido
                JsonObject json = JsonParser.parseString(respuesta.toString()).getAsJsonObject();

                if (json.has("items")) {
                    JsonObject libro = json.getAsJsonArray("items").get(0).getAsJsonObject();
                    JsonObject volumenInfo = libro.getAsJsonObject("volumeInfo");

                    // Extraer valoración y comentario
                    double valoracion = volumenInfo.has("averageRating") ? volumenInfo.get("averageRating").getAsDouble() : 0.0;
                    String comentario = volumenInfo.has("description") ? volumenInfo.get("description").getAsString() : "Sin descripción disponible";

                    return new ReseñaLibro(valoracion, comentario);
                } else {
                    throw new ServicioNoDisponibleException("No se encontró información para el ISBN proporcionado.");
                }

            } else {
                throw new ServicioNoDisponibleException("Error al obtener evaluación. Código de respuesta: " + codigoRespuesta);
            }

        } catch (Exception e) {
            throw new ServicioNoDisponibleException("No se pudo conectar al servicio externo: " + e.getMessage());
        }
    }
}