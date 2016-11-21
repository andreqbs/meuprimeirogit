/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.view.CapturaController;

/**
 *
 * @author andreqbs
 */
public class ControlerLeituraEscrita {

    private LeituraController lc;
    private CapturaController cc;

    public ControlerLeituraEscrita(int largura, int altura) {
        lc = new LeituraController();
        cc = new CapturaController();
        cc.showWindow(largura, altura);

    }

    public ControlerLeituraEscrita() {

    }

    public void definirTamanho(int largura, int altura) {
        lc = new LeituraController();
        cc = new CapturaController();
        cc.showWindow(largura, altura);

    }

    public void iniciarLeituraEscrita() {
        cc.capturaEstatico();
        lc.leituraNumero();

    }

    public String getValor() {
        return lc.valorLido();
    }

    public void setLocal(String localImagem, String localJar) {
        lc.setLocal(localImagem, localJar);

    }

}
