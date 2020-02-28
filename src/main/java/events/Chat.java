package events;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import  net.dv8tion.jda.core.hooks.ListenerAdapter;
    public class Chat extends ListenerAdapter {

        public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
            String[] message = e.getMessage().getContentRaw().split(" ");
            String name = e.getMember().getUser().getName();

                if (message[0].equalsIgnoreCase("How") && message[1].equalsIgnoreCase("are")
                        && message[2].equalsIgnoreCase("you") && message[3].equalsIgnoreCase("?")) {
                    if (!e.getMember().getUser().isBot()){
                  e.getChannel().sendMessage("Not bad. I forgot how good you are at this. You should pace yourself, though. We have A LOT of tests to do.").queue();
                } else if (message[0].equalsIgnoreCase("hello")) {
                    if (!e.getMember().getUser().isBot()) {
                        e.getChannel().sendMessage("Oh... It's you." + name).queue();
                    } else if (message[0].equalsIgnoreCase("Caroline") && message[1].equalsIgnoreCase("?"))
                        if (!e.getMember().getUser().isBot()) {
                            e.getChannel().sendMessage(name + "One moment.").queue();
                        } else if (message[0].equalsIgnoreCase("hey"))
                            if (!e.getMember().getUser().isBot()) {
                                e.getChannel().sendMessage(name + "Well, I'm back").queue();
                            }
                }
            }
        }
    }