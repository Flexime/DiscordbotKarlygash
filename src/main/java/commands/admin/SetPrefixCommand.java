package commands.admin;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.util.List;

public class SetPrefixCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    Member member = event.getMember();
    TextChannel channel =event.getChannel();

    if (!member.hasPermission(Permission.MANAGE_CHANNEL)){
      channel.sendMessage("You need the Manage Server permission to use the command").queue();
      return;
    }
    if (args.isEmpty()){
      channel.sendMessage("Usage: "+Constants.Prefix+getInvoke()+"[prefix]").queue();
      return;
    }
    String newPrefix =args.get(0);

    Constants.Prefixes.put(event.getGuild().getIdLong(),newPrefix);

    channel.sendMessage("New prefix set to: " + newPrefix + " ").queue();

  }

  @Override
  public String getHelp() {
    return "Sets the prefix for this server\n+" +
            "Usage: " + Constants.Prefix+getInvoke()+"[prefix]";
  }

  @Override
  public String getInvoke() {
    return "setprefix";
  }
}
