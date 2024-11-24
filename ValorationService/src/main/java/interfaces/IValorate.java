/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.valorationService.exceptions.SystemNotAvailableException;
import com.valorationService.exceptions.ValorationNotFoundException;
import entityes.Valoration;

/**
 *
 * @author skevi
 */
public interface IValorate {
    
    /**
     *
     * @param title
     * @param author
     * @return
     * @throws com.valorationService.exceptions.SystemNotAvailableException
     * @throws com.valorationService.exceptions.ValorationNotFoundException
     */
    public Valoration getValoration(String title, String author) throws 
            SystemNotAvailableException, ValorationNotFoundException;
    
}
