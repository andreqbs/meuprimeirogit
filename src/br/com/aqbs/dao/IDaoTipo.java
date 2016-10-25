/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.dao;


import br.com.aqbs.exception.DaoException;
import br.com.aqbs.model.Tipo;
import java.util.List;

/**
 *
 * @author aqbs
 */
public interface IDaoTipo {
    
    public abstract Tipo find(Integer id) throws DaoException;

    public abstract List<Tipo> list() throws DaoException;

    public abstract void create(Tipo l) throws IllegalArgumentException, DaoException;

    public abstract void update(Tipo l) throws IllegalArgumentException, DaoException;

    public abstract void delete(Integer id) throws DaoException;
    
}
