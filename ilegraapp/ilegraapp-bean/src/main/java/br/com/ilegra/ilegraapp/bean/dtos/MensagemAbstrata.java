package br.com.ilegra.ilegraapp.bean.dtos;

import br.com.ilegra.ilegraapp.bean.enums.TipoLinha;
import java.io.Serializable;

/**
 *
 * @author dudu
 */
public class MensagemAbstrata implements Serializable {

    private static final long serialVersionUID = -952301333223137126L;

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

    @Override
    public String toString() {
        return "MensagemAbstrata{" + "tipoLinha=" + tipoLinha + '}';
    }

}
