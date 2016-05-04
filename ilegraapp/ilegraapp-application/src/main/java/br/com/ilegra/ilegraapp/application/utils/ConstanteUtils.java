package br.com.ilegra.ilegraapp.application.utils;

/**
 *
 * @author dudu
 */
public abstract class ConstanteUtils {
    
    //Nome da atividade que será executada pelo scheduller.
    public static final String NOME_ATV = "projetoIlegraTask";
    
    //Grupo da atividade que será executada pelo scheduller.
    public static final String GRUPO_ATV = "grupoIlegraTask";
    
    //Tempo de intervalo de execução da atividade.
    public static final String CRON_ATV = "0/10 * * * * ?";
}
