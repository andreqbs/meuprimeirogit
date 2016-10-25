/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.dao.TipoDaoJDBC;

/**
 *
 * @author andreqbs
 */
public class GerenciarTipo {

    private DaoFactory javabase = DaoFactory.getInstance("javabase.jdbc");
    private TipoDaoJDBC tipoDAO = javabase.getTipoDAO();
    
}
