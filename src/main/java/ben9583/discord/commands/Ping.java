package ben9583.discord.commands;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "ping", triggers = {"ping"}, description = "Pings the bot (for debug purposes)", attributes = {})
public class Ping implements AbstractCommand<Message> {
    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        message.getChannel().sendMessage(args.length() > 0 ? args : "Pong!").queue();
    }
}
