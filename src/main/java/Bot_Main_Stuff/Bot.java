package Bot_Main_Stuff;
import javax.security.auth.login.LoginException;

import chatminigames.GuessNumber;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.*;
import events.*;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
import java.awt.*;
import java.time.Instant;




public class Bot extends ListenerAdapter {
private final Random random = new Random();
private Bot() {






        CommandManager commandManager = new CommandManager(random);
        Listener listener = new Listener(commandManager);
        Logger logger = LoggerFactory.getLogger(Bot.class);

        WebUtils.setUserAgent("Mozilla/5.0 Caroline/Flexime#5157");

        EmbedUtils.setEmbedBuilder(
                ()->new EmbedBuilder()
                        .setColor(getRandomColor())
                        .setFooter("Aperture Science",null)
                        .setTimestamp(Instant.now())
        );

        try {
            logger.info("Booting");
            new JDABuilder(AccountType.BOT)
                    .setToken(Secrets.Token)
                    .setGame(Game.streaming("Test chambers",""))
                    .addEventListener(listener)
                    .build().awaitReady();
            logger.info("Running");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        new Bot();

        JDA jda = new JDABuilder(AccountType.BOT).setToken(Secrets.Token).build();
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new CategoryCreate());
        jda.addEventListener(new Good());
        jda.addEventListener(new Karlygash());
        jda.addEventListener(new uWu());
        jda.addEventListener(new FilterOnOff());
        jda.addEventListener(new Filter());
        jda.addEventListener(new FilterMessage());
        jda.addEventListener(new Chat());


        CommandClientBuilder builder = new CommandClientBuilder();
      //  builder.setOwnerId("537648917815230485");
    //    builder.setPrefix("f!");
      //  builder.setHelpWord("helpme");


        //==============Useful=========================
      //  builder.addCommand(new ServerInfo());
        builder.addCommand(new Calculate());
      //  builder.addCommand(new Invite());
      //  builder.addCommand(new UserInfoCommand());
        builder.addCommand(new GuessNumber());
     //   builder.addCommand(new ball());
        builder.addCommand(new reverse());
        CommandClient client = builder.build();

        jda.addEventListener(client);

    }
    private Color getRandomColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }
}