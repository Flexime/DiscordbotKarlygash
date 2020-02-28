package chatminigames;

import Bot_Main_Stuff.CommandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Turrel implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    EmbedBuilder eb=new EmbedBuilder();
    eb.setThumbnail("https://cdn.dribbble.com/users/358029/screenshots/1436665/turret-final.gif");
    event.getChannel().sendMessage(eb.build()).queue();
    event.getChannel().sendMessage("https://cdn.dribbble.com/users/358029/screenshots/1436665/turret-final.gif").queue();
    event.getChannel().sendMessage(event.getAuthor().getAsMention()+"Are you still there ?").queue();
  }

  @Override
  public String getHelp() {
    return "deploys turret";
  }

  @Override
  public String getInvoke() {
    return "tr";
  }
}
