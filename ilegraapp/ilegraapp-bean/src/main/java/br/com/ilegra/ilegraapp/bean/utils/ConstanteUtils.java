package br.com.ilegra.ilegraapp.bean.utils;

import java.util.Date;

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
    public static final String CRON_ATV = "0/$ * * * * ?";

    //Arquivo de configuração inicial
    public static final String NOME_ARQUIVO_CONFIG = "config.properties";

    //Endereco da pasta de entrada (definido assim no doc)
    public static final String ENDERECO_PASTA_IN = "data\\in\\";

    //Endereco da pasta de saida (definido assim no doc)
    public static final String ENDERECO_PASTA_OUT = "data\\out\\";

    //Caractere que delimita uma informação da linha que será importada
    public static final String CARACTERE_DELIMITADOR = "ç";
    
    //Caminho definido na entrada
    public static String CAMINHO = "";

    //Tempo de intervalo de execução definido na entrada
    public static Integer INTERVALO;
    
    //Salva a última data que um processamento foi realizado
    public static Date DATA_ULTIMA_EXECUCAO;
}
