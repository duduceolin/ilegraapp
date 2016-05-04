package br.com.ilegra.ilegraapp.application.controllers;

import br.com.ilegra.ilegraapp.application.base.BaseController;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import br.com.ilegra.ilegraapp.bean.utils.ConstanteUtils;
import java.io.File;
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
    
    private String caminho;
    private Integer tempo;
    private Scheduler scheduler;

    /**
     * Método principal de processamento.
     */
    @Override
    public void processar() {
        
        try {
            validarEntrada();
            ConstanteUtils.CAMINHO = caminho;
            ConstanteUtils.INTERVALO = tempo;
            ConstanteUtils.DATA_ULTIMA_EXECUCAO = null;
            
            JobDetail job = JobBuilder.newJob(ArquivoController.class).withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).withSchedule(
                    CronScheduleBuilder.cronSchedule(ConstanteUtils.CRON_ATV.replace("$", tempo.toString()))).build();
            iniciarJob(job, trigger);
            
        } catch (Exception e) {
            if (e instanceof ServException) {
                logarMensagemDebug(ConfigExceptionFactory.criarMensagemLog((ServException) e));
            } else {
                logarException(e);
            }
        }
        
    }
    
    public void finalizarExecucao() {
        try {
            finalizarJob();
        } catch (Exception e) {
            logarException(e);
        }
    }

    /**
     * Verificar se todos os parâmetros iniciais estão preenchidos corretamente.
     *
     * @throws ServException
     */
    private void validarEntrada() throws ServException {
        if (tempo == null || tempo < 0) {
            throw new ConfigExceptionFactory().criarException(ConfigException.GR001);
        }
        
        if (caminho == null) {
            throw new ConfigExceptionFactory().criarException(ConfigException.GR001);
        } else {
            File file = new File(caminho);
            
            if (!file.isDirectory()) {
                throw new ConfigExceptionFactory().criarException(ConfigException.GR003);
            }
        }
    }

    /**
     * Método para inicializar um trabalho de tempo em tempo definido
     * anteriormente.
     *
     * @param job
     * @param trigger
     * @throws SchedulerException
     */
    private void iniciarJob(JobDetail job, Trigger trigger) throws SchedulerException {
        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
    
    private void finalizarJob() throws SchedulerException {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
    
    public String getCaminho() {
        return caminho;
    }
    
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
    public Integer getTempo() {
        return tempo;
    }
    
    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }
    
}
