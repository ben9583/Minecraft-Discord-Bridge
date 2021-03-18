package ben9583.discord.commands;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import com.github.rainestormee.jdacommand.CommandHandler;

import ben9583.discord.DiscordBotSettings;
import ben9583.discord.commands.settings.SetAccessRequestChannel;
import ben9583.discord.commands.settings.SetAdvancementsToDiscord;
import ben9583.discord.commands.settings.SetCommandPrefix;
import ben9583.discord.commands.settings.SetDeathMessagesToDiscord;
import ben9583.discord.commands.settings.SetDefaultRole;
import ben9583.discord.commands.settings.SetDiscordToMinecraftChat;
import ben9583.discord.commands.settings.SetIntegratedChannel;
import ben9583.discord.commands.settings.SetJoinQuitMessagesToDiscord;
import ben9583.discord.commands.settings.SetMinecraftChatToDiscord;
import ben9583.discord.commands.settings.SetServerSayMessagesToDiscord;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.Permission;

@CommandDescription(name = "set", triggers = { "set", "settings" }, description = "Sets bot settings.", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1") })
public class Set implements AbstractCommand<Message> {

    private static final CommandHandler<Message> SETTINGSHANDLER = new CommandHandler<>();;

    public Set() {
        SETTINGSHANDLER.registerCommands(new SetIntegratedChannel(), new SetAdvancementsToDiscord(),
                new SetDeathMessagesToDiscord(), new SetDiscordToMinecraftChat(), new SetJoinQuitMessagesToDiscord(),
                new SetMinecraftChatToDiscord(), new SetServerSayMessagesToDiscord(), new SetCommandPrefix(), 
                new SetAccessRequestChannel(), new SetDefaultRole());
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

    private boolean checkCommandExecuteRights(AbstractCommand<Message> command, Message message) {
        if (command.hasAttribute("OwnerOnly") && !message.getMember().isOwner()) {
            return false;
        }
        if(command.hasAttribute("AdminOnly") && !message.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            return false;
        }

        return true;
    }

}
