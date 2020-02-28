package commands;

import Bot_Main_Stuff.CommandHandler;
import Bot_Main_Stuff.CommandManager;
import Bot_Main_Stuff.Constants;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;


import java.awt.*;
import java.util.List;
import java.util.Random;

public class help implements CommandHandler {


    private  final Random random= new Random();

    private final CommandManager manager;
    public help(CommandManager manager){

        this.manager=manager;
    }



    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {

        if(args.isEmpty()){
            generateAndSendEmbed(event);
            return;
        }
        String joined =String.join("",args);
        CommandHandler command =manager.getCommand(joined);

        if (command == null){
            event.getChannel().sendMessage("The Command"+joined+ "does not exist\n" +
                    "Use `"+ Constants.Prefix+getInvoke()+"` For a list of commands").queue();
            return;
            }

        String message ="Command help for `"+command.getInvoke()+"`\n"+command.getHelp();
        event.getChannel().sendMessage(message).queue();
    }

    private void generateAndSendEmbed(GuildMessageReceivedEvent event){
        EmbedBuilder builder = EmbedUtils.defaultEmbed()
                .setTitle("A list of all commands:");


        StringBuilder descriptionBuilder = builder.getDescriptionBuilder();

        manager.getCommands().forEach(
                commandHandler -> descriptionBuilder.append("`").append(commandHandler.getInvoke()).append("`\n")

                );
                event.getChannel().sendMessage(builder.build()).queue();
    }

    private Color getRandomColor(){
        float r= random.nextFloat();
        float g= random.nextFloat();
        float b= random.nextFloat();
        return new Color(r,g,b);
    }


    @Override
    public String getHelp() {
        return "Shows a list of all commands\n" +
                "Usage: `" + Constants.Prefix+getInvoke()+ " [command]`";
    }

    @Override
    public String getInvoke() {
        return "help";
    }
}


/**
public class help extends Command {
    public help(){
        this.name = "help";
        this.aliases = new String[]{"help"};
        this.help = "Gives information about commands";
    }

    @Override
    protected  void  execute(CommandEvent e ){
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("fhelp"))
            if(!e.getMember().getUser().isBot()){
                EmbedBuilder helpembed = new EmbedBuilder();

                helpembed.setTitle("My commands");
                helpembed.setColor(Color.ORANGE);
                helpembed.addField("Useful" ,"fhlep,fcalculate,fuser,fserverinfo,finvite",true);
                helpembed.addField("Moderation","work in progress",true);
                helpembed.addField("Music","work in progress",true);
                helpembed.addField("Filter","ffilter",true);
                e.getChannel().sendMessage(helpembed.build()).queue();

            }
    }
}
**/