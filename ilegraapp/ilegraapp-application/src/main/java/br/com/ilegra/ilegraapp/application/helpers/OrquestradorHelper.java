package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.application.utils.FileUtils;
import br.com.ilegra.ilegraapp.bean.dtos.Cliente;
import br.com.ilegra.ilegraapp.bean.dtos.MensagemAbstrata;
import br.com.ilegra.ilegraapp.bean.dtos.Venda;
import br.com.ilegra.ilegraapp.bean.dtos.Vendedor;
import br.com.ilegra.ilegraapp.bean.enums.TipoErro;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import br.com.ilegra.ilegraapp.bean.utils.MensagemUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
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

    private static final String LINHA_NUMERO_CLIENTES = "Número de clientes: ";
    private static final String LINHA_NUMERO_VENDEDORES = "Número de vendedores: ";
    private static final String LINHA_MAIOR_VENDA = "ID Maior venda: ";
    private static final String LINHA_PIOR_VENDEDOR = "Pior vendedor: ";

    /**
     * Método invocado pela thread.
     */
    @Override
    public void run() {
        processarArquivo();
    }

    /**
     * Método principal de processamento de arquivo. Percorre linha a linha do
     * arquivo que está em processamento.
     */
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

    /**
     * Método de processamento de conteúdo de linha (redireciona para o helper
     * conforme seu tipo de mensagem).
     *
     * @param linha
     * @throws ServException
     */
    private void processarLinha(String linha) throws ServException {
        logarMensagemDebug(MensagemUtils.MSG_PROCESSAMENTO_LINHA.replace("?", linha));

        MensagemAbstrata msg = verificarTipoLinha(linha);

        switch (msg.getTipoLinha()) {
            case CLIENTE:
                ClienteHelper clienteHelper = new ClienteHelper(linha);
                clientes.add(clienteHelper.getCliente());
                logarMensagemDebug(MensagemUtils.MSG_ADICIONANDO_OBJETO.replace("?", clienteHelper.getCliente().toString()));
                break;
            case VENDA:
                VendaHelper vendaHelper = new VendaHelper(linha);
                vendas.add(vendaHelper.getVenda());
                logarMensagemDebug(MensagemUtils.MSG_ADICIONANDO_OBJETO.replace("?", vendaHelper.getVenda().toString()));
                break;
            case VENDEDOR:
                VendedorHelper vendedorHelper = new VendedorHelper(linha);
                vendedores.add(vendedorHelper.getVendedor());
                logarMensagemDebug(MensagemUtils.MSG_ADICIONANDO_OBJETO.replace("?", vendedorHelper.getVendedor().toString()));
                break;
        }
    }

    /**
     * Método para organizar as listas e montar o arquivo de saida.
     *
     * @throws ServException
     */
    private void processarListas() throws ServException {
        gerarVendasPorVendedor();

        gerarConteudoSaida();

        gerarArquivoSaida();
    }

    private String gerarConteudoSaida() {
        StringBuilder str = new StringBuilder();

        str.append(gerarLinhaSaida(LINHA_NUMERO_CLIENTES, String.valueOf(clientes.size())));
        str.append(gerarLinhaSaida(LINHA_NUMERO_VENDEDORES, String.valueOf(vendedores.size())));

        if (vendas.size() > 0) {
            Collections.sort(vendas);
            Collections.reverse(vendas);
            str.append(gerarLinhaSaida(LINHA_MAIOR_VENDA, String.valueOf(vendas.get(0).getId())));
        }

        if (vendedores.size() > 0) {
            Collections.sort(vendedores);
            str.append(gerarLinhaSaida(LINHA_PIOR_VENDEDOR, String.valueOf(vendedores.get(0).getNome())));
        }

        return str.toString();
    }

    /**
     * Método para gerar o arquivo de saida.
     */
    private void gerarArquivoSaida() {
        final String caminhoPasta = ConstanteUtils.CAMINHO + ConstanteUtils.ENDERECO_PASTA_OUT;
        final String nomeArquivoEntrada = arquivoProcessamento.getName();
        final String nomeArquivoSaida = nomeArquivoEntrada.replace(".dat", ".done.dat");

        File diretorio = new File(caminhoPasta);

        if (!diretorio.isDirectory()) {
            diretorio.mkdir();
        }

        try {
            FileUtils.criarArquivo(caminhoPasta + nomeArquivoSaida, gerarConteudoSaida());
        } catch (ServException e) {
            logarMensagemDebug(ConfigExceptionFactory.criarMensagemLog(e));
        }
    }

    /**
     * Método que gera o conteúdo que será impresso no arquivo de saída.
     * @param desc
     * @param param
     * @return 
     */
    private String gerarLinhaSaida(String desc, String param) {
        StringBuilder str = new StringBuilder();

        str.append(desc);
        str.append(param);
        str.append("\n");

        return str.toString();
    }

    /**
     * Método para transferir as vendas para cada vendedor. Feito desta maneira
     * para não haver preocupações com a ordem do arquivo de entrada.
     */
    private void gerarVendasPorVendedor() {
        Iterator<Venda> itrVendas = vendas.iterator();

        while (itrVendas.hasNext()) {
            Venda venda = itrVendas.next();

            if (venda != null) {
                final String nomeVendedor = venda.getVendedor().getNome();

                if (adicionarVendaParaVendedor(nomeVendedor, venda)) {
                    //itrVendas.remove();
                }

            }
        }
    }

    /**
     * Método para buscar um vendedor pelo nome e inserir uma venda na sua lista
     * de vendas.
     *
     * @param nome
     * @param venda
     * @return
     */
    private Boolean adicionarVendaParaVendedor(String nome, Venda venda) {

        logarMensagemDebug(MensagemUtils.MSG_ADICIONANDO_VENDA.replace("?", venda.toString()));

        for (Vendedor vendedor : vendedores) {
            if (nome.equals(vendedor.getNome())) {
                vendedor.getVendas().add(venda);
                vendedor.setTotalVendas(vendedor.getTotalVendas().add(venda.getTotalVenda()));
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Método para conversão de tipo de linha.
     *
     * @param linha
     * @return
     * @throws ServException
     */
    private MensagemAbstrata verificarTipoLinha(String linha) throws ServException {
        try {
            final String codigo = linha.substring(0, 3);

            MensagemAbstrata mensagem = new MensagemAbstrata(codigo);
            logarMensagemDebug(MensagemUtils.MSG_TIPO_LINHA.replace("?", mensagem.getTipoLinha().name()));

            return mensagem;
        } catch (Exception e) {
            throw new ConfigExceptionFactory().criarException(ConfigException.GR002);
        }
    }

    /**
     *
     */
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
