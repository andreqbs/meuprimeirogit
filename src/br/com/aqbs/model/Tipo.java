/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.model;

/**
 *
 * @author andreqbs
 */
public class Tipo {
    
    private int id;
    private String nome;
    private int pagamento;

    public Tipo() {
    }

    public Tipo(String nome, int pagamento) {
        this.nome = nome;
        this.pagamento = pagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }
    
    
    
}
