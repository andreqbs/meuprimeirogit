/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.dao.NumeroDaoJDBC;
import br.com.aqbs.model.Numero;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aqbs
 */
public class GerenciarRoleta {

    private DaoFactory javabase = DaoFactory.getInstance("javabase.jdbc");
    private NumeroDaoJDBC numeroDAO = javabase.getNumeroDAO();
    private List<Integer> l = new ArrayList<>();

    public List<Numero> getNumeros() {
        List<Numero> list = numeroDAO.list();
        return list;
    }

    public List<Integer> calculaEstatisticas(int valor) {

        if (l.isEmpty()) {
            for (int i = 0; i < 13; i++) {
                l.add(0);
            }
        }
        int repeticoes;

        if (valor > 0 && valor < 13) {
            repeticoes = l.get(0);
            repeticoes++;
            l.set(0, repeticoes);
            l.set(1, 0);
            l.set(2, 0);
        } else if (valor > 12 && valor < 25) {
            repeticoes = l.get(1);
            repeticoes++;
            l.set(1, repeticoes);
            l.set(0, 0);
            l.set(2, 0);
        } else if (valor > 24 && valor < 37) {
            repeticoes = l.get(2);
            repeticoes++;
            l.set(2, repeticoes);
            l.set(1, 0);
            l.set(0, 0);
        }

        if (valor > 0 && valor < 19) {
            repeticoes = l.get(3);
            repeticoes++;
            l.set(3, repeticoes);
            l.set(4, 0);
        } else if (valor > 18 && valor < 37) {
            repeticoes = l.get(4);
            repeticoes++;
            l.set(4, repeticoes);
            l.set(3, 0);
        }

        if (valor == 1 || valor == 4 || valor == 7 || valor == 10 || valor == 13
                || valor == 16 || valor == 19 || valor == 22 || valor == 25
                || valor == 28 || valor == 31 || valor == 34) {

            repeticoes = l.get(5);
            repeticoes++;
            l.set(5, repeticoes);
            l.set(6, 0);
            l.set(7, 0);
        } else if (valor == 2 || valor == 5 || valor == 8 || valor == 11 || valor == 14
                || valor == 17 || valor == 20 || valor == 23 || valor == 26
                || valor == 29 || valor == 32 || valor == 35) {

            repeticoes = l.get(6);
            repeticoes++;
            l.set(6, repeticoes);
            l.set(5, 0);
            l.set(7, 0);
        } else if (valor == 3 || valor == 6 || valor == 9 || valor == 12 || valor == 15
                || valor == 18 || valor == 21 || valor == 24 || valor == 27
                || valor == 30 || valor == 33 || valor == 36) {
            repeticoes = l.get(7);
            repeticoes++;
            l.set(7, repeticoes);
            l.set(5, 0);
            l.set(6, 0);

        }

        if (valor % 2 == 0) {
            repeticoes = l.get(8);
            repeticoes++;
            l.set(8, repeticoes);
            l.set(9, 0);
        } else if (valor % 2 == 1) {
            repeticoes = l.get(9);
            repeticoes++;
            l.set(9, repeticoes);
            l.set(8, 0);

        }
        if (pegarCor(valor).equals("Vermelho")) {
            repeticoes = l.get(10);
            repeticoes++;
            l.set(10, repeticoes);
            l.set(11, 0);

        } else if (pegarCor(valor).equals("Preto")) {
            repeticoes = l.get(11);
            repeticoes++;
            l.set(11, repeticoes);
            l.set(10, 0);

        } else {
            l.set(0, 0);
            l.set(1, 0);
            l.set(2, 0);
            l.set(3, 0);
            l.set(4, 0);
            l.set(9, 0);
            l.set(10, 0);
            l.set(11, 0);
            l.set(12, 0);
            l.set(5, 0);
            l.set(6, 0);
            l.set(7, 0);

        }

        if (valor != 5 && valor != 8 && valor != 6 && valor != 9 && valor != 10
                && valor != 11 && valor != 13 && valor != 14 && valor != 16
                && valor != 17 && valor != 23 && valor != 24 && valor != 27
                && valor != 30 && valor != 31 && valor != 33 && valor != 34
                && valor != 36) {

            repeticoes = l.get(12);
            repeticoes++;
            l.set(12, repeticoes);

        } else {

            l.set(12, 0);

        }

        return l;

    }

    private String pegarCor(int valor) {

        switch (valor) {
            case 0:
                return "Verde";
            case 1:
                return "Vermelho";
            case 2:
                return "Preto";
            case 3:
                return "Vermelho";
            case 4:
                return "Preto";
            case 5:
                return "Vermelho";
            case 6:
                return "Preto";
            case 7:
                return "Vermelho";
            case 8:
                return "Preto";
            case 9:
                return "Vermelho";
            case 10:
                return "Preto";
            case 11:
                return "Preto";
            case 12:
                return "Vermelho";
            case 13:
                return "Preto";
            case 14:
                return "Vermelho";
            case 15:
                return "Preto";
            case 16:
                return "Vermelho";
            case 17:
                return "Preto";
            case 18:
                return "Vermelho";
            case 19:
                return "Vermelho";
            case 20:
                return "Preto";
            case 21:
                return "Vermelho";
            case 22:
                return "Preto";
            case 23:
                return "Vermelho";
            case 24:
                return "Preto";
            case 25:
                return "Vermelho";
            case 26:
                return "Preto";
            case 27:
                return "Vermelho";
            case 28:
                return "Preto";
            case 29:
                return "Preto";
            case 30:
                return "Vermelho";
            case 31:
                return "Preto";
            case 32:
                return "Vermelho";
            case 33:
                return "Preto";
            case 34:
                return "Vermelho";
            case 35:
                return "Preto";
            case 36:
                return "Vermelho";
            default:
                return "ERRO";

        }

    }

}
