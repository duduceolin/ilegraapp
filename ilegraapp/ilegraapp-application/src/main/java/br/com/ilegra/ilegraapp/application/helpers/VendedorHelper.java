package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.dtos.Vendedor;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import java.math.BigDecimal;

/**
 *
 * @author dudu
 */
public class VendedorHelper extends BaseHelper{

    private Vendedor vendedor;

    public VendedorHelper(String linha) {
        String[] parametros = linha.split(ConstanteUtils.CARACTERE_DELIMITADOR);

        popularVendedor(parametros);
    }
    
    private void popularVendedor(String[] params){
        Vendedor vend = new Vendedor();
        
        vend.setCpf(params[1]);
        vend.setNome(params[2]);
        vend.setSalario(new BigDecimal(params[3]));
        
        this.vendedor = vend;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
