package ben9583.minecraft;

import ben9583.discord.DiscordBot;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    private final DiscordBot bot;
    private final Server server;

    public PlayerJoinQuitListener(DiscordBot bot, Server server) {
        this.bot = bot;
        this.server = server;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerJoin(PlayerJoinEvent event) {
        if(this.server.getOnlinePlayers().size() == 1) {
            bot.sendMessageToChannel(event.getPlayer().getName() + " logged in. There is now 1 player online.");
        } else {
            bot.sendMessageToChannel(event.getPlayer().getName() + " logged in. There are now " + this.server.getOnlinePlayers().size() + " players online.");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerQuit(PlayerQuitEvent event) {
        if(this.server.getOnlinePlayers().size() - 1 == 1) {
            bot.sendMessageToChannel(event.getPlayer().getName() + " disconnected. There is now "
                    + (this.server.getOnlinePlayers().size() - 1) + " player online.");
        } else {
            bot.sendMessageToChannel(event.getPlayer().getName() + " disconnected. There are now "
                    + (this.server.getOnlinePlayers().size() - 1) + " players online.");
        }
    }

}
