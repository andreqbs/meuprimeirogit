/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.dao;


import br.com.aqbs.exception.DaoException;
import br.com.aqbs.model.Dealer;
import br.com.aqbs.model.Numero;
import java.util.List;

/**
 *
 * @author aqbs
 */
public interface IDaoDealer {
    
    public abstract Numero find(Integer id) throws DaoException;

    public abstract List<Dealer> list() throws DaoException;

    public abstract void create(Dealer l) throws IllegalArgumentException, DaoException;

    public abstract void update(Dealer l) throws IllegalArgumentException, DaoException;

    public abstract void delete(Integer id) throws DaoException;
    
}
