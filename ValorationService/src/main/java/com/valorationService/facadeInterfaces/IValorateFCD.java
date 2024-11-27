/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.valorationService.facadeInterfaces;

import entidades.Valoration;

public interface IValorateFCD {
    Valoration getValoration(String title, String author) throws Exception;
}
