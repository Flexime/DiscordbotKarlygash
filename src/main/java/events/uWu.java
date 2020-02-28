package events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import  net.dv8tion.jda.core.hooks.ListenerAdapter;

public class uWu extends ListenerAdapter {

    public void  onGuildMessageReceived(GuildMessageReceivedEvent e ){
        String[] message = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName();
        if(message[0].equalsIgnoreCase("^W^"))
            if(!e.getMember().getUser().isBot()){
                e.getChannel().sendMessage(name + " uWuWuWuWuWu ^W^" ).queue();
            }
    }
}
