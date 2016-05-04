/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.application.helpers;

import br.com.ilegra.ilegraapp.application.base.BaseHelper;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import br.com.ilegra.ilegraapp.bean.utils.PropUtils;
import java.io.File;
import java.util.Date;

/**
 *
 * @author dudu
 */
public class ValidadorHelper extends BaseHelper{

    public Boolean realizarProcessamento() {

        File diretorio = new File(ConstanteUtils.CAMINHO + ConstanteUtils.ENDERECO_PASTA_IN);
        Date modificacaoDiretorio = new Date(diretorio.lastModified());

        if (modificacaoDiretorio.after(ConstanteUtils.DATA_ULTIMA_EXECUCAO)) {
            return Boolean.TRUE;
        }

        if (log != null && log.isDebugEnabled()){
            log.debug("Processamento não realizado.");
        }
        
        return Boolean.FALSE;
    }
}
