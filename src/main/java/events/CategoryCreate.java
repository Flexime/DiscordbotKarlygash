package events;

import net.dv8tion.jda.core.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CategoryCreate extends ListenerAdapter {

    public void onCategoryCreate(CategoryCreateEvent e) {
        e.getGuild().getDefaultChannel().sendMessage("someone just created an category!").queue();

    }
}