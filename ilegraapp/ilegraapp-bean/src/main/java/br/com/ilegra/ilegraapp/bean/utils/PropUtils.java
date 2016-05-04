package br.com.ilegra.ilegraapp.bean.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author dudu
 */
public abstract class PropUtils {

    public static Object lerPropriedades(String enderecoProp, String codigoProp) throws Exception {
        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream(enderecoProp);
        properties.load(fis);

        return (Object) properties.getProperty(codigoProp);
    }

    public static void mudarValorPropriedade(String enderecoProp, String codigoProp, String novoValor) throws Exception {
        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream(enderecoProp);
        properties.load(fis);
        properties.setProperty(codigoProp, novoValor);

        FileOutputStream fos = new FileOutputStream(enderecoProp);
        properties.store(fos, "");
        fos.close();
    }
}
