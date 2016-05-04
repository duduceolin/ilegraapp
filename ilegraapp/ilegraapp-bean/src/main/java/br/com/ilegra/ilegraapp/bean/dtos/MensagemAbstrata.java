/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.bean.dtos;

import br.com.ilegra.ilegraapp.bean.enums.TipoLinha;
import java.io.Serializable;

/**
 *
 * @author dudu
 */
public class MensagemAbstrata implements Serializable {

    private TipoLinha tipoLinha;

    public MensagemAbstrata() {

    }

    public MensagemAbstrata(String codigo) {
        this.tipoLinha = TipoLinha.getTipoByChave(codigo);
    }

    public TipoLinha getTipoLinha() {
        return tipoLinha;
    }

    public void setTipoLinha(TipoLinha tipoLinha) {
        this.tipoLinha = tipoLinha;
    }

}
