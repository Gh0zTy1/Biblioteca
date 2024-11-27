/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.integration;

import com.valorationService.exceptions.SystemNotAvailableException;
import com.valorationService.exceptions.ValorationNotFoundException;
import com.valorationService.facadeInterfaces.IValorateFCD;
import entidades.ReseñaLibro;
import interfaces.IValorate;

public class ExternalSystemIntegration implements IValorateFCD {

    private final IValorate valorate;

    public ExternalSystemIntegration(IValorate valorate) {
        this.valorate = valorate;
    }

    @Override
    public ReseñaLibro getValoration(String title, String author) throws Exception {
        try {
            return valorate.getValoration(title, author);
        } catch (SystemNotAvailableException | ValorationNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
