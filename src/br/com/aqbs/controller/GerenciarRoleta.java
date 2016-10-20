/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.dao.NumeroDaoJDBC;
import br.com.aqbs.model.Numero;
import java.util.List;

/**
 *
 * @author aqbs
 */
public class GerenciarRoleta {

    private DaoFactory javabase = DaoFactory.getInstance("javabase.jdbc");
    private NumeroDaoJDBC numeroDAO = javabase.getNumeroDAO();

    public List<Numero> getNumeros() {
        List<Numero> list = numeroDAO.list();
        return list;
    }

}
