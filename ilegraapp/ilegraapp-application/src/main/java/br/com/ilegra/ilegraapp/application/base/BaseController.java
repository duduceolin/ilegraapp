package br.com.ilegra.ilegraapp.application.base;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;



/**
 *
 * @author dudu
 */
public abstract class BaseController{
    
    protected Logger log = Logger.getLogger(this.getClass());;
    
    //Método obrigatório em um controlador.
    public abstract void processar();
    
}
