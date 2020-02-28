package Music;

import Bot_Main_Stuff.CommandHandler;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class QueueCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    PlayerManager playerManager = PlayerManager.getInstance();
    GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
    BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();


    if (queue.isEmpty()){
      channel.sendMessage("The queue is empty");
      return;
    }

    int trackCount = Math.min(queue.size(),20);
    List<AudioTrack> tracks = new ArrayList<>(queue);
    EmbedBuilder builder = EmbedUtils.defaultEmbed()
        .setTitle("Current Queue(Total:"+queue.size()+")");

    for (int i =0; i<trackCount; i++){
      AudioTrack track =tracks.get(i);
      AudioTrackInfo info =track.getInfo();

      builder.appendDescription(String.format(
         "%s - %s\n",info.title,info.author
      ));
    }
    channel.sendMessage(builder.build()).queue();
  }

  @Override
  public String getHelp() {
    return "Shows the current queue for music player";
  }

  @Override
  public String getInvoke() {
    return "queue";
  }
}
