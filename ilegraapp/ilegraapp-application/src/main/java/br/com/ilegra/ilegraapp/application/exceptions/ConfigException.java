package br.com.ilegra.ilegraapp.application.exceptions;

import br.com.ilegra.ilegraapp.bean.enums.TipoErro;

/**
 *
 * @author dudu
 */
public enum ConfigException {
    /**
     * Campos Obrigatórios não preenchidos
     */
    GR001("Campos Obrigatórios não preenchidos");

    private final String descricao;

    private final TipoErro tipoErro;

    private ConfigException(final String descricao) {
        this(descricao, TipoErro.NEGOCIO);
    }

    private ConfigException(final String descricao, final TipoErro tipoErro) {
        this.descricao = descricao;
        this.tipoErro = tipoErro;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

}
