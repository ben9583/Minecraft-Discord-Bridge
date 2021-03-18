package ben9583.discord.commands;

import ben9583.discord.DiscordBotSettings;
import ben9583.discord.commands.settings.*;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import com.github.rainestormee.jdacommand.CommandHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "ping", triggers = { "ping" }, description = "Pings the bot (for debug purposes).", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1") })
public class Ping implements AbstractCommand<Message> {
    public Ping() { }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        message.getChannel().sendMessage("Pong!");
    }
}
