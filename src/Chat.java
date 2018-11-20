import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Chat {

    private List<Channel> channels;
    private List<User> users;
    private static Bot bot;

    public Chat(){
        channels = new ArrayList<>();
        users = new ArrayList<>();
        bot = new Bot();
    }

    /**
     * Check if the channel is already created,
     * if not add the channel to the list of channels in the chat
     * @param channelName
     * @return the channel if the channel is added, null if the channel has already been created
     */
    public Channel createChannel(String channelName){
        Channel channel = new Channel(channelName);
        for(Channel c: channels){
            if(c.getChannelName().equals(channel.getChannelName())){
                System.out.println("Channel "+channel.getChannelName()+" already exists!");
                return null;
            }
        }

        channels.add(channel);
        channel.onUserJoined(bot);
        return channel;
    }

    /**
     * Check if a user is already created,
     * if not create it adding it to the users list
     * @param username, fullName
     * @return the user if the user is added, null if the user has already been created
     */
    public User createUser(String username, String fullName){
        User user = new User(username,fullName);
        for(User u: users){
            if(u.getUsername().equals(user.getUsername())){
                System.out.println("User "+user.getUsername()+" already exists!");
                return null;
            }
        }

        users.add(user);
        return user;
    }

    /**
     * Takes a user and a channel and join the user into the channel,
     * check if the user has already joined the channel, if not
     * then creates a message from the user and add to the channel
     * @param user
     * @param channel
     */
    public void channelJoinCommand(User user, Channel channel){
        if(user.joinChannel(channel)&&users.contains(user)){
            if(channels.contains(channel)){
                Message nameMessage = new Message(user, user.getFullName()+":");
                Message message = new Message(user,user.getUsername()+" has joined the channel");
                channel.addMessage(nameMessage);
                channel.addMessage(message);
            }else {
                System.out.println("The requested channel doesn't exist!");
            }
        }else if(!users.contains(user)){
            System.out.println("The requested user doesn't exist!");
        }
    }

    /**
     * Takes a user and a channel and calls the leave method on the user who leaves the channel,
     * check if the user has already leaved the channel, if not
     * then create a message grom the user and add to the channel
     * @param user
     * @param channel
     */
    public void channelLeaveCommand(User user, Channel channel){


        if(users.contains(user)){
            if(channels.contains(channel)){
                if(user.leaveChannel(channel)){
                    Message message = new Message(user, user.getUsername()+" has left the channel");
                    channel.addMessage(message);
                }
            }else{
                System.out.println("The requested channel doesn't exist!");
            }
        }else {
            System.out.println("The requested user doesn't exist!");
        }
    }

    /**
     * Takes a channel a message and a user, verifies that the channel contain the message and the user,
     * in this case adds the message to the channel, otherwise prints an error message.
     * @param channel
     * @param user
     * @param messagePost
     */
    public void postMessage(Channel channel, User user, String messagePost){
        if(channels.contains(channel)){
            if(channel.getUsersInChannel().contains(user)){
                Message nameMessage = new Message(user, user.getFullName()+":");
                channel.addMessage(nameMessage);
                Message message = new Message(user, messagePost);
                channel.addMessage(message);
                bot.analyseMessage(channel);
            }else {
                System.out.println("User "+ user.getUsername() + " has not joined channel "+ channel.getChannelName());
            }
        }else if(!channels.contains(channel)){
            System.out.println("The requested channel doesn't exist!");
        }
    }

    /**
     * Takes a channel, check if it is into the list and prints every message in that channel
     * @param channel
     */
    public void printChannelMessages(Channel channel){
        System.out.println("Messages in "+channel.getChannelName()+":");
        if(channels.contains(channel)){
            List <Message> messages = channel.getMessages();
            messages.forEach((Message m)->{
                System.out.println(m.getMessage());
            });
        }
    }
}
