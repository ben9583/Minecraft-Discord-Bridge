package ben9583.discord.commands;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "version", triggers = {"version"}, description = "Returns the version of this plugin", attributes = {})
public class Version implements AbstractCommand<Message> {
    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        message.getChannel().sendMessage("Running version 1.4.1").queue();
    }
}
