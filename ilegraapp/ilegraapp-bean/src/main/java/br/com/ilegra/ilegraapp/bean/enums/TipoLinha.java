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
