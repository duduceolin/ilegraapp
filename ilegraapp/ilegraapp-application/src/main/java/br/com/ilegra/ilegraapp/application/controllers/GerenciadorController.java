/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.application.controllers;

import br.com.ilegra.ilegraapp.application.base.BaseController;
import br.com.ilegra.ilegraapp.application.utils.ConstanteUtils;
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
    @Override
    public void processar() {

        try {
            JobDetail job = JobBuilder.newJob(ArquivoController.class).withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(ConstanteUtils.NOME_ATV, ConstanteUtils.GRUPO_ATV).withSchedule(CronScheduleBuilder.cronSchedule(ConstanteUtils.CRON_ATV)).build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            System.out.println("Ocorreu um problema no agendamento");
            e.printStackTrace();
        }
    }

}
