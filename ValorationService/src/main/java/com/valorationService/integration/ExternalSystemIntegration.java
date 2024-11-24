/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.integration;

import com.valorationService.exceptions.SystemNotAvailableException;
import com.valorationService.exceptions.ValorationNotFoundException;
import com.valorationService.facadeInterfaces.IValorateFCD;
import entityes.Valoration;
import interfaces.IValorate;
import valoration.Valorate;

/**
 *
 * @author skevi
 */
public class ExternalSystemIntegration implements IValorateFCD{

    /**
     * 
     */
    private final IValorate valoration;

    /**
     * 
     * @param valoration
     */
    public ExternalSystemIntegration(IValorate valoration) {
        this.valoration = valoration;
    }
    
    /**
     * 
     * @param title
     * @param author
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public Valoration getValoration(String title, String author) 
            throws Exception{
        try{
            return valoration.getValoration(title, author);
        }
        catch(SystemNotAvailableException | ValorationNotFoundException ex){
            throw new Exception(ex.getMessage());
        }
    }
    
}
