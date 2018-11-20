import java.util.ArrayList;
import java.util.List;

public class Bot extends User {
    private Message message;
    private List<String> containedWord;

    public Bot(){
        super("rocket.cat","Rocket.cat");
        containedWord = new ArrayList<>();
        containedWord.add("Hi ");
        containedWord.add("Hi");
        containedWord.add("Hi, ");
        containedWord.add("Hello ");
        containedWord.add("Hello, ");
    }

    /**
     * Analyze the last message in a channel and perform operation dependent on this message
     * @param channel
     */
    public void analyseMessage(Channel channel){
        List<Message> messages = channel.getMessages();

        messages.forEach((Message m)->{
            message = m;
        });

        //check if the message is not from the Bot itself
        if(message.getAuthor() != this){

            //check if the message contains one of the words into the list, if yes it welcome the user
            for(String s: containedWord){
                if(message.getMessage().contains(s)){
                    Message messageUser = new Message(this, "Rocket.Cat:");
                    Message messageAnswer = new Message(this, "Howdy, "+message.getAuthor().getFullName());
                    channel.addMessage(messageUser);
                    channel.addMessage(messageAnswer);
                }
            }

            //gets the list of users in the channel
            List<User> users = channel.getUsersInChannel();
            for(User u: users){
                String userHi = "`"+u.getUsername();

                //if the message contains a ` followed by a username, notifies the user
                if(message.getMessage().contains(userHi)){
                    u.incrementUnreadMessages();
                }
            }

            //if the message contains a `all it notifies all the users in the channel
            if(message.getMessage().contains("`all")){
                for(User u: users){
                    u.incrementUnreadMessages();
                }
            }
        }
    }
}
