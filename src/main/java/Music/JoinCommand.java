package Music;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.GuildVoiceState;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class JoinCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {

    TextChannel channel =event.getChannel();
    AudioManager audioManager =event.getGuild().getAudioManager();
   if(audioManager.isConnected()){
     channel.sendMessage("I'm already connected to a channel").queue();
   }

    GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

   if(!memberVoiceState.inVoiceChannel()){
     channel.sendMessage("Join to voice channel first").queue();
   }

    VoiceChannel voiceChannel = memberVoiceState.getChannel();
    Member selfMember =event.getGuild().getSelfMember();

    if (!selfMember.hasPermission(voiceChannel,Permission.VOICE_CONNECT)){
      channel.sendMessageFormat("I'm missing permission to join $s",voiceChannel).queue();
      return;
    }
    audioManager.openAudioConnection(voiceChannel);
    channel.sendMessage("Joining your voice channel").queue();
  }

  @Override
  public String getHelp() {
    return "Makes bot join channel";
  }

  @Override
  public String getInvoke() {
    return "join";
  }
}
