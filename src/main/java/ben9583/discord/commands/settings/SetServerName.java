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
            DiscordBotSettings.setServerName(args);
            message.getChannel().sendMessage("New server name is: " + DiscordBotSettings.getServerName()).queue();
        } else {
            message.getChannel().sendMessage("Name cannot be empty. Please pick valid name.").queue();
        }
    }

}
