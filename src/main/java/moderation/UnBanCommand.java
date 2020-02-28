package moderation;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class UnBanCommand implements CommandHandler {
  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {

    TextChannel channel = event.getChannel();

    if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
      channel.sendMessage("You need the Ban Members permission to use this command").queue();
      return;
    }
    if (!event.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
      channel.sendMessage("I need the Ban Members permission to unban members").queue();
      return;
    }
    if (args.isEmpty()){
      channel.sendMessage("Usage: " + Constants.Prefix+ getInvoke()+"[user/user id /username#disc]").queue();
      return;
    }
    String argsJoined = String.join(" ",args);

    event.getGuild().getBanList().queue(bans -> {


      List<User> goodUser =bans.stream().filter(ban -> isCorrectUser(ban,argsJoined))
              .map(Guild.Ban::getUser).collect(Collectors.toList());

      if (goodUser.isEmpty()) {
        channel.sendMessage("This is not banned").queue();
        return;
      }
      User target = goodUser.get(0);

      String mod = String.format("%#s",event.getAuthor());
      String bannedUser = String.format("%#s",event.getAuthor());

      event.getGuild().getController().unban(target).reason("Unbanned by:"+mod).queue();

      channel.sendMessage("User"+bannedUser+"unbanned.").queue();

    });
  }
  @Override
  public String getHelp() {
    return "Unbans a member from the server\n"+
            "Usage: "+ Constants.Prefix+getInvoke() +"[username/user id/username#disc]";
  }

  @Override
  public String getInvoke() {
    return "unban";
  }
  private boolean isCorrectUser(Guild.Ban ban,String arg){
    User bannedUser=ban.getUser();
    return  bannedUser.getName().equalsIgnoreCase(arg)|| bannedUser.getId().equals(arg)
            || String.format("%^s",bannedUser).equalsIgnoreCase(arg);
  }
}
