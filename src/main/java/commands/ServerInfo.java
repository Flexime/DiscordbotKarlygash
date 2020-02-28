package commands;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ServerInfo implements CommandHandler {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        Guild guild =event.getGuild();

        String generalInfo = String.format(
                "**Owner**:<@%s>\n**Region**: %s\n**Creation Date**: %s\n**Verification Level**: %s",
                guild.getOwnerId(),
                guild.getRegion(),
                guild.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME),
                convertVerificationLVL(guild.getVerificationLevel())
        );

        String memberinfo = String.format(
                "**Total Roles**: %s\n**Total Members**: %s\n**Online Members: %s\n**Offline Member**:%s\n**Bot Count**: %s",
                guild.getRoleCache().size(),
                guild.getMemberCache().size(),
                guild.getMemberCache().stream().filter((m)->m.getOnlineStatus()== OnlineStatus.ONLINE).count(),
                guild.getMemberCache().stream().filter((m)->m.getOnlineStatus()== OnlineStatus.OFFLINE).count(),
                guild.getMemberCache().stream().filter((m)->m.getUser().isBot()).count()
        );
        EmbedBuilder embed = EmbedUtils.defaultEmbed()
                .setTitle("Server info for: "+guild.getName())
                .setThumbnail(guild.getIconUrl())
                .addField("Geberal info",generalInfo,false)
                .addField("Role and Member Counts",memberinfo,false)
                ;

        event.getChannel().sendMessage(embed.build()).queue();
    }

    @Override
    public String getHelp() {
        return "Displays information about server.\n" +
                "Usage `" + Constants.Prefix +getInvoke()+"[server name]";
    }

    @Override
    public String getInvoke() {

        return "serverinfo";
    }

    private  String convertVerificationLVL(Guild.VerificationLevel lvl){
        String[] names = lvl.name().toLowerCase().split("");
        StringBuilder out = new StringBuilder();

        for(String name: names){
            out.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).append(" ");
        }
        return out.toString().trim();
    }
}