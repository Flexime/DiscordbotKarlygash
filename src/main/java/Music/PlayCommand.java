package Music;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {

    TextChannel channel =event.getChannel();
    if (args.isEmpty()){
      channel.sendMessage("Provide arguments").queue();
      return;
    }
    String input =String.join("",args);
    if (!isUrl(input)&& !input.startsWith("ytsearch")){
      //Youtube api instead
      channel.sendMessage("provide a valid youtube,soundcloud or bandcamp link").queue();
      return;
    }

    PlayerManager manager = PlayerManager.getInstance();

   manager.loadAndPlay(event.getChannel(),input);

  }
  private boolean isUrl(String input){
    try {
      new URL(input);
      return true;
    }catch (MalformedURLException ignored){
      return false;
    }
  }

  @Override
  public String getHelp() {
    return "Plays a song \n" +
            "Usage: " + Constants.Prefix +getInvoke()+ "[song url]" ;
  }

  @Override
  public String getInvoke() {
    return "play";
  }
}
