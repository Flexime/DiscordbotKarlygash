package events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import  net.dv8tion.jda.core.hooks.ListenerAdapter;
public class Good extends ListenerAdapter {

    public void  onGuildMessageReceived(GuildMessageReceivedEvent e ){
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("How") && message[1].equalsIgnoreCase("are")
        && message[2].equalsIgnoreCase("you") && message[3].equalsIgnoreCase("?"))
            if(!e.getMember().getUser().isBot()){
                e.getChannel().sendMessage("Better than you" ).queue();
            }
    }
}