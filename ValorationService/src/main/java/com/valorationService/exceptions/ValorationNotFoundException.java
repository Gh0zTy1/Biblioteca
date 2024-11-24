/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.exceptions;

/**
 *
 * @author skevi
 */
public class ValorationNotFoundException extends Exception{

    public ValorationNotFoundException() {
    }

    public ValorationNotFoundException(String message) {
        super(message);
    }

    public ValorationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValorationNotFoundException(Throwable cause) {
        super(cause);
    }

    public ValorationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
