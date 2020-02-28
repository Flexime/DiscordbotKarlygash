package events;

        import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
        import  net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Karlygash extends ListenerAdapter {

    public void  onGuildMessageReceived(GuildMessageReceivedEvent e ){
        String[] args = e.getMessage().getContentRaw().split(" ");
        String name = e.getMember().getUser().getName();
        if(args[0].equalsIgnoreCase("Karlygash"))
            if(!e.getMember().getUser().isBot()){
                e.getChannel().sendMessage(name + " Its me yea ^W^" ).queue();
            }
    }
}
