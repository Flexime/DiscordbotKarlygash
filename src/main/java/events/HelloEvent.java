package events;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import  net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

    public void  onGuildMessageReceived(GuildMessageReceivedEvent e ){
        String[] args = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName();

        if(args[0].equalsIgnoreCase("hello"))
            if(!e.getMember().getUser().isBot()){
                e.getChannel().sendMessage("hello! " + name).queue();
            }
    }
}
