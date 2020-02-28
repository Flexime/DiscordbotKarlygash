package moderation;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BanCommand implements CommandHandler {

  @Override
  public void handle(List<String> args, GuildMessageReceivedEvent event) {
    String reason = String.join("", args.subList(1, args.size()));
    TextChannel channel = event.getChannel();
    Member member = event.getMember();
    Member selfMember = event.getGuild().getSelfMember();
    List<Member> mentionMembers = event.getMessage().getMentionedMembers();

    if (mentionMembers.isEmpty() || args.size() < 2) {


      channel.sendMessage("Missing arguments").queue();
      return;
    }
    Member target = mentionMembers.get(0);

    if (!member.hasPermission(Permission.BAN_MEMBERS) && !member.canInteract(target)) {
      channel.sendMessage("You dont have permission to use this command").queue();
      return;
    }
    if (!selfMember.hasPermission(Permission.BAN_MEMBERS) && !selfMember.canInteract(target)) {
      channel.sendMessage("Can't kick that member of I don't have the ban member permission").queue();
      return;

    }

    event.getGuild().getController().ban(target,1,String.format("Kick by: %with reason %s",event.getAuthor()
            ,reason)).queue();

    channel.sendMessage("Success!").queue();
  }
  @Override
  public String getHelp() {
    return "kicks off the server\n" +
            "Usage : " + Constants.Prefix + getInvoke() + "[user] [reason]";
  }

  @Override
  public String getInvoke() {
    return "ban";
  }
}
