package ben9583.discord.commands.settings;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;

import ben9583.Ben9583;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "deathmessages", triggers = { "deathmessages",
        "death", "deadMessagesToDiscord" }, description = "Sets on/off player death messages in Discord", attributes = {
                @CommandAttribute(key = "OwnerOnly", value = "1") })
public class SetDeathMessagesToDiscord implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.deathMessagesToDiscord")) {
            Ben9583.getPlugin(Ben9583.class).disablePlayerDeathListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.deathMessagesToDiscord", false);
            message.getChannel().sendMessage("Player death messages disabled").queue();
        } else {
            Ben9583.getPlugin(Ben9583.class).enablePlayerDeathListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.deathMessagesToDiscord", true);        
            message.getChannel().sendMessage("Player death messages enabled").queue();
        }
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
