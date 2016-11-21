/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreqbs
 */
public class LeituraEscritaArquivo {

    private String localJar;
    private String localImagem;
    private int largura;
    private int altura;

    public String leitor() {
        StringBuilder builder = new StringBuilder();
        try (InputStream input = new FileInputStream("..//meuprimeirogit//dist//arquivo.txt")) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(LeituraEscritaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder.toString();

    }

    public void escritor(String linha) {

        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("..//meuprimeirogit//dist//arquivo.txt"));

            buffWrite.append(linha);
            buffWrite.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeituraEscritaArquivo.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(LeituraEscritaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLocalJar() {
        return localJar;
    }

    public void setLocalJar(String localJar) {
        this.localJar = localJar;
    }

    public String getLocalImagem() {
        return localImagem;
    }

    public void setLocalImagem(String localImagem) {
        this.localImagem = localImagem;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    

}
