package commands;


import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class FilterMessage extends ListenerAdapter {
    public static boolean allowed = false;

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(FilterOnOff.filterOn == true) {
            if (e.getMessage().getContentRaw().equalsIgnoreCase("ffiltermessage") && !allowed == true) {
                e.getChannel().sendMessage("Filter Response Has Been Enabled.").queue();
                System.out.println("Enabled");
                allowed = false;
            } else if (e.getMessage().getContentRaw().equalsIgnoreCase("ffiltermessage") && allowed == false) {
                e.getChannel().sendMessage("Filter Response Has Been Disabled.").queue();
                System.out.println("Disabled");
                allowed = true;
            }
        }else if(e.getMessage().getContentRaw().equalsIgnoreCase("ffiltermessage") && !FilterOnOff.filterOn == false){
            e.getChannel().sendMessage("You can't toggle filter response while the filter is off. To turn the filter on, run ftogglefilter").queue();
        }


    }

}