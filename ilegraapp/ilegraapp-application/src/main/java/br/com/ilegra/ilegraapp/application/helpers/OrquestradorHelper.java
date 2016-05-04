package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.bean.dtos.Cliente;
import br.com.ilegra.ilegraapp.bean.dtos.MensagemAbstrata;
import br.com.ilegra.ilegraapp.bean.dtos.Venda;
import br.com.ilegra.ilegraapp.bean.dtos.Vendedor;
import br.com.ilegra.ilegraapp.bean.enums.TipoErro;
import br.com.ilegra.ilegraapp.bean.utils.MensagemUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dudu
 */
public class OrquestradorHelper extends BaseHelper implements Runnable {

    private File arquivoProcessamento;

    private List<Vendedor> vendedores;
    private List<Venda> vendas;
    private List<Cliente> clientes;

    @Override
    public void run() {
        processarArquivo();
    }

    public void processarArquivo() {

        inicializarListas();

        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(arquivoProcessamento));
            String linha = "";
            Integer numeroLinha = 0;
            while (true) {
                if (linha != null) {
                    if (!linha.isEmpty()) {
                        try {
                            processarLinha(linha);
                        } catch (ServException e) {
                            if (TipoErro.DESCONHECIDO.equals(e.getTipoErro())) {
                                throw e;
                            }

                            logarMensagemDebug(MensagemUtils.ERRO_LINHA.replace("?", numeroLinha.toString()));
                        }
                        numeroLinha++;
                    }

                } else {
                    break;
                }

                linha = buffRead.readLine();
            }

            buffRead.close();

            processarListas();

        } catch (Exception e) {
            logarMensagemDebug(MensagemUtils.ERRO_ARQUIVO.replace("?", arquivoProcessamento.getName()));
            logarException(e);
        }

    }

    private void processarLinha(String linha) throws ServException {
        MensagemAbstrata msg = verificarTipoLinha(linha);

        switch (msg.getTipoLinha()) {
            case CLIENTE:
                ClienteHelper clienteHelper = new ClienteHelper(linha);
                clientes.add(clienteHelper.getCliente());
                break;
            case VENDA:
                VendaHelper vendaHelper = new VendaHelper(linha);
                vendas.add(vendaHelper.getVenda());
                break;
            case VENDEDOR:
                VendedorHelper vendedorHelper = new VendedorHelper(linha);
                vendedores.add(vendedorHelper.getVendedor());
                break;
        }
    }

    private void processarListas() throws ServException {
        gerarVendasPorVendedor();
    }

    private void gerarVendasPorVendedor() {
        Iterator<Venda> itrVendas = vendas.iterator();

        while (itrVendas.hasNext()) {
            Venda venda = itrVendas.next();

            if (venda != null) {
                String nomeVendedor = venda.getVendedor().getNome();

                if (adicionarVendaParaVendedor(nomeVendedor, venda)) {
                    itrVendas.remove();
                }

            }
        }
    }

    private Boolean adicionarVendaParaVendedor(String nome, Venda venda) {

        for (Vendedor vendedor : vendedores) {
            if (nome.equals(vendedor.getNome())) {
                vendedor.getVendas().add(venda);
                vendedor.setTotalVendas(vendedor.getTotalVendas().add(venda.getTotalVenda()));
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    private MensagemAbstrata verificarTipoLinha(String linha) throws ServException {
        try {
            String codigo = linha.substring(0, 3);

            MensagemAbstrata mensagem = new MensagemAbstrata(codigo);

            return mensagem;
        } catch (Exception e) {
            if (log != null) {
                log.error(e);
            }
            throw new ConfigExceptionFactory().criarException(ConfigException.GR002);
        }
    }

    private void inicializarListas() {
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    public File getArquivoProcessamento() {
        return arquivoProcessamento;
    }

    public void setArquivoProcessamento(File arquivoProcessamento) {
        this.arquivoProcessamento = arquivoProcessamento;
    }

}
