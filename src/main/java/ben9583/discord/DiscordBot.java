package ben9583.discord;

import ben9583.discord.commands.*;
import ben9583.discord.handler.MessageHandler;
import com.github.rainestormee.jdacommand.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

/**
 *
 */
public class DiscordBot {

    public static final DiscordBotSettings BOTSETTINGS = new DiscordBotSettings();
    private static final CommandHandler<Message> COMMANDHANDLER = new CommandHandler<Message>();
    private JDA jda;
    private Logger logger;

    /**
     * @param token the token of this Discord bot
     * @param logger where events will be logged
     */
    public DiscordBot(String token, Logger logger) {
        try {
            this.logger = logger;
            COMMANDHANDLER.registerCommands(new Set(), new Ping(), new Version(), new Players(), new Time());
            this.jda = JDABuilder.createDefault(token).addEventListeners(new MessageHandler(COMMANDHANDLER)).build();
            this.jda.awaitReady();
            sendMessageToChannel(DiscordBotSettings.getServerName() + ": online");
        } catch (LoginException e) {
            this.logger.severe("Logging to the Discord was not successful. Please check, is the token valid.");
        } catch (InterruptedException e) {
            this.logger.severe("Error waiting JDA to load.");
        }
    }

    /**
     * @param message the message to be sent to the configured channel
     */
    public void sendMessageToChannel(String message) {
        if (DiscordBotSettings.discordChannelIsSet()) {
            TextChannel textChannel = this.jda.getTextChannelById(DiscordBotSettings.getDiscordChannelID());
            if (textChannel != null) {
                textChannel.sendMessage(message).queue();
            } else {
                this.logger.warning("Could not find channel id: " + DiscordBotSettings.getDiscordChannelID());
            }
        } else {
            this.logger.warning("You have not set integrated Discord channel ID.");
        }
        //jda.
    }

    /**
     * @param message the message to be sent to the configured channel
     */
    public void sendMessageToChannelAndWait(String message) {
        if (DiscordBotSettings.discordChannelIsSet()) {
            TextChannel textChannel = this.jda.getTextChannelById(DiscordBotSettings.getDiscordChannelID());
            if (textChannel != null) {
                textChannel.sendMessage(message).complete();
            } else {
                this.logger.warning("Could not find channel id: " + DiscordBotSettings.getDiscordChannelID());
            }
        } else {
            this.logger.warning("You have not set integrated Discord channel ID.");
        }
    }

    /**
     * @param discordID the ID of a discord member
     * @return whether or not this member's role was added
     */
    public boolean addDefaultRoleToUser(String discordID) {
        if (DiscordBotSettings.ChannelRoleIsSet() && DiscordBotSettings.discordChannelIsSet()) {
            TextChannel textChannel = this.jda.getTextChannelById(DiscordBotSettings.getDiscordChannelID());
            Role role = textChannel.getGuild().getRoleById(DiscordBotSettings.getChannelRoleID());
            if (role != null) {
                textChannel.getGuild().addRoleToMember(discordID, role).queue();
                return true;
            }
        } else {
            this.logger.warning("You have not set integrated roles ID");
        }
        return false;
    }

    /**
     * @param discordID the ID of a discord member
     * @return whether or not this member's role was removed
     */
    public boolean removeDefaultRoleToUser(String discordID) {
        if (DiscordBotSettings.ChannelRoleIsSet() && DiscordBotSettings.discordChannelIsSet()) {
            TextChannel textChannel = this.jda.getTextChannelById(DiscordBotSettings.getDiscordChannelID());
            Role role = textChannel.getGuild().getRoleById(DiscordBotSettings.getChannelRoleID());
            if (role != null) {
                textChannel.getGuild().removeRoleFromMember(discordID, role).queue();
                return true;
            }
        } else {
            this.logger.warning("You have not set integrated roles ID");
        }
        return false;
    }

    public void shutConnection() {
        sendMessageToChannelAndWait(DiscordBotSettings.getServerName() + ": offline");
        this.jda.shutdownNow();
    }

}
