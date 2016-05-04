/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.application.application;

import br.com.ilegra.ilegraapp.application.controllers.GerenciadorController;

/**
 *
 * @author dudu
 */
public class Inicializador {
    
    public static void main(String[] args){
        try {
            GerenciadorController gerenciador = new GerenciadorController();
            gerenciador.processar();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
    
}
