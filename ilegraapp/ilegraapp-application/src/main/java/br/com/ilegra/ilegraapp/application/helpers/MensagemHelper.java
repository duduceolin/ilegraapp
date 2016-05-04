package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dudu
 */
public class MensagemHelper extends BaseHelper {

    private List<File> listaArquivos;

    public MensagemHelper() {
        if (pesquisarArquivos()) {
            //iniciar exec
            ConstanteUtils.DATA_ULTIMA_EXECUCAO = new Date();
            
            for (File file : listaArquivos) {
                OrquestradorHelper helper = new OrquestradorHelper();
                helper.setArquivoProcessamento(file);
                Thread t1 = new Thread(helper);
                t1.start();
            }

        } else if (log != null && log.isDebugEnabled()) {
            log.debug("Não existem arquivos para serem processados.");
        }
    }

    private Boolean pesquisarArquivos() {

        File diretorioEntrada = new File(ConstanteUtils.CAMINHO + ConstanteUtils.ENDERECO_PASTA_IN);
        listaArquivos = new ArrayList<>();
        Boolean adicionou = Boolean.FALSE;

        if (diretorioEntrada.isDirectory()) {
            File[] arquivos = diretorioEntrada.listFiles();

            for (int i = 0; i < arquivos.length; i++) {
                if (arquivos[i].isFile()) {
                    Date dataModificacao = new Date(arquivos[i].lastModified());
                    if (ConstanteUtils.DATA_ULTIMA_EXECUCAO == null || dataModificacao.after(ConstanteUtils.DATA_ULTIMA_EXECUCAO)) {
                        listaArquivos.add(arquivos[i]);
                        adicionou = Boolean.TRUE;
                    }
                }
            }
        }

        return adicionou;
    }

}
