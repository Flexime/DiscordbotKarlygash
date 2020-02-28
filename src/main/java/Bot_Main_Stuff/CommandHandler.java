package Bot_Main_Stuff;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public interface CommandHandler {

  void handle(List<String> args, GuildMessageReceivedEvent event);

  String getHelp();

  String getInvoke();

}