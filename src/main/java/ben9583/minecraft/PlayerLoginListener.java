package ben9583.minecraft;

import ben9583.discord.DiscordBot;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    private final DiscordBot bot;
    private final Server server;
    private final WhiteListHandler whitelistHandler;

    public PlayerLoginListener(DiscordBot bot, Server server, WhiteListHandler whitelistHandler) {
        this.bot = bot;
        this.server = server;
        this.whitelistHandler = whitelistHandler;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void playerLogin(PlayerLoginEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        if (this.whitelistHandler.checkIfWhitelistContains(uuid) && this.whitelistHandler.checkIfAuthDone(uuid)) {
            if (!this.whitelistHandler.checkIfAllowedToLogin(uuid)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You are not allowed to login; contact a server administrator if you believe this is an error.");
            } else if (!this.whitelistHandler.checkIfAccessVoteCountPositive(uuid)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You are not allowed to login; make sure your vote count is positive in the access-request Discord channel.");
            } else if (!this.whitelistHandler.checkIfConfirmedIpForUser(uuid, event.getRealAddress().getHostAddress())) {
                String authToken = this.whitelistHandler.startIPAuthProcess(uuid, event.getRealAddress().getHostAddress());
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Unknown IP address for this user; please send the following number as private message to the Discord bot: " + authToken);
            }
        } else {
            String authToken = this.whitelistHandler.startAuthProcess(event.getPlayer().getName(), uuid, event.getRealAddress().getHostAddress());
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Your auth token has been created; please send the following number as private message to the Discord Bot: " + authToken);
        }


    }


}
