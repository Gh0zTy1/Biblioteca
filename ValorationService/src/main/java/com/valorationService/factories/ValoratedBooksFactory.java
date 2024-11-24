/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.factories;

import entityes.Book;
import entityes.Valoration;
import valoration.Valorate;

/**
 *
 * @author skevi
 */
public class ValoratedBooksFactory {
    
    /**
     * 
     */
    Valorate valorate;

    /**
     * 
     * @param valorate 
     */
    public ValoratedBooksFactory(Valorate valorate) {
        this.valorate = valorate;
    }
    
    /**
     * 
     */
    public void fabricateValoratedBooks(){
        
            Valoration valoration1 = new Valoration((byte)5, 
                    "Maginifico libro");
            Valoration valoration2 = new Valoration((byte)3, 
                    "Un libro emocionante");
            Valoration valoration3 = new Valoration((byte)4, 
                    "Triste y emotivo en toda la historia");
            Valoration valoration4 = new Valoration((byte)2, 
                    "Cargado de emotividad y reflexion");
            Valoration valoration5 = new Valoration((byte)4, 
                    "Muy interesante y conmovedor");            
            
            valorate.addBook(new Book("978-3-16", 
                                            "Cien años de soledad", 
                                            "Gabriel García Márquez",
                                             valoration1));
            valorate.addBook(new Book("978-0-452", 
                                            "Orgullo y prejuicio", 
                                            "Jane Austen",
                                             valoration2));
            valorate.addBook(new Book("978-1-566", 
                                            "1984", 
                                            "George Orwell",
                                             valoration3));
            valorate.addBook(new Book("978-0-743", 
                                            "El gran Gatsby", 
                                            "F. Scott Fitzgerald",
                                             valoration4));
            valorate.addBook(new Book("938-0-143", 
                                            "El gran Gatsby", 
                                            "F. Scott Fitzgerald",
                                             valoration4));
            valorate.addBook(new Book("978-0-525", 
                                            "Don Quijote de la Mancha", 
                                            "Miguel de Cervantes",
                                             valoration3));
            valorate.addBook(new Book("948-1-741", 
                                            "1984", 
                                            "George Orwell",
                                             valoration3));
            valorate.addBook(new Book("578-0-123", 
                                            "Orgullo y prejuicio", 
                                            "Jane Austen",
                                             valoration2));
            valorate.addBook(new Book("978-1-4767", 
                                            "Inferno", 
                                            "Dan Brown",
                                             valoration5));
            valorate.addBook(new Book("978-0-452", 
                                            "Orgullo y prejuicio", 
                                            "Jane Austen",
                                             valoration2));
    }
    
}
