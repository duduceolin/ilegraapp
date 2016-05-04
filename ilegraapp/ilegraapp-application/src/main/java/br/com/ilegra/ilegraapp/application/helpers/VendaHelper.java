package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.bean.dtos.Produto;
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
public class VendaHelper extends BaseHelper {

    private Venda venda;

    public VendaHelper(String linha) throws ServException {
        String[] parametros = linha.split(ConstanteUtils.CARACTERE_DELIMITADOR);

        popularVenda(parametros);
    }

    private void popularVenda(String[] params) throws ServException {
        try {
            Venda ve = new Venda();
            ve.setId(Integer.parseInt(params[1]));
            ve.setVendedor(new Vendedor());
            ve.getVendedor().setNome(params[3]);
            ve.setTotalVenda(new BigDecimal(BigInteger.ZERO));

            String produto = params[2];
            produto = produto.replace("[", "");
            produto = produto.replace("]", "");

            String[] produtos = produto.split(",");

            ve.setProdutos(new ArrayList<Produto>());

            for (String prod : produtos) {
                String[] dif = prod.split("-");

                Produto pr = new Produto();
                pr.setId(Integer.parseInt(dif[0]));
                pr.setQuantidade(Integer.parseInt(dif[1]));
                pr.setPreco(new BigDecimal(dif[2]));

                ve.setTotalVenda(ve.getTotalVenda().add(pr.getCustoTotal()));
                ve.getProdutos().add(pr);
            }

            this.venda = ve;
        } catch (Exception e) {
            logarException(e);
            throw new ConfigExceptionFactory().criarException(ConfigException.GR002);
        }

    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
