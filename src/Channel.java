import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a channel into the chat system
 */

public class Channel {
    private String channelName;
    //all the users who joined the channel
    private List <User> users;
    //all the messages posted in the channel
    private List <Message> messages;

    public Channel(String channelName){
        this.channelName = channelName;
        users = new ArrayList<>();
        messages = new ArrayList<>();
    }

    /**
     * Check if a user has already joined the channel
     * in case false, it adds the user to the channel
     * @param user
     */
    public void onUserJoined(User user){
        if(!users.contains(user)){
            users.add(user);
//            users.forEach((User u)->System.out.println(u.toString()+"toString"));
        }
    }

    /**
     * Check if a user is present in a channel,
     * in case it is the user is removed from the users list
     * @param user
     */
    public void onUserLeft(User user){
        if(users.contains(user)){
            users.remove(user);
        }
    }

    /**
     * Check if the user is inside the channel,
     * if so add the message to the list of messages
     * @param message
     * @return true if the user is inside the channel and the message is added,
     *         false if the user is not in the channel
     */
    public boolean addMessage(Message message){
        if(users.contains(message.getAuthor())){
            messages.add(message);
            return true;
        }
        return false;
    }

    /**
     * Returns the channelName as a string
     * @return channelName String
     */
    public String getChannelName(){
        return channelName;
    }

    /**
     * Returns the users into the channels
     * @return users List
     */
    public List getUsersInChannel(){
        return users;
    }

    /**
     * Returns the list of messages in the channel
     * @return messages List
     */
    public List getMessages(){
        return messages;
    }
}
