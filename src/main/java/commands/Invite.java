package commands;

import Bot_Main_Stuff.CommandHandler;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;


public class Invite implements CommandHandler {


    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        String[] message= e.getMessage().getContentRaw().split(" ");
        if (message[0].equalsIgnoreCase("finvite") && message.length ==1){
            e.getChannel().sendMessage("To use: finvite  create").queue();
        } else if (message.length >=2 && message[0].equalsIgnoreCase("finvite") && message[1].equalsIgnoreCase("create")) {
            e.getChannel().sendMessage("Awesome " + e.getAuthor().getName() + " You want to invite someone?").queue();
            e.getChannel().sendMessage("Give them this invite link"+e.getGuild().getInvites()).queue();
        }
    }

    @Override
    public String getHelp() {
        return "Gives invite link";
    }

    @Override
    public String getInvoke() {
        return "invite";
    }
}
