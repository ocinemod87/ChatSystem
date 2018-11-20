import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single user in the Chat system.
 */

public class User {
    private String username;
    private String fullName;
    //A list of the channels the user has joined
    private List <Channel> channels;
    private int notificationNumber;

    public User (String username, String fullName){
        this.username = username;
        this.fullName = fullName;
        channels = new ArrayList();
    }

    /**
     * Check if the user already joined a channel if not
     * adds a channel to the list of channels the user has joined.
     * @param channel that will be added to the list
     * @return true if the channel is added to the list,
     * false if the user has already joined the channel
     */
    public boolean joinChannel (Channel channel){
        if(channels.contains(channel)){
            return false;
        }else {
            channels.add(channel);
            channel.onUserJoined(this);
            return true;
        }
    }

    /**
     *Check if the user has joined the channel,
     * if so it removes the channel from the user list (the user leaves the channel)
     * and returns true, otherwise returns false.
     * @param channel that will be leaved
     * @return true if the channel is leaved, false if the user list does not contain that channel
     */
    public boolean leaveChannel(Channel channel){
        if(channels.contains(channel)){
//            System.out.println("Remove true");
            channels.remove(channel);
            channel.onUserLeft(this);
            return true;
        }else{
//            System.out.println("Remove false");
            return false;
        }
    }

    /**
     * Print the channel that a user has joined
     */
    public void printChannelsJoined(){
        System.out.print("User "+ username +" has joined the following channels:");
        channels.forEach((Channel c) -> {
            System.out.print(" "+c.getChannelName());
        });
    }

    /**
     * Return the unique username in a String
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    public String getFullName(){
        return fullName;
    }

    /**
     *increment the unread notification int
     */
    public void incrementUnreadMessages(){
        notificationNumber += 1;
    }

    /**
     * Print welcome to the user, it say to him how many unread notification has and clear the unread notification
     * when it logs in
     */
    public void onLogin(){
        System.out.println("Hi, "+fullName);
        System.out.println("You have "+notificationNumber+" unread messages.");
        notificationNumber = 0;
    }
    /**
     * Return the full name String of the user
     * @return The full name of the user
     */
    public String toString(){
        return fullName;
    }
}
