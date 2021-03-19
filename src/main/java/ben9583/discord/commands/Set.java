package ben9583.discord.commands;

import ben9583.discord.DiscordBotSettings;
import ben9583.discord.commands.settings.*;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import com.github.rainestormee.jdacommand.CommandHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "set", triggers = {"set", "settings"}, description = "Sets bot settings.", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class Set implements AbstractCommand<Message> {

    private static final CommandHandler<Message> SETTINGSHANDLER = new CommandHandler<>();

    public Set() {
        SETTINGSHANDLER.registerCommands(new SetIntegratedChannel(), new SetAdvancementsToDiscord(),
                new SetDeathMessagesToDiscord(), new SetDiscordToMinecraftChat(), new SetJoinQuitMessagesToDiscord(),
                new SetMinecraftChatToDiscord(), new SetServerSayMessagesToDiscord(), new SetCommandPrefix(),
                new SetAccessRequestChannel(), new SetDefaultRole(), new SetServerName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        String[] splitMessage = args.split("\\s+", 2);
        AbstractCommand<Message> command = SETTINGSHANDLER.findCommand(splitMessage[0].toLowerCase());
        if (command == null) {
            return;
        }

        if (!checkCommandExecuteRights(command, message)) {
            return;
        }

        if (!message.getChannel().getId().equals(DiscordBotSettings.getDiscordChannelID()) && !command.hasAttribute("canUseOnAnyChannel")) {
            return;
        }

        SETTINGSHANDLER.execute(command, message, splitMessage.length > 1 ? splitMessage[1] : "");
    }

    /**
     * @param command the command who's rights should be checked
     * @param message the message that called the command
     * @return whether or not this command should execute
     */
    private boolean checkCommandExecuteRights(AbstractCommand<Message> command, Message message) {
        if (command.hasAttribute("OwnerOnly") && !message.getMember().isOwner()) {
            return false;
        }
        return !command.hasAttribute("AdminOnly") || message.getMember().hasPermission(Permission.ADMINISTRATOR);
    }

}
