package commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Filter extends  ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (FilterOnOff.filterOn == true) {
            String[] LIST_OF_BAD_WORDS = {"anal", "anus", "arse", "ass", "motherfucker", "balls", "bastard", "bitch", "blowjob", "blow job", "buttplug", "cock", "coon", "cunt", "dildo", "fag", "dyke", "fucking", "nigger", "Goddamn", "jizz", "nigga", "pussy", "shit", "whore"};
            String[] message = e.getMessage().getContentRaw().split(" ");
            for (int i = 0; i < message.length; i++) {
                boolean badWord = false;
                //Check each message for each bad word
                for (int b = 0; b < LIST_OF_BAD_WORDS.length; b++) {
                    if (message[i].equalsIgnoreCase(LIST_OF_BAD_WORDS[b])) {
                        e.getMessage().delete().queue();
                        badWord = true;
                        if (FilterMessage.allowed == true) {
                            e.getChannel().sendMessage("Dont say that word!Baka!" + e.getMember().getUser().getName()).queue();
                        }
                    }
                }
                System.out.println(message[i] + " " + badWord); //report for console
            }
        }
    }
}