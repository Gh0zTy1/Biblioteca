/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import Dao.ServicioEvaluacionLibrosDAO;
import Excepciones.ServicioNoDisponibleException;
import Interfaces.IServicioEvaluacionLibros;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private double valoracion;
    private String reseña;
    private Boolean disponible;

    // Constructor
    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.valoracion = 0.0;  // Valoración inicializada a 0.0
        this.reseña = "Sin descripción disponible";  // Reseña inicializada con un valor predeterminado
        this.disponible = true;
    }

    public Libro(String isbn) {
        this.isbn = isbn;
        this.disponible = true;
    }
    

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    // Getters y setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }
}
