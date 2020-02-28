package Bot_Main_Stuff;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {

  private final CommandManager manager ;
  private final Logger logger= LoggerFactory.getLogger(Listener.class);

  Listener(CommandManager manager){
    this.manager=manager;
  }

  @Override
  public void onReady(ReadyEvent event) {

   logger.info(String.format("Logged in as %#s\n",event.getJDA().getSelfUser()));
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    User author =event.getAuthor();
    Message message =event.getMessage();
    String content =message.getContentDisplay();

    if(event.isFromType(ChannelType.TEXT)){
      Guild guild =event.getGuild();
      TextChannel textChannel= event.getTextChannel();


      logger.info(String.format("(%s)[%s]<%#s>: %s", guild.getName(), textChannel.getName(), author, content));
    }else if (event.isFromType(ChannelType.PRIVATE)){
      logger.info(String.format("[PRIVATE]<%#s>: %s\n", author, content));
    }
  }

  @Override
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

    String rw =event.getMessage().getContentRaw();

    if(rw.equalsIgnoreCase(Constants.Prefix+"shutdown")&&
    event.getAuthor().getIdLong()== Constants.Owner){
      shutdown(event.getJDA());
      return;
    }

    String prefixes =Constants.Prefixes.computeIfAbsent(event.getGuild().getIdLong(),(l) -> Constants.Prefix);

    if (!event.getAuthor().isBot() && !event.getMessage().isWebhookMessage() &&
            rw.startsWith(prefixes))
    manager.handleCommand(event);
  }
  private void shutdown(JDA jda){
    jda.shutdown();
    System.exit(0);
  }
}
