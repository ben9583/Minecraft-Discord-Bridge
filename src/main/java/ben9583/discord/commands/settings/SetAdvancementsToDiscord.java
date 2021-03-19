package ben9583.discord.commands.settings;

import ben9583.Ben9583;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "advancementstodiscord", triggers = {"advancementstodiscord",
        "advancements"}, description = "Sets on/off advancement messages integration to Discord", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class SetAdvancementsToDiscord implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.advancementsToDiscord")) {
            Ben9583.getPlugin(Ben9583.class).disablePlayerAdvancementListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.advancementsToDiscord", false);
            message.getChannel().sendMessage("Advancements integration from Minecraft to Discord disabled").queue();
        } else {
            Ben9583.getPlugin(Ben9583.class).enablePlayerAdvancementListener();
            Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.advancementsToDiscord", true);
            message.getChannel().sendMessage("Advancements messages integration from Minecraft to Discord enabled").queue();
        }
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
