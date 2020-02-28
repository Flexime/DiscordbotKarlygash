package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class reverse extends Command {

    public reverse() {
        this.name = "reverse";
        this.aliases = new String[]{"reverse"};
        this.help = "Writes up down";
    }


    @Override
    protected void execute(CommandEvent e) {

        String[] message = e.getMessage().getContentRaw().split(" ");

        if (message.length == 1 && message[0].equalsIgnoreCase("freverse")) {
            e.getChannel().sendMessage("Reverse:").queue();
        }
        String result = "";

        for (int i = 0; i < message.length; i++) {
            if (i == message.length - 1)
                result = message[i] + result;
            else
                result = " " + message[i] + result;


            e.getChannel().sendMessage(result).queue();


        }
    }
}