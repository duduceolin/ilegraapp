package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.bean.dtos.Venda;
import br.com.ilegra.ilegraapp.bean.dtos.Vendedor;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author dudu
 */
public class VendedorHelper extends BaseHelper {
    
    private Vendedor vendedor;
    
    /**
     * Método para separar os parâmetros da linha pelo delimitador definido.
     *
     * @param linha
     * @throws ServException
     */
    public VendedorHelper(String linha) throws ServException {
        String[] parametros = linha.split(ConstanteUtils.CARACTERE_DELIMITADOR);
        
        popularVendedor(parametros);
    }
    
    /**
     * Método para conversão do conteúdo de uma linha do tipo VENDEDOR em um objeto
     * Vendedor.
     *
     * @param params
     * @throws ServException
     */
    private void popularVendedor(String[] params) throws ServException {
        try {
            Vendedor vend = new Vendedor();
            
            vend.setCpf(params[1]);
            vend.setNome(params[2]);
            vend.setSalario(new BigDecimal(params[3]));
            vend.setVendas(new ArrayList<Venda>());
            vend.setTotalVendas(new BigDecimal(BigInteger.ZERO));
            
            this.vendedor = vend;
            
        } catch (Exception e) {
            logarException(e);
            throw new ConfigExceptionFactory().criarException(ConfigException.GR002);
        }
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
}
