package Bot_Main_Stuff;

import Music.*;
import chatminigames.Turrel;
import chatminigames.ball;
import commands.*;
import commands.admin.SetPrefixCommand;
import moderation.BanCommand;
import moderation.KickCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

public class CommandManager {


  private final Map<String, CommandHandler> commands =new HashMap<>();
  CommandManager(Random random){
    addCommand(new Ping());
    addCommand(new help(this));
    addCommand(new CatCommand());
    addCommand(new DogCommand());
    addCommand(new MemeCommand(random));
    addCommand(new UserInfoCommand());
    addCommand(new ServerInfo());
    addCommand(new KickCommand());
    addCommand(new BanCommand());
    addCommand(new SetPrefixCommand());
    addCommand(new JoinCommand());
    addCommand(new LeaveCommand());
    addCommand(new PlayCommand());
    addCommand(new StopCommand());
    addCommand(new QueueCommand());
    addCommand(new SkipCommand());
    addCommand(new NowPlaying());
    addCommand(new ball());
    addCommand(new Ricardo());
    addCommand(new Turrel());
  }

  private void  addCommand(CommandHandler command){
    if(!commands.containsKey(command.getInvoke())){
      commands.put(command.getInvoke(),command);
    }
  }

  public Collection<CommandHandler>getCommands(){

    return commands.values();
  }

  public CommandHandler getCommand(@NotNull String name){

    return commands.get(name);
  }

  void handleCommand(GuildMessageReceivedEvent event){
    final String prefixes=Constants.Prefixes.get(event.getGuild().getIdLong());

    final String[] split =event.getMessage().getContentRaw().replaceFirst(
            "(?i)"+ Pattern.quote(prefixes),"").split("\\s+");
    final String invoke=split[0].toLowerCase();
    if (commands.containsKey(invoke)){
      final List<String> args = Arrays.asList(split).subList(1,split.length);

      event.getChannel().sendTyping().queue();//typing...
      commands.get(invoke).handle(args,event);
    }
  }
    private void shutdown(JDA jda){
    jda.shutdown();
    System.exit(0);
    }
}
