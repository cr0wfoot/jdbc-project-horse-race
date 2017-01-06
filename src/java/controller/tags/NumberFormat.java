/**
 * **********************************************************************************
 * Module: NumberFormat.java Author: Hrytsiuk Purpose: Defines the Class NumberFormat
 * **********************************************************************************
 */
package controller.tags;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;

/**
 * Custom tag, parse the number according to format
 */
public class NumberFormat extends SimpleTagSupport {
    
    /**
     * Log4j logger
     */
    Logger logger = Logger.getLogger(NumberFormat.class.getName());
    
    /**
     * The value of format for number
     */
    private String format;
    
    /**
     * The value of number
     */
    private String number;

    /**
     * Set the value of {@link NumberFormat#format}
     * @param format
     * the value of format for number
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Set the value of {@link NumberFormat#number}
     * @param number
     * the value of number to be formatted
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        try {
            double amount = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat(format);
            String formattedNumber = formatter.format(amount);
            getJspContext().getOut().write(formattedNumber);
        } catch (Exception ex) {
            logger.error(ex);
            throw new SkipPageException(ex);
        }
    }

}
