/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ilegra.ilegraapp.application.base;

import org.apache.log4j.Logger;

/**
 *
 * @author dudu
 */
public abstract class BaseHelper {

    protected Logger log;

    public BaseHelper() {
        if (log == null) {
            log = Logger.getLogger(this.getClass());
        }
    }

    /**
     * Logar uma exception pelo log4j.
     *
     * @param e
     */
    public void logarException(Exception e) {
        if (log != null) {
            log.error(e);
        }
    }

    /**
     * Logar uma mensagem pelo log4j.
     *
     * @param message
     */
    public void logarMensagemDebug(String message) {
        if (log != null && log.isDebugEnabled()) {
            log.debug(message);
        }
    }
}
