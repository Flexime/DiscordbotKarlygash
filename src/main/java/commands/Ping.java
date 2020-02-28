package commands;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Ping implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
      event.getChannel().sendMessage("Pong!").queue((message ->
              message.editMessageFormat("Ping is %sms",event.getJDA().getPing()).queue())
      );
  }

  @Override
  public String getHelp() {
    return "Pong!\n" +
            "Usage: `"+ Constants.Prefix +getInvoke()+ "`";
  }

  @Override
  public String getInvoke() {
    return "ping";
  }
}
