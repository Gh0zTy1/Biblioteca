/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.valorationService.facadeInterfaces;

import entityes.Valoration;

/**
 *
 * @author skevi
 */
public interface IValorateFCD {
    
    Valoration getValoration(String title, String author) throws Exception;
    
}
