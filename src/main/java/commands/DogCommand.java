package commands;

import Bot_Main_Stuff.CommandHandler;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class DogCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    WebUtils.ins.getJSONObject("https://random.dog/woof.json").async((json)->{
       String url = json.getString("url");
      MessageEmbed embed = EmbedUtils.embedImage(url);
      event.getChannel().sendMessage(embed).queue();

            });
  }

  @Override
  public String getHelp() {
    return "Shows dogos";
  }

  @Override
  public String getInvoke() {
    return "dog";
  }
}
