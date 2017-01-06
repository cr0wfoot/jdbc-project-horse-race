/**
 * ******************************************************************************
 * Module: Command.java Author: Hrytsiuk Purpose: Defines the Interface Command
 * ******************************************************************************
 */
package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command Object interface
 */
public interface Command {
    
    /**
     * Execute command according to user's request
     * @param request
     * HTTP servlet request
     * @param response
     * HTTP servlet response
     * @return page redirect to
     */
    void execute(HttpServletRequest request, HttpServletResponse response);
    
}
