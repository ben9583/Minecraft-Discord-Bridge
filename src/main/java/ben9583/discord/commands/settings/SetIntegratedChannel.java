package ben9583.discord.commands.settings;

import ben9583.discord.DiscordBotSettings;
import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;

@CommandDescription(name = "integratedchannel", triggers = {"integratedchannel",
        "setintegratedchannel"}, description = "Sets current channel to be integrated with Minecraft.", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1"),
        @CommandAttribute(key = "canUseOnAnyChannel", value = "1")})
public class SetIntegratedChannel implements AbstractCommand<Message> {

    @Override
    public void execute(Message message, String args) {
        DiscordBotSettings.setDiscordChannelID(message.getChannel().getId());
        message.getChannel().sendMessage("This channel is now integrated with Minecraft chat").queue();
    }

}
