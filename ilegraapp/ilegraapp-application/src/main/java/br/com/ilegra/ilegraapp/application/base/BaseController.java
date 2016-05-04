package br.com.ilegra.ilegraapp.application.base;

import org.apache.log4j.Logger;



/**
 *
 * @author dudu
 */
public abstract class BaseController{
    
    protected Logger log;
    
    //Método obrigatório em um controlador.
    public abstract void processar();
    
    public BaseController(){
        if (log == null){
            log = Logger.getLogger(this.getClass());
        }
    }
}
