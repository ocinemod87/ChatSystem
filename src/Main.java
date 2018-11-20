public class Main {
    public static void main(String[] args) {

        User u1,u2,u3,trbj,wilr,ivml,vlli,lisj,saram,mdho,sahu;
        Channel c1,c2;
        Message m1,m2;
        Chat chat;
        chat = new Chat();
        c1 = chat.createChannel("gp-autumn-2018-ta");
        trbj = chat.createUser("trbj", "Troels Bjerre Lund");
        chat.channelJoinCommand(trbj, c1);
        chat.postMessage(c1, trbj, "Hi");
        chat.postMessage(c1, trbj, "Hello Rocket Cat! Have you seen the TAs?");
        chat.postMessage(c1, trbj, "... guess not");
        chat.printChannelMessages(c1);

    }
}
