/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.valorationService.exceptions;

/**
 *
 * @author skevi
 */
public class SystemNotAvailableException extends Exception{

    public SystemNotAvailableException() {
    }

    public SystemNotAvailableException(String message) {
        super(message);
    }

    public SystemNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemNotAvailableException(Throwable cause) {
        super(cause);
    }

    public SystemNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
