package ben9583.discord;

import ben9583.Ben9583;

/**
 *
 */
public class DiscordBotSettings {
    private static String discordChannelID;
    private static String requestAccessChannelID;
    private static String commandPrefix;
    private static boolean enabledDiscordtoMinecraftChat;
    private static String channelRoleID;
    private static String serverName;

    /**
     *
     */
    public DiscordBotSettings() {
        discordChannelID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.channelID");
        enabledDiscordtoMinecraftChat = Ben9583.getPlugin(Ben9583.class).getConfig()
                .getBoolean("integration.discordToMinecraftChat");
        commandPrefix = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.commandPrefix");
        requestAccessChannelID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.requestAccessChannelID");
        channelRoleID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.syncedChannelRoleID");
        serverName = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.serverName");
    }

    /**
     * 
     */
    public static String getCommandPrefix() {
        return commandPrefix;
    }

    /**
     * 
     * @param newCommandPrefix
     */
    public static void setCommandPrefix(String newCommandPrefix) {
        commandPrefix = newCommandPrefix;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.commandPrefix", commandPrefix);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     *
     * @return
     */
    public static boolean isEnabledDiscordtoMinecraftChat() {
        return enabledDiscordtoMinecraftChat && discordChannelIsSet();
    }

    /**
     *
     * @param newEnabledDiscordtoMinecraftChat
     */
    public static void setEnabledDiscordtoMinecraftChat(boolean newEnabledDiscordtoMinecraftChat) {
        enabledDiscordtoMinecraftChat = newEnabledDiscordtoMinecraftChat;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.discordToMinecraftChat",
                enabledDiscordtoMinecraftChat);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return the discordChannelID
     */
    public static String getDiscordChannelID() {
        return discordChannelID;
    }

    /**
     * @param newDiscordChannelID the discordChannelID to set
     */
    public static void setDiscordChannelID(String newDiscordChannelID) {
        discordChannelID = newDiscordChannelID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.channelID", discordChannelID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     *
     * @return
     */
    public static boolean discordChannelIsSet() {
        if (discordChannelID.isEmpty() || "DEFAULTCHANNELID".equals(discordChannelID)) {
            return false;
        }
        return true;
    }

        /**
     * @return the discordChannelID
     */
    public static String getchannelRoleID() {
        return channelRoleID;
    }

    /**
     * @param newDiscordChannelID the discordChannelID to set
     */
    public static void setChannelRoleID(String newChannelRoleID) {
        channelRoleID = newChannelRoleID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.syncedChannelRoleID", channelRoleID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     *
     * @return
     */
    public static boolean ChannelRoleIsSet() {
        if (discordChannelID.isEmpty() || "DEFAULTROLE".equals(channelRoleID)) {
            return false;
        }
        return true;
    }

        /**
     * @return the requestAccessChannelID
     */
    public static String getRequestAccessID() {
        return requestAccessChannelID;
    }

    /**
     * @param newDiscordChannelID the requestAccessChannelID to set
     */
    public static void setRequestAccessChannelID(String newDiscordChannelID) {
        requestAccessChannelID = newDiscordChannelID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.requestAccessChannelID", requestAccessChannelID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     *
     * @return
     */
    public static boolean requestAccessChannelIsSet() {
        if (discordChannelID.isEmpty() || "ACCESSREQUESTCHANNELID".equals(requestAccessChannelID)) {
            return false;
        }
        return true;
    }

    /**
     * @param name the new name of the server
     */
    public static void setServerName(String name) {
        serverName = name;
    }

    /**
     * @return the server's name
     */
    public static String getServerName() {
        return serverName;
    }

}
