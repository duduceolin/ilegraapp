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
}
