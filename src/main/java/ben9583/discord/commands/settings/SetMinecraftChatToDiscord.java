package ben9583.discord.commands.settings;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;

import ben9583.Ben9583;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "minecraftchattodiscord", triggers = {
        "minetodiscord", "minecraftchattodiscord" }, description = "Sets on/off chat integration from Minecraft to Discord.", attributes = {
                @CommandAttribute(key = "AdminOnly", value = "1") })
public class SetMinecraftChatToDiscord implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.mincraftChatToDiscord")) {
            Ben9583.getPlugin(Ben9583.class).disableChatListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.mincraftChatToDiscord", false);
            message.getChannel().sendMessage("Chat integration from Minecraft to Discord disabled").queue();
        } else {
            Ben9583.getPlugin(Ben9583.class).enableChatListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.mincraftChatToDiscord", true);        
            message.getChannel().sendMessage("Chat integration from Minecraft to Discord enabled").queue();
        }
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
