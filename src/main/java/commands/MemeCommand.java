package commands;

import Bot_Main_Stuff.CommandHandler;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class MemeCommand implements CommandHandler {

  private final Random random;

  public MemeCommand(Random random){
    this.random =random;

  }

  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    WebUtils.ins.getJSONObject("https://api-to.get-a.life/meme").async((json)->{
      if (random.nextInt(2)==1){
        String url = json.getString("url");
        MessageEmbed embed = EmbedUtils.embedImage(url);
        event.getChannel().sendMessage(embed).queue();
        return;
      }
      String text = json.getString("text");
      MessageEmbed embed = EmbedUtils.embedMessage(text);
      event.getChannel().sendMessage(embed).queue();
  });
  }

  @Override
  public String getHelp() {
    return "Memes is real";
  }

  @Override
  public String getInvoke() {
    return "meme";
  }
}
