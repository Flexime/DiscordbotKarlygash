package Music;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class StopCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    PlayerManager playerManager =PlayerManager.getInstance();
    GuildMusicManager musicManager =playerManager.getGuildMusicManager(event.getGuild());

  musicManager.scheduler.getQueue().clear();
  musicManager.player.stopTrack();
  musicManager.player.setPaused(false);

  event.getChannel().sendMessage("Stopping player").queue();

  }

  @Override
  public String getHelp() {
    return "Stops music player";
  }

  @Override
  public String getInvoke() {
    return "stop";
  }
}
