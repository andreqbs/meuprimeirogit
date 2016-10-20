/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.exception;

/**
 *
 * @author aqbs
 */
public class DaoException extends RuntimeException {
    
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
