package Music;

import Bot_Main_Stuff.CommandHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class SkipCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    TextChannel channel =event.getChannel();
    PlayerManager playerManager = PlayerManager.getInstance();
    GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
    TrackScheduler scheduler =musicManager.scheduler;
    AudioPlayer player =musicManager.player;


    if (player.getPlayingTrack() == null){
      channel.sendMessage("The player is not playing anything").queue();
      return;
    }
    scheduler.nextTrack();

    channel.sendMessage("Skipping current track").queue();
  }

  @Override
  public String getHelp() {
    return "Skips current song";
  }

  @Override
  public String getInvoke() {
    return "skip";
  }
}
