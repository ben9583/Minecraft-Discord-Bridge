package ben9583.discord.commands.settings;

import ben9583.discord.DiscordBotSettings;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "name", triggers = {
        "name", "servername"}, description = "Sets server name for status reports", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class SetServerName implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (!args.isEmpty()) {
            DiscordBotSettings.setCommandPrefix(args);
            message.getChannel().sendMessage("New command prefix is: " + DiscordBotSettings.getCommandPrefix()).queue();
        } else {
            message.getChannel().sendMessage("Prefix cannot be empty. Please pick valid prefix.").queue();
        }
    }

}
