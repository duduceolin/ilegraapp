package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import br.com.ilegra.ilegraapp.bean.utils.MensagemUtils;
import java.io.File;
import java.util.Date;

/**
 *
 * @author dudu
 */
public class ValidadorHelper extends BaseHelper {

    /**
     * Método que verifica se existem arquivos que foram alterados posterior a
     * última data de execução do processamento.
     *
     * @return
     */
    public Boolean realizarProcessamento() {
        
        File diretorio = new File(ConstanteUtils.CAMINHO + ConstanteUtils.ENDERECO_PASTA_IN);
        if (diretorio.isDirectory()) {
            Date modificacaoDiretorio = new Date(diretorio.lastModified());
            
            if (ConstanteUtils.DATA_ULTIMA_EXECUCAO == null
                    || modificacaoDiretorio.after(ConstanteUtils.DATA_ULTIMA_EXECUCAO)) {
                return Boolean.TRUE;
            }
            
        }
        
        logarMensagemDebug(MensagemUtils.MSG_SEM_ARQUIVOS);
        
        return Boolean.FALSE;
    }
}
