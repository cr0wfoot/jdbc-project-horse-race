/**
 * **********************************************************************************
 * Module: ResultParser.java Author: Hrytsiuk Purpose: Defines the Class ResultParser
 * **********************************************************************************
 */
package controller.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;

/**
 * Custom tag, parse an object with data, and views it on page in created table
 */
public class ResultParser extends SimpleTagSupport {
    
    /**
     * Log4j logger
     */
    Logger logger = Logger.getLogger(ResultParser.class.getName());
    
    /**
     * An object with data
     */
    private String result;
    
    /**
     * Set the value of {@link ResultParser#result}
     * @param result
     * the value of result object
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        try {
            String[] parsedResult = result.split("\\[");
            getJspContext().getOut().write("<table><tr><td>");
            if(parsedResult[0].isEmpty()) {
                getJspContext().getOut().write(result.replace("],", "</td></tr></table><table><tr><td>")
                                                     .replace("=[", "</td><td>")
                                                     .replace("];", "</td></tr><tr><td>")
                                                     .replace(", ", "</td></tr><tr><td></td><td>")
                                                     .replace(";", ", ")
                                                     .replace("]]", "").replace("[", ""));
            } else {
                getJspContext().getOut().write(parsedResult[0].replace(";", "</td></tr><tr><td>").replace("=", "</td><td>"));
                if(parsedResult[1] != null) {
                    if(parsedResult[1].contains("null")) {
                        getJspContext().getOut().write(parsedResult[1].replace("];", "</td></tr><tr><td>")
                                                                    .replace(", ", "</td></tr><tr><td></td><td>")
                                                                    .replace("=", " ").replace(";", ", "));
                        getJspContext().getOut().write("</td><td>");
                        getJspContext().getOut().write(parsedResult[2].replace("]", "")); 
                    } else {
                        getJspContext().getOut().write(parsedResult[1].replace("]", "")
                                                                    .replace(", ", "</td></tr><tr><td></td><td>")
                                                                    .replace(";", ", "));
                    }
                }   
            }
            getJspContext().getOut().write("</td></tr></table>");
        } catch (Exception ex) {
            logger.error(ex);
            throw new SkipPageException(ex);
        }
    }

}
