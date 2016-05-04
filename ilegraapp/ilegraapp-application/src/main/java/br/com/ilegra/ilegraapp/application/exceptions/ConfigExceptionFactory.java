package br.com.ilegra.ilegraapp.application.exceptions;

/**
 *
 * @author dudu
 */
public class ConfigExceptionFactory {

    /**
     * Método de criação de exception personalizada.
     *
     * @param ex
     * @return
     */
    public ServException criarException(ConfigException ex) {
        if (ex == null) {
            throw new IllegalArgumentException("É obrigatório informar a exceção a ser lançada.");
        }

        return new ServException(ex.name(), ex.getDescricao(), ex.getTipoErro());
    }

    public static String criarMensagemLog(ServException e) {
        return e.getCodErro() + " - " + e.getMensagem() + " - " + e.getTipoErro().name();
    }

}
