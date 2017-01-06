/**
 * ***************************************************************************
 * Module: Message.java Author: Hrytsiuk Purpose: Defines the Class Message
 * ***************************************************************************
 */
package model.entities;

/**
 * Describes entity Message
 */
public class Message {
    
    /**
     * The value of ID message
     */
    private int id;
    
    /**
     * The value of ID user which message belongs to
     */
    private int userId;
    
    /**
     * The value of message content
     */
    private String content;

    /**
     * Get the value of message's ID
     * @return the value of {@link Message#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of {@link Message#id}
     * @param id
     * the value of messages's ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get the value of user's ID
     * @return the value of {@link Message#userId}
     */
    public int getIdUser() {
        return userId;
    }

    /**
     * Set the value of {@link Message#userId}
     * @param id
     * the value of user's ID
     */
    public void setIdUser(int id) {
        this.userId = id;
    }

    /**
     * Get the value of message's content
     * @return the value of {@link Message#content}
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the value of {@link Message#content}
     * @param content
     * the value of content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Empty constructor
     */
    public Message() {
        
    }
    
    /**
     * Constructor with three arguments initialize fields 
     * {@link Message#id}, {@link Message#userId}, {@link Message#content}
     * 
     * @param id    the int value of message's ID
     * @param userId    the int value of user's ID
     * @param content    the String value of content
     */
    public Message(int id, int userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }   
    
    @Override
    public String toString() {
        return "ID=" + id + ";user=" + userId + ";content=" + content;
    }
    
}
