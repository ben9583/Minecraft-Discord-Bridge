package ben9583.discord.commands.settings;

import ben9583.Ben9583;
import ben9583.discord.DiscordBotSettings;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "discordtominecraftchat", triggers = {"discordtominecraftchat",
        "discotomine"}, description = "Sets on/off chat integration from Discord to Minecraft", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class SetDiscordToMinecraftChat implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        if (Ben9583.getPlugin(Ben9583.class).getConfig().getBoolean("integration.discordToMinecraftChat")) {
            DiscordBotSettings.setEnabledDiscordToMinecraftChat(false);
            message.getChannel().sendMessage("Chat integration from Discord to Minecraft disabled").queue();
        } else {
            DiscordBotSettings.setEnabledDiscordToMinecraftChat(true);
            message.getChannel().sendMessage("Chat integration from Discord to Minecraft enabled").queue();
        }
    }

}
