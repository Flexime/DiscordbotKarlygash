package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;


public class Calculate extends  Command  {
    public Calculate(){
        this.name = "calculate";
        this.aliases = new String[]{"calculate"};
        this.help = "Makes simple calculations";
    }

    @Override
    protected void execute (CommandEvent e) {

        String[] message = e.getMessage().getContentRaw().split(" ");


        if(message.length == 1 && message[0].equalsIgnoreCase("fcalculate")){
            e.getChannel().sendMessage("I know how to add and sub.Ask me anything! (without brackets): fcalculate [add/sub/mult/div] [first-num] [second-num]").queue();
        }else if(message[0].equalsIgnoreCase("fcalculate") && message[1].equalsIgnoreCase("add")){
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 + num2)).queue();
        }else if(message[0].equalsIgnoreCase("fcalculate") && message[1].equalsIgnoreCase("sub")) {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 - num2)).queue();
        }else if(message[0].equalsIgnoreCase("fcalculate") && message[1].equalsIgnoreCase("mult")) {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The result is: " + (num1 * num2)).queue();
        }else if(message[0].equalsIgnoreCase("fcalculate") && message[1].equalsIgnoreCase("div")) {
            int num1=Integer.parseInt(message[2]);
            int num2=Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: "+(num1/num2)).queue();
        }
    }
}
