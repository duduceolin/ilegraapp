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
    GR001("Campos Obrigatórios não preenchidos", TipoErro.VALIDACAO),
    GR002("Erro no formato da linha", TipoErro.NEGOCIO),
    GR003("O diretório de entrada não existe.", TipoErro.NEGOCIO),
    GR004("O diretório de saída não existe.", TipoErro.NEGOCIO),
    GR005("Ocorreu um problema ao gerar o arquivo de saída.", TipoErro.DESCONHECIDO);

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
