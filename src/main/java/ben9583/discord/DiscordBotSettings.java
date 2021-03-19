package ben9583.discord;

import ben9583.Ben9583;

public class DiscordBotSettings {
    private static String discordChannelID;
    private static String requestAccessChannelID;
    private static String commandPrefix;
    private static boolean enabledDiscordToMinecraftChat;
    private static String channelRoleID;
    private static String serverName;

    public DiscordBotSettings() {
        discordChannelID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.channelID");
        enabledDiscordToMinecraftChat = Ben9583.getPlugin(Ben9583.class).getConfig()
                .getBoolean("integration.discordToMinecraftChat");
        commandPrefix = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.commandPrefix");
        requestAccessChannelID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.requestAccessChannelID");
        channelRoleID = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.syncedChannelRoleID");
        serverName = Ben9583.getPlugin(Ben9583.class).getConfig().getString("discord.serverName");
    }

    /**
     * @return the prefix the bot listens for on Discord
     */
    public static String getCommandPrefix() {
        return commandPrefix;
    }

    /**
     * @param newCommandPrefix the prefix the bot should listen for on Discord
     */
    public static void setCommandPrefix(String newCommandPrefix) {
        commandPrefix = newCommandPrefix;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.commandPrefix", commandPrefix);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return whether or not Discord chat is relayed to Minecraft
     */
    public static boolean isEnabledDiscordToMinecraftChat() {
        return enabledDiscordToMinecraftChat && discordChannelIsSet();
    }

    /**
     * @param newEnabledDiscordToMinecraftChat whether or not Discord chat should be relayed to Minecraft
     */
    public static void setEnabledDiscordToMinecraftChat(boolean newEnabledDiscordToMinecraftChat) {
        enabledDiscordToMinecraftChat = newEnabledDiscordToMinecraftChat;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("integration.discordToMinecraftChat",
                enabledDiscordToMinecraftChat);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return the Discord channel ID of where this bot will be active
     */
    public static String getDiscordChannelID() {
        return discordChannelID;
    }

    /**
     * @param newDiscordChannelID the Discord channel ID of where this bot should be active
     */
    public static void setDiscordChannelID(String newDiscordChannelID) {
        discordChannelID = newDiscordChannelID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.channelID", discordChannelID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return Whether or not the Discord channel ID of where this bot will be active is set
     */
    public static boolean discordChannelIsSet() {
        return !discordChannelID.isEmpty() && !"DEFAULTCHANNELID".equals(discordChannelID);
    }

    /**
     * @return the Discord role ID that lets members vote for authentication and view chat relays
     */
    public static String getChannelRoleID() {
        return channelRoleID;
    }

    /**
     * @param newChannelRoleID the Discord role ID that should let members vote for authentication and view chat relays
     */
    public static void setChannelRoleID(String newChannelRoleID) {
        channelRoleID = newChannelRoleID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.syncedChannelRoleID", channelRoleID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return whether or not the Discord role ID that lets members vote for authentication and view chat relays is set
     */
    public static boolean ChannelRoleIsSet() {
        return !discordChannelID.isEmpty() && !"DEFAULTROLE".equals(channelRoleID);
    }

    /**
     * @return the channel where authentication requests will be put to vote on
     */
    public static String getRequestAccessID() {
        return requestAccessChannelID;
    }

    /**
     * @param newDiscordChannelID the channel where authentication requests should be put to vote on
     */
    public static void setRequestAccessChannelID(String newDiscordChannelID) {
        requestAccessChannelID = newDiscordChannelID;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.requestAccessChannelID", requestAccessChannelID);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

    /**
     * @return whether or not the channel where authentication requests will be put to vote on is set
     */
    public static boolean requestAccessChannelIsSet() {
        return !discordChannelID.isEmpty() && !"ACCESSREQUESTCHANNELID".equals(requestAccessChannelID);
    }

    /**
     * @return the server's name
     */
    public static String getServerName() {
        return serverName;
    }

    /**
     * @param name the new name of the server
     */
    public static void setServerName(String name) {
        serverName = name;
        Ben9583.getPlugin(Ben9583.class).getConfig().set("discord.serverName", serverName);
        Ben9583.getPlugin(Ben9583.class).saveConfig();
    }

}
