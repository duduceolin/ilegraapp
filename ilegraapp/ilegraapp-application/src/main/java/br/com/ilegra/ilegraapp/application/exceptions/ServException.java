package br.com.ilegra.ilegraapp.application.exceptions;

import br.com.ilegra.ilegraapp.bean.enums.TipoErro;

/**
 *
 * @author dudu
 */
public class ServException extends Exception {

    private String codErro;
    private String mensagem;
    private TipoErro tipoErro;

    public ServException(String message) {
        super(message);
    }

    public ServException(String cod, String message, TipoErro tipoErro) {
        this.codErro = cod;
        this.mensagem = message;
        this.tipoErro = tipoErro;
    }

    public String getCodErro() {
        return codErro;
    }

    public void setCodErro(String codErro) {
        this.codErro = codErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

    public void setTipoErro(TipoErro tipoErro) {
        this.tipoErro = tipoErro;
    }

}
