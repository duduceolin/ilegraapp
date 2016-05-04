package br.com.ilegra.ilegraapp.application.utils;

import br.com.ilegra.ilegraapp.application.exceptions.ConfigException;
import br.com.ilegra.ilegraapp.application.exceptions.ConfigExceptionFactory;
import br.com.ilegra.ilegraapp.application.exceptions.ServException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author dudu
 */
public class FileUtils {

    public static void criarArquivo(String caminho, String conteudo) throws ServException {
        try {
            PrintStream print = new PrintStream(new FileOutputStream(caminho));

            print.print(conteudo);
            print.close();

        } catch (Exception e) {
            throw new ConfigExceptionFactory().criarException(ConfigException.GR005);
        }
    }

}
