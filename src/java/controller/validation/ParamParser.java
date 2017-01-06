/**
 * ********************************************************************************
 * Module: ParamParser.java Author: Hrytsiuk Purpose: Defines the Class ParamParser
 * ********************************************************************************
 */
package controller.validation;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 * Params validator: validates parameters according to regex, store all checked
 * params in order, you validate them, so you can take them, parsed to Str, int or float.
 * Works like ResultSet.
 */
public class ParamParser {
    
    /**
     * Regex for email
     */
    private final static String REGEX_EMAIL = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    
    /**
     * Regex for access role
     */
    private final static String REGEX_ACCESS = "^admin|user|bookie$";
    
    /**
     * Regex for password
     */
    private final static String REGEX_PASSWORD = "^[a-z0-9_-]{4,16}$";
    
    /**
     * Regex for coefficients
     */
    private final static String REGEX_FLOAT = "^[1-9]\\.[0-9]+$"; 
    
    /**
     * Regex for natural digits
     */
    private final static String REGEX_NATURAL = "^[1-9][0-9]*$"; 
    
    /**
     * Regex for name
     */
    private final static String REGEX_NAME = "^[А-ЯA-ZЁ][а-яёa-z]+$";
    
    /**
     * Regex for first name and second name
     */
    private final static String REGEX_FULL_NAME = "^[А-ЯA-ZЁ][а-яёa-z]+\\s[А-ЯA-ZЁ][а-яёa-z]+$";
    
    /**
     * Regex for text
     */
    private final static String REGEX_TEXT = "^[А-ЯA-ZЁ][а-яА-ЯёЁa-zA-Z0-9\\s]+$";
    
    /**
     * Regex for date
     */
    private final static String REGEX_DATE = "(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])";
     
    /**
     * Contains HTTP servlet request with all parameters
     */
    private HttpServletRequest request;
    
    /**
     * Constructor with one argument initialize fields 
     * {@link ParamParser#request}, {@link ParamParser#params}
     * 
     * @param request    HTTP servlet request with all parameters
     */
    public ParamParser(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Private method, compare param with chosen regex, and add one to list 
     * {@link ParamParser#params} if validation successful
     * @param param
     * the name of parameter to chose from request
     * @param regex
     * chosen regex
     */
    private String valid(String param, String regex) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(request.getParameter(param)).matches())
            return request.getParameter(param);
        else throw new IllegalArgumentException();
    }
    
    /**
     * Check if param is text
     * @param param
     * the name of parameter to chose from request
     */
    public String text(String param) throws IllegalArgumentException {
        return valid(param, REGEX_TEXT);
    }
    
    /**
     * Check if param is access role
     * @param param
     * the name of parameter to chose from request
     */
    public String access(String param) throws IllegalArgumentException {
        return valid(param, REGEX_ACCESS);
    }
    
    /**
     * Check if param is natural
     * @param param
     * the name of parameter to chose from request
     */
    public int natural(String param) throws IllegalArgumentException {
        return Integer.valueOf(valid(param, REGEX_NATURAL));
    }
    
    /**
     * Check if param is float
     * @param param
     * the name of parameter to chose from request
     */
    public float getFloat(String param) throws IllegalArgumentException {
        return Float.valueOf(valid(param, REGEX_FLOAT));
    }
    
    /**
     * Check if param is date
     * @param param
     * the name of parameter to chose from request
     */
    public String date(String param) throws IllegalArgumentException {
        return valid(param, REGEX_DATE);
    }
    
    /**
     * Check if param is email
     * @param param
     * the name of parameter to chose from request
     */
    public String email(String param) throws IllegalArgumentException {
        return valid(param, REGEX_EMAIL);
    }
    
    /**
     * Check if param is password
     * @param param
     * the name of parameter to chose from request
     */
    public String pass(String param) throws IllegalArgumentException {
        return valid(param, REGEX_PASSWORD);
    }
    
    /**
     * Check if param is name
     * @param param
     * the name of parameter to chose from request
     */
    public String name(String param) throws IllegalArgumentException {
        return valid(param, REGEX_NAME);
    }
    
    /**
     * Check if param is name and surname
     * @param param
     * the name of parameter to chose from request
     */
    public String fullName(String param) throws IllegalArgumentException {
        return valid(param, REGEX_FULL_NAME);
    }
    
    public void checkNotNull(String...params) throws IllegalArgumentException {
        for(String p : params) {
            if(request.getParameter(p) == null)
                throw new IllegalArgumentException();
        }
    }
    
}
