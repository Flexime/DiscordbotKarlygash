package Music;

import Bot_Main_Stuff.CommandHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NowPlaying implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    TextChannel channel =event.getChannel();
    PlayerManager playerManager = PlayerManager.getInstance();
    GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
    TrackScheduler scheduler =musicManager.scheduler;
    AudioPlayer player =musicManager.player;

    if (player.getPlayingTrack()==null){
      channel.sendMessage("Player is not playing any song ").queue();
      return;
    }

    AudioTrackInfo info =player.getPlayingTrack().getInfo();

    channel.sendMessage(EmbedUtils.embedMessage(String.format(
            "Playing [&s](%s)\n%s %s - %s",
            info.title,
            info.uri,
            player.isPaused()? "\u23F8":"",

            formatTime(player.getPlayingTrack().getPosition()),
            formatTime(player.getPlayingTrack().getDuration())

    ))).queue();
  }

  @Override
  public String getHelp() {
    return "Shows currently plaing track";
  }

  @Override
  public String getInvoke() {
    return "np";
  }

  private String formatTime(long timeinMillis){
    final long hours =timeinMillis/ TimeUnit.MINUTES.toMillis(1);
    final long minutes =timeinMillis/TimeUnit.MINUTES.toMillis(1);
    final long seconds =timeinMillis % TimeUnit.MINUTES.toMillis(1)/TimeUnit.SECONDS.toMillis(1);

    return String.format("%02d:%02d:%02d",hours,minutes,seconds);
  }

}
