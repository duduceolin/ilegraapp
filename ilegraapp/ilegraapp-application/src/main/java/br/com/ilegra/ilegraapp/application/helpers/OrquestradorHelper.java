package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.dtos.Cliente;
import br.com.ilegra.ilegraapp.bean.dtos.MensagemAbstrata;
import br.com.ilegra.ilegraapp.bean.dtos.Venda;
import br.com.ilegra.ilegraapp.bean.dtos.Vendedor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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

    public void processarArquivo() {

        inicializarListas();

        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(arquivoProcessamento));
            String linha = "";
            while (true) {
                if (linha != null) {
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
                            break;
                    }
                } else {
                    break;
                }
                linha = buffRead.readLine();
            }
            buffRead.close();
        } catch (Exception e) {
            if (log != null) {
                log.error(e);
            }
        }

    }

    private MensagemAbstrata verificarTipoLinha(String linha) {
        String codigo = linha.substring(0, 2);

        MensagemAbstrata mensagem = new MensagemAbstrata(codigo);

        return mensagem;
    }

    private void inicializarListas() {
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    @Override
    public void run() {
        processarArquivo();
    }

    public File getArquivoProcessamento() {
        return arquivoProcessamento;
    }

    public void setArquivoProcessamento(File arquivoProcessamento) {
        this.arquivoProcessamento = arquivoProcessamento;
    }

}
