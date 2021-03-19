package ben9583.minecraft;

import ben9583.Ben9583;
import ben9583.discord.DiscordBot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

/**
 *
 */
public class ServerCommandListener implements Listener {

    private final DiscordBot bot;

    /**
     * @param bot
     */
    public ServerCommandListener(DiscordBot bot) {
        this.bot = bot;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerChat(ServerCommandEvent event) {
        if (event.getCommand().toLowerCase().startsWith("say") && event.getCommand().length() >= 4 && !event.getCommand().substring(4).toLowerCase().startsWith(Ben9583
                .getPlugin(Ben9583.class).getConfig().getString("integration.serverSayMessageFilterPrefix"))) {
            bot.sendMessageToChannel("<Server> " + event.getCommand().substring(4));
        }

    }

}