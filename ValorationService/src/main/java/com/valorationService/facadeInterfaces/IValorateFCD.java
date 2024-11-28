/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.valorationService.facadeInterfaces;

import entidades.ReseñaLibro;
import entidades.Valoration;

public interface IValorateFCD {
    ReseñaLibro getValoration(String title, String author) throws Exception;
}
