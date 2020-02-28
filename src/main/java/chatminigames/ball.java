package chatminigames;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class ball implements CommandHandler {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        if(message.length==1 && message[0].equalsIgnoreCase("f8ball")&& message[message.length-1].equalsIgnoreCase("?")){
            event.getChannel().sendMessage("Ask 8ball:").queue();
        } else if (message[message.length-1].equalsIgnoreCase("?")) {

            Random rand = new Random();

            int choice = 1 + rand.nextInt(20);
            String response = "";

            if (choice == 1)
                response = "It is certain";
            else if (choice == 2)
                response = "It is decidedly so";
            else if (choice == 3)
                response = "Without a doubt";
            else if (choice == 4)
                response = "Yes - definitely";
            else if (choice == 5)
                response = "You may rely on it";
            else if (choice == 6)
                response = "As I see it, yes";
            else if (choice == 7)
                response = "Most likely";
            else if (choice == 8)
                response = "Outlook good";
            else if (choice == 9)
                response = "Signs point to yes";
            else if (choice == 10)
                response = "Yes";
            else if (choice == 11)
                response = "Reply hazy, try again";
            else if (choice == 12)
                response = "Ask again later";
            else if (choice == 13)
                response = "Better not tell you now";
            else if (choice == 14)
                response = "Cannot predict now";
            else if (choice == 15)
                response = "Concentrate and ask again";
            else if (choice == 16)
                response = "Don't count on it";
            else if (choice == 17)
                response = "My reply is no";
            else if (choice == 18)
                response = "My sources say no";
            else if (choice == 19)
                response = "Outlook not so good";
            else if (choice == 20)
                response = "Very doubtful";
            else
                response = "8-BALL ERROR!";

            event.getChannel().sendMessage("MAGIC 8-BALL SAYS: " + response).queue();
        }
    }

    @Override
    public String getHelp() {
        return "8BALL SAYS:[IMPORT EVIL STRING HERE]";
    }

    @Override
    public String getInvoke() {
        return "ball";
    }
}