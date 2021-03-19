package ben9583.discord.commands.settings;

import ben9583.Ben9583;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "serversaymessagestodiscord", triggers = {
        "saytodiscord", "serverSayMessagesToDiscord"}, description = "Sets on/off server say messages integration to Discord", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class SetServerSayMessagesToDiscord implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.serverSayMessagesToDiscord")) {
            Ben9583.getPlugin(Ben9583.class).disableServerCommandListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.serverSayMessagesToDiscord", false);
            message.getChannel().sendMessage("Server say messages integration from Minecraft to Discord disabled").queue();
        } else {
            Ben9583.getPlugin(Ben9583.class).enableServerCommandListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.serverSayMessagesToDiscord", true);
            message.getChannel().sendMessage("Server say messages integration from Minecraft to Discord enabled").queue();
        }
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
