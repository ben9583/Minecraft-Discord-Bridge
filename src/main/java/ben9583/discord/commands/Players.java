package ben9583.discord.commands;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;

import static org.bukkit.Bukkit.getServer;

@CommandDescription(name = "players", triggers = {"players"}, description = "Returns how many players are online", attributes = {
        @CommandAttribute(key = "AdminOnly", value = "1")})
public class Players implements AbstractCommand<Message> {
    private final Server server;

    public Players() {
        this.server = getServer();
    }
    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        if(server.getOnlinePlayers().size() == 0) {
            message.getChannel().sendMessage("There are 0 players online.").queue();
        } else if(server.getOnlinePlayers().size() == 1) {
            message.getChannel().sendMessage("There is 1 player online: " + server.getOnlinePlayers().iterator().next().getName()).queue();
        } else {
            String outputStr = "There are now " + server.getOnlinePlayers().size() + " players online: ";
            String[] playerNameArray = server.getOnlinePlayers().stream().map(HumanEntity::getName).toArray(String[]::new);

            outputStr = outputStr + String.join(", ", playerNameArray);
            message.getChannel().sendMessage(outputStr).queue();
        }
    }
}
