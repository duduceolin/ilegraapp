package br.com.ilegra.ilegraapp.application.controllers;

import br.com.ilegra.ilegraapp.application.base.BaseController;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author dudu
 */
public class GerenciadorController extends BaseController {

    /**
     * Método para execução de atividade automática.
     */
    private String caminho;

    @Override
    public void processar() {

        try {

            ConstanteUtils.CAMINHO = caminho;
            ConstanteUtils.DATA_ULTIMA_EXECUCAO = null;

            JobDetail job = JobBuilder.newJob(ArquivoController.class).withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).withSchedule(CronScheduleBuilder.cronSchedule(ConstanteUtils.CRON_ATV)).build();
            iniciarJob(job, trigger);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void iniciarJob(JobDetail job, Trigger trigger) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}
