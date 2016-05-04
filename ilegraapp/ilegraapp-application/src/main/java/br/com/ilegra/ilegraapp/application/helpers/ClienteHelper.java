package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.dtos.Cliente;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;

/**
 *
 * @author dudu
 */
public class ClienteHelper extends BaseHelper{

    private Cliente cliente;

    public ClienteHelper(String linha) {
        String[] parametros = linha.split(ConstanteUtils.CARACTERE_DELIMITADOR);

        popularCliente(parametros);
    }

    private void popularCliente(String[] params) {
        Cliente cl = new Cliente();

        cl.setCnpj(params[1]);
        cl.setNome(params[2]);
        cl.setArea(params[3]);

        this.cliente = cl;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
