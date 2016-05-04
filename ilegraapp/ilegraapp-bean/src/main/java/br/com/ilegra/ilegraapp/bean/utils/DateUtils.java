package br.com.ilegra.ilegraapp.bean.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dudu
 */
public abstract class DateUtils {

    /**
     * Método que retorna uma data no formado (dd/MM/yyyy – hh:mm:ss)
     * @param data
     * @return 
     */
    public static String gerarDataFormatada(Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy – hh:mm:ss");
        return dateFormat.format(data);
    }
}
