package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.bean.dtos.Cliente;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;

/**
 *
 * @author dudu
 */
public class ClienteHelper extends BaseHelper {

    private Cliente cliente;

    /**
     * Método para separar os parâmetros da linha pelo delimitador definido.
     *
     * @param linha
     * @throws ServException
     */
    public ClienteHelper(String linha) throws ServException {
        String[] parametros = linha.split(ConstanteUtils.CARACTERE_DELIMITADOR);

        popularCliente(parametros);
    }

    /**
     * Método para conversão do conteúdo de uma linha do tipo CLIENTE em um
     * objeto Cliente.
     *
     * @param params
     * @throws ServException
     */
    private void popularCliente(String[] params) throws ServException {

        try {
            Cliente cl = new Cliente();

            cl.setCnpj(params[1]);
            cl.setNome(params[2]);
            cl.setArea(params[3]);

            this.cliente = cl;
        } catch (Exception e) {
            logarException(e);
            throw new ConfigExceptionFactory().criarException(ConfigException.GR002);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
