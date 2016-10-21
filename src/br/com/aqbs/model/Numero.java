/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.model;

/**
 *
 * @author aqbs
 */
public class Numero {
    private int id;
    private String valor;
    private String cor;
    private String data;
    private String turno;
    private String dealer;

    public Numero(String valor, String cor, String data, String turno, String dealer) {
        this.valor = valor;
        this.cor = cor;
        this.data = data;
        this.turno = turno;
        this.dealer = dealer;
    }

    public Numero() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }
    
    
    
    
}
