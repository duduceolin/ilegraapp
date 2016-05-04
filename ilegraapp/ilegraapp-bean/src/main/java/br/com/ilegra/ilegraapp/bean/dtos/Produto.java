/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.bean.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author dudu
 */
public class Produto implements Serializable {

    private Integer id;
    private Integer quantidade;
    private BigDecimal preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getCustoTotal() {
        BigDecimal custoTotal = preco.multiply(new BigDecimal(quantidade.toString()));
        return custoTotal;
    }

}
