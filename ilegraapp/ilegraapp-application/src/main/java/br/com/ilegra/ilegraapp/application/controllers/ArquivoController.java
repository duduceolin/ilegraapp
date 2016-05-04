package br.com.ilegra.ilegraapp.application.controllers;

import br.com.ilegra.ilegraapp.application.base.BaseController;
import br.com.ilegra.ilegraapp.application.helpers.MensagemHelper;
import br.com.ilegra.ilegraapp.application.helpers.ValidadorHelper;
import br.com.ilegra.ilegraapp.bean.utils.DateUtils;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author dudu
 */
public class ArquivoController extends BaseController implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        this.processar();
    }

    @Override
    public void processar() {

        if (log != null && log.isDebugEnabled()) {
            log.debug("Inicializou processamento: " + DateUtils.gerarDataFormatada(new Date()));
        }
        
        Boolean processar = new ValidadorHelper().realizarProcessamento();
        
        if (processar){
            new MensagemHelper();
        }
        
        if (log != null && log.isDebugEnabled()) {
            log.debug("Finalizou processamento: " + DateUtils.gerarDataFormatada(new Date()));
        }
    }
    

}
