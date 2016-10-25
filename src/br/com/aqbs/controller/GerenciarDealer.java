/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.dao.DealerDaoJDBC;
import br.com.aqbs.model.Dealer;
import java.util.List;


/**
 *
 * @author andreqbs
 */
public class GerenciarDealer {
    
    private DaoFactory javabase = DaoFactory.getInstance("javabase.jdbc");
    private DealerDaoJDBC dealerDao = javabase.getDealerDAO();
   
    
    public void inserirDealer(String dealer) {
        Dealer n = new Dealer(dealer);
        dealerDao.create(n);
    }
    
    public List<Dealer> listar() {
        return dealerDao.list();
    }
    
}
