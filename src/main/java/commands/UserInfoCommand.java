package commands;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.Constants;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoCommand implements CommandHandler {

    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        if(args.isEmpty()){
            event.getChannel().sendMessage("Missing arguments,check `" + Constants.Prefix +"help" + getInvoke() +"`").queue();
            return;
        }
        String joined = String.join("",args);
        List<User> foundUsers = FinderUtil.findUsers(joined,event.getJDA());

        if(foundUsers.isEmpty()){
            List<Member> foundMembers =FinderUtil.findMembers(joined,event.getGuild());
            if(foundMembers.isEmpty()) {
                event.getChannel().sendMessage("No user found for `"+joined+"`").queue();
                return;
            }
            foundUsers = foundMembers.stream().map(Member::getUser/*Method reference */).collect(Collectors.toList());
        }
        User user = foundUsers.get(0);
        Member member =event.getGuild().getMember(user);

        MessageEmbed embed = EmbedUtils.defaultEmbed()
                .setColor(member.getColor())
                .setThumbnail(user.getEffectiveAvatarUrl().replace("gif","png"))
                .addField("Username#Discriminator",String.format("%#s",user),false)
                .addField("Display name",member.getEffectiveName(),false)
                .addField("User id +Mention",String.format("%s(%s)",user.getId(),member.getAsMention()),false)
                .addField("Account Created",user.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME),false)
                .addField("Guild Joined",member.getJoinDate().format(DateTimeFormatter.RFC_1123_DATE_TIME),false)
                .addField("Online Status",member.getOnlineStatus().name().toLowerCase().replaceAll("_"," "),false)
                .addField("Bot account",user.isBot()?"Yes":"No",false)
                .build();
        event.getChannel().sendMessage(embed).queue();

    }

    @Override
    public String getHelp() {
        return "Displays information about user.\n" +
                "Usage `" + Constants.Prefix +getInvoke()+"[user name/@user/user id]";
    }

    @Override
    public String getInvoke() {

        return "userinfo";
    }
}


    /**
     *
     * extends Commands
     *
      *
    public UserInfoCommand(){
        super.name = "user-info";
        super.help = "Get some information about a user";
        super.aliases = new String[]{"userinfo"};
        super.category = new Category("Members");
        super.cooldown = 5;
        super.arguments = "[name]";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()){
            event.reply("Provide a name! Like this: fuser-info [name]");
        }else{
            //Gets the member object of the user
            Member name;
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Time formatter
            try{
                name = event.getMessage().getMentionedMembers().get(0);
                EmbedBuilder eb = new EmbedBuilder()
                        .setColor(Color.orange)
                        .setThumbnail("https://cdn.discordapp.com/attachments/532670225225744425/541993930984783882/coffee-fox-teeturtle_1024x1024.jpg")
                        .setAuthor("Information on " + name.getUser().getName(), "http://www.google.com", name.getUser().getAvatarUrl())
                        .setDescription(name.getUser().getName() + " joined on " + name.getJoinDate().format(fmt) + " :clock: ")
                        .addField("Game:", displayGameInfo(name), true)
                        .addField("Status:", name.getOnlineStatus().toString(), true)
                        .addField("Roles:", getRolesAsString(name.getRoles()), true)
                        .addField("Nickname: ", name.getNickname() == null ? "No Nickname" : name.getNickname(), true);
                event.reply(eb.build());
                event.reply(event.getAuthor().getAsMention() + " there you go");
            }catch (IndexOutOfBoundsException ex){
                System.out.println("Exception Occured");
                event.reply("You need to provide the name as a mention.");
            }
        }
    }
    //Display game status
    private String displayGameInfo(Member name){
        try{
            String game = name.getGame().getName();
            return "Playing: " + game;
        }catch (NullPointerException exx){
            return "No Game Being Played";
        }
    }
    //Get roles
    private String getRolesAsString(List rolesList){
        String roles;
        if (!rolesList.isEmpty()){
            Role tempRole = (Role) rolesList.get(0);
            roles = tempRole.getName();
            for (int i = 1; i < rolesList.size(); i++){
                tempRole = (Role) rolesList.get(i);
                roles = roles + ", " + tempRole.getName();
            }
        }else{
            roles = "No Roles";
        }
        return roles;
    }
}*/