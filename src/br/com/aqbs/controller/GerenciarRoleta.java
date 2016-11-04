/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aqbs.controller;

import br.com.aqbs.conexao.DaoFactory;
import br.com.aqbs.dao.NumeroDaoJDBC;
import br.com.aqbs.model.Numero;
import br.com.aqbs.model.Som;
import br.com.aqbs.view.FXMLGraficoConfiguracaoController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private List<Integer> backup = new ArrayList<>();
    private Som s = new Som();
    private FXMLGraficoConfiguracaoController gcc;
    private String dealer;

    public GerenciarRoleta() {
        Thread threadDoPdf = new Thread(s);
        // threadDoPdf.start();
    }
    
    public List<Numero> getNumeros() {
        List<Numero> list = numeroDAO.list();
        return list;
    }

    public void inserirNumero(String valor, String turno, String dealer) {
        int v = Integer.parseInt(valor);
        LocalDateTime timePoint = LocalDateTime.now();
        LocalDate theDate = timePoint.toLocalDate();

        Numero n = new Numero(valor, pegarCor(v), theDate.toString(), turno, dealer);
        numeroDAO.create(n);
    }

    public void inserirNumero(String valor) {
        int v = Integer.parseInt(valor);
        LocalDateTime timePoint = LocalDateTime.now();
        LocalDate theDate = timePoint.toLocalDate();

        Numero n = new Numero(valor, pegarCor(v), timePoint.format(DateTimeFormatter.ISO_LOCAL_DATE), timePoint.format(DateTimeFormatter.ISO_LOCAL_TIME), dealer);
        numeroDAO.create(n);
    }

    public List<Integer> totalRodadas() {

        List<Numero> numeros = getNumeros();

        List<Integer> total = new ArrayList<>();
        total.add(0);
        total.add(0);
        total.add(0);
        total.add(parteDaMesa(numeros, 0, 19));
        total.add(parteDaMesa(numeros, 18, 37));
        total.add(0);
        total.add(0);
        total.add(0);
        total.add(parImparSeguidos(numeros, true)); //true - par
        total.add(parImparSeguidos(numeros, false)); //false - impar
        total.add(corSeguidas(numeros, "Vermelho"));
        total.add(corSeguidas(numeros, "Preto"));
        total.add(0);

        return total;
    }

    public void reavaliarGrafico() {
        for (int i = 0; i < l.size(); i++) {
            l.set(i, backup.get(i));
        }
    }

    public List<Integer> calculaEstatisticas(int valor) {

        if (l.isEmpty()) {
            for (int i = 0; i < 13; i++) {
                l.add(0);
                backup.add(0);
            }
        }
        for (int i = 0; i < l.size(); i++) {
            backup.set(i, l.get(i));
        }

        int repeticoes;

        if (valor > 0 && valor < 13) {
            repeticoes = l.get(2);
            repeticoes++;
            l.set(0, 0);
            l.set(1, 0);
            l.set(2, repeticoes);
        } else if (valor > 12 && valor < 25) {
            repeticoes = l.get(1);
            repeticoes++;
            l.set(0, 0);
            l.set(1, repeticoes);
            l.set(2, 0);
        } else if (valor > 24 && valor < 37) {
            repeticoes = l.get(0);
            repeticoes++;
            l.set(0, repeticoes);
            l.set(1, 0);
            l.set(2, 0);
        }

        if (valor > 0 && valor < 19) { //Metade menor
            repeticoes = l.get(3);
            repeticoes++;
            l.set(3, repeticoes);
            l.set(4, 0);
            if (repeticoes > 4) {
                s.aviso();
            }
        } else if (valor > 18 && valor < 37) { //Metade maior
            repeticoes = l.get(4);
            repeticoes++;
            l.set(4, repeticoes);
            l.set(3, 0);
            if (repeticoes > 4) {
                s.aviso();
            }
        }

        if (valor == 1 || valor == 4 || valor == 7 || valor == 10 || valor == 13
                || valor == 16 || valor == 19 || valor == 22 || valor == 25
                || valor == 28 || valor == 31 || valor == 34) {

            repeticoes = l.get(7);
            repeticoes++;
            l.set(5, 0);
            l.set(6, 0);
            l.set(7, repeticoes);
        } else if (valor == 2 || valor == 5 || valor == 8 || valor == 11 || valor == 14
                || valor == 17 || valor == 20 || valor == 23 || valor == 26
                || valor == 29 || valor == 32 || valor == 35) {

            repeticoes = l.get(6);
            repeticoes++;
            l.set(5, 0);
            l.set(6, repeticoes);
            l.set(7, 0);
        } else if (valor == 3 || valor == 6 || valor == 9 || valor == 12 || valor == 15
                || valor == 18 || valor == 21 || valor == 24 || valor == 27
                || valor == 30 || valor == 33 || valor == 36) {
            repeticoes = l.get(5);
            repeticoes++;
            l.set(5, repeticoes);
            l.set(6, 0);
            l.set(7, 0);

        }

        if (valor % 2 == 0) {
            repeticoes = l.get(8);
            repeticoes++;
            l.set(8, repeticoes);
            l.set(9, 0);
            if (repeticoes > 4) {
                s.aviso();
            }
        } else if (valor % 2 == 1) {
            repeticoes = l.get(9);
            repeticoes++;
            l.set(9, repeticoes);
            l.set(8, 0);
            if (repeticoes > 4) {
                s.aviso();
            }

        }
        if (pegarCor(valor).equals("Vermelho")) {
            repeticoes = l.get(10);
            repeticoes++;
            l.set(10, repeticoes);
            l.set(11, 0);
            if (repeticoes > 4) {
                s.aviso();
            }

        } else if (pegarCor(valor).equals("Preto")) {
            repeticoes = l.get(11);
            repeticoes++;
            l.set(11, repeticoes);
            l.set(10, 0);
            if (repeticoes > 4) {
                s.aviso();
            }

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
            l.set(8, 0);

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

        if (valor == -1) {
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
            l.set(8, 0);
        }
        return l;

    }
    
    public void calcularAposta() {
        
            if(l.get(0) > 3 ) {
                
            }
            
            if(l.get(1) > 3) {
                
            }
            
            if( l.get(2) > 3) {
                
            }
            
            if(l.get(3) > 9 ) {
                
            }
            
            if(l.get(4) > 9) {
                
            }
            
            if(l.get(5) > 3 ) {
                
            }
            
            if(l.get(6) > 3) {
                
            }
            
            if( l.get(7) > 3) {
                

            }           
    }

    public String pegarCor(int valor) {

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

    public List<Integer> tratarEstatistica(List<Integer> l) {

        List<Integer> t = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {

        }

        return null;
    }

    public void limparApostas() {
        for (int i = 0; i < l.size(); i++) {
            l.set(i, 0);
        }

    }

    private int corSeguidas(List<Numero> t, String cor) {

        int aux = 0;
        int total = 0;
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getCor().equals(cor)) {
                aux++;
            } else {
                if (aux > total) {
                    total = aux;
                }
                aux = 0;
            }

        }
        return total;
    }

    private int parteDaMesa(List<Numero> t, int incio, int fim) {

        int aux = 0;
        int total = 0;
        for (int i = 0; i < t.size(); i++) {
            int valor = Integer.parseInt(t.get(i).getValor());
            if (valor > incio && valor < fim) {
                aux++;
            } else {
                if (aux > total) {
                    total = aux;
                }
                aux = 0;
            }

        }
        return total;
    }

    private int parImparSeguidos(List<Numero> t, boolean opcao) {

        int aux = 0;
        int total = 0;
        for (int i = 0; i < t.size(); i++) {
            int valor = Integer.parseInt(t.get(i).getValor());
            if (opcao) {
                if (valor % 2 == 0) {
                    aux++;
                } else {
                    if (aux > total) {
                        total = aux;
                    }
                    aux = 0;
                }
            } else if (valor % 2 == 1) {
                aux++;
            } else {
                if (aux > total) {
                    total = aux;
                }
                aux = 0;
            }

        }
        return total;
    }

    public void setDealer(String result) {
       this.dealer = result;
    }

}
