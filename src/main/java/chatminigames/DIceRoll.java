package chatminigames;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class DIceRoll implements CommandHandler {

  private final Random dice = new Random();

  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {



  }

  @Override
  public String getHelp() {
    return null;
  }

  @Override
  public String getInvoke() {
    return null;
  }
}
