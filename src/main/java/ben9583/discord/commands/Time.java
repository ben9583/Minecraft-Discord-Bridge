package ben9583.discord.commands;

import com.github.rainestormee.jdacommand.AbstractCommand;
import com.github.rainestormee.jdacommand.CommandAttribute;
import com.github.rainestormee.jdacommand.CommandDescription;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.Server;
import org.bukkit.World;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

@CommandDescription(name = "time", triggers = {"time"}, description = "Returns what time it is in the server", attributes = {})
public class Time implements AbstractCommand<Message> {
    private final Server server;

    public Time() {
        this.server = getServer();
    }
    @Override
    @SuppressWarnings("unchecked")
    public void execute(Message message, String args) {
        List<World> worlds = server.getWorlds();
        if(worlds.size() == 0){
            message.getChannel().sendMessage("There is no world running from which to get the time!").queue();
            return;
        }
        long time = (worlds.get(0).getTime() + 6000) % 24000;
        int hours = (int)(time / 1000);
        int minutes = (int)((time % 1000) / 60);
        String partOfDay = hours / 12 == 1 ? "PM" : "AM";
        hours = ((hours - 1) % 12) + 1;
        if(hours == 0) { hours = 12; }
        String minutesFormatted = minutes < 10 ? "0" + minutes : "" + minutes;
        message.getChannel().sendMessage("The time is " + time + " (" + hours + ":" + minutesFormatted + " " + partOfDay + ") in " + worlds.get(0).getName()).queue();
    }
}
