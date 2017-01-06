/**
 * *******************************************************************************************
 * Module: GetRedirect.java Author: Hrytsiuk Purpose: Defines the Class GetRedirect
 * *******************************************************************************************
 */
package controller.command.concretes;

import controller.command.AbstractCommand;

/**
 * Command: redirect (no DB changes)
 */
public class GetRedirect extends AbstractCommand {
     
    /**
     * Get url, where to redirect
     */
    @Override                
    protected void doOperation() { 
        resultURL = getURL();
    }
    
}
