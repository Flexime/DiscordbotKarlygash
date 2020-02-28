package chatminigames;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GuessNumber extends Command {

    public GuessNumber() {
        this.name = "guess";
        this.aliases = new String[]{"number"};
        this.help = "Thinks over some random number";
    }

    @Override
    protected void execute(CommandEvent event) {
        int number = (int) (Math.random() * 101);
        int count = 0;
        String[] message = event.getMessage().getContentRaw().split(" ");

        if (message.length == 1 && message[0].equalsIgnoreCase("fguess")) {
            event.getChannel().sendMessage("Guess a magic number between 0 and 100").queue();
        } else if (message[1].equalsIgnoreCase("is")) {

            int guess = Integer.parseInt(message[2]);

            while (guess !=number ) {

                count++;

            if (guess == number) {
                event.getChannel().sendMessage("Yes, the number is " + number).queue();
                event.getChannel().sendMessage("Your tries" + guess).queue();
            }else if (guess > number) {
                event.getChannel().sendMessage("Your guess is too high").queue();
            } else
                event.getChannel().sendMessage("Your guess is too low").queue();


            }
        }
    }
}

