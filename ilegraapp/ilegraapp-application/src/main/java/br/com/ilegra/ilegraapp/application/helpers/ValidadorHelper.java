package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import java.io.File;
import java.util.Date;

/**
 *
 * @author dudu
 */
public class ValidadorHelper extends BaseHelper {

    public Boolean realizarProcessamento() {

        File diretorio = new File(ConstanteUtils.CAMINHO + ConstanteUtils.ENDERECO_PASTA_IN);
        if (diretorio.isDirectory()) {
            Date modificacaoDiretorio = new Date(diretorio.lastModified());

            if (ConstanteUtils.DATA_ULTIMA_EXECUCAO == null
                    || modificacaoDiretorio.after(ConstanteUtils.DATA_ULTIMA_EXECUCAO)) {
                return Boolean.TRUE;
            }

        }

        if (log != null && log.isDebugEnabled()) {
            log.debug("Processamento não realizado.");
        }

        return Boolean.FALSE;
    }
}
