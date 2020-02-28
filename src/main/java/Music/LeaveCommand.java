package Music;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class LeaveCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    AudioManager audioManager = event.getGuild().getAudioManager();

    if (!audioManager.isConnected()) {
      channel.sendMessage("I'm not connected to a channel").queue();
      return;
    }
    VoiceChannel voiceChannel = audioManager.getConnectedChannel();

    if (!voiceChannel.getMembers().contains(event.getMember())) {
      channel.sendMessage("You have to be in the same channel with me ").queue();
      return;
    }
    audioManager.closeAudioConnection();
    channel.sendMessage("Disconnected").queue();
  }

  @Override
  public String getHelp() {
    return  "Makes the bot leave your channel";
  }

  @Override
  public String getInvoke()
  {
    return "leave";
  }
}
