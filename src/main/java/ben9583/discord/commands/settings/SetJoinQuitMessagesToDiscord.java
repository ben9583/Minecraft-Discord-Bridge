package ben9583.discord.commands.settings;

import ben9583.Ben9583;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "joinquitmessages", triggers = {
        "join", "quit", "joinQuitMessagesToDiscord"}, description = "Sets on/off join/disconnect messages on Discord.", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class SetJoinQuitMessagesToDiscord implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.joinQuitMessagesToDiscord")) {
            Ben9583.getPlugin(Ben9583.class).disablePlayerJoinQuitListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.joinQuitMessagesToDiscord", false);
            message.getChannel().sendMessage("Player death messages disabled").queue();
        } else {
            Ben9583.getPlugin(Ben9583.class).enablePlayerJoinQuitListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.joinQuitMessagesToDiscord", true);
            message.getChannel().sendMessage("Player death messages enabled").queue();
        }
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
