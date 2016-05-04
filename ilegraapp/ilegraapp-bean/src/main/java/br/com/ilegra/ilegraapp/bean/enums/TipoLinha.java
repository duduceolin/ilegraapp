/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.bean.enums;

/**
 *
 * @author dudu
 */
public enum TipoLinha {
    VENDEDOR,
    VENDA,
    CLIENTE;

    public static TipoLinha getTipoByChave(String chave) {
        TipoLinha tipo = null;
        
        switch (chave) {
            case "001":
                tipo = TipoLinha.VENDEDOR;
                break;

            case "002":
                tipo = TipoLinha.CLIENTE;
                break;

            case "003":
                tipo = TipoLinha.VENDA;
                break;
        }

        return tipo;
    }
}
