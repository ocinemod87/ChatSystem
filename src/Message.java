/**
 * This class represents a single message posted by a user
 */

public class Message {
    private User author;
    private String message;

    public Message(User user, String message){
        author = user;
        this.message = message;
    }

    /**
     * Print the author and the message and return the resulting String
     * @return the String of the author and his message
     */
    public String toString(){
        //not sure is like that
        System.out.println(author.toString() + ":");
        System.out.println(message);
        return "";
    }

    /**
     * Return the author of the message in a String
     * @return The author of the message
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Return the message posted in a String
     * @return The message posted
     */
    public String getMessage() {
        return message;
    }
}
