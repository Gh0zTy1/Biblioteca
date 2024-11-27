/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.factories;

import entidades.Libro;
import entidades.ReseñaLibro;
import valoration.Valorate;

public class ValoratedBooksFactory {

    private final Valorate valorate;

    public ValoratedBooksFactory(Valorate valorate) {
        this.valorate = valorate;
    }

    public void fabricateValoratedBooks() {
        ReseñaLibro reseña1 = new ReseñaLibro(5.0, "Magnífico libro");
        ReseñaLibro reseña2 = new ReseñaLibro(3.0, "Un libro emocionante");
        ReseñaLibro reseña3 = new ReseñaLibro(4.0, "Triste y emotivo en toda la historia");

        valorate.addBook(new Libro("Cien años de soledad", "Gabriel García Márquez", 1));
        valorate.addBook(new Libro("Orgullo y prejuicio", "Jane Austen", 2));
        valorate.addBook(new Libro("1984", "George Orwell", 3));
    }
}
