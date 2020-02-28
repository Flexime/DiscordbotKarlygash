package commands;

import Bot_Main_Stuff.CommandHandler;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class CatCommand implements CommandHandler {

  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {

    WebUtils.ins.scrapeWebPage("https://api.thecatapi.com/api/images/get?format=xml&results_per_pahe_1").async((document )-> {

      String url = document.getElementsByTag("url").first().html();
      MessageEmbed embed = EmbedUtils.embedImage(url);
      event.getChannel().sendMessage(embed).queue();
    });

  }

  @Override
  public String getHelp() {
    return "Show you cat";
  }

  @Override
  public String getInvoke() {

    return "cat";
  }
}
