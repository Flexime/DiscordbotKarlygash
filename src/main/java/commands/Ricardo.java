package commands;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Ricardo implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {


    EmbedBuilder eb=new EmbedBuilder();
    eb.setThumbnail("https://j.gifs.com/GZLqxK.gif");
    event.getChannel().sendMessage(eb.build()).queue();
    event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();

  }

  @Override
  public String getHelp() {

    return "Sends Ricardo";
  }

  @Override
  public String getInvoke() {

    return "ricardo";
  }
}
