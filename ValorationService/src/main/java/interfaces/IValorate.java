/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import com.valorationService.exceptions.SystemNotAvailableException;
import com.valorationService.exceptions.ValorationNotFoundException;
import entidades.ReseñaLibro;

public interface IValorate {
    ReseñaLibro getValoration(String title, String author) throws 
            SystemNotAvailableException, ValorationNotFoundException;
}
