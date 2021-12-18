package ben9583.minecraft;

import ben9583.discord.DiscordBot;
import ben9583.discord.DiscordBotSettings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 */
public class ChatListener implements Listener {

    private final DiscordBot bot;

    public ChatListener(DiscordBot bot) {
        this.bot = bot;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        //if(event.getMessage().charAt(0) == '@') { // A ping is (probably) being sent
            //int indexOfPound = event.getMessage().indexOf("#");
            //List<User> mentionedUsers = event.

            //if(indexOfPound != -1) {
                //bot.jda
            //}
        //}
        bot.sendMessageToChannel("<" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
    }

}
