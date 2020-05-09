package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

import javax.security.auth.login.LoginException;

public class Bot {
    public String minecraftMessagePrefix = SettingsManager.loadSetting("minecraftMessagePrefix");
    public String discordMessagePrefix = SettingsManager.loadSetting("discordMessagePrefix");
    public String linkChannelId = SettingsManager.loadSetting("chatLinkChannelId");
    public boolean isChatLinkActive = Boolean.parseBoolean(SettingsManager.loadSetting("chatLinkActive"));
    public boolean isBotActive;
    public static JDA bot;
    public TextChannel linkChannel;
    private final MinecraftServer minecraftServerInstance;

    public Bot() {
        try {
            bot = JDABuilder.createDefault(SettingsManager.loadSetting("botToken")).build();
            bot.addEventListener(new ChatlinkMessageListener());
            bot.awaitReady();
            linkChannel = bot.getTextChannelById(linkChannelId);
            isBotActive = true;
        } catch (LoginException | InterruptedException e) {
            bot = null;
            isBotActive = false;
        }
        minecraftServerInstance = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
    }

    public void sendMessageToMinecraft(String message) {
        minecraftServerInstance.getPlayerManager().sendToAll(new LiteralText(message));
    }

    public void sendMessageToChatlink(String message) {
        if (linkChannel != null) {
            linkChannel.sendMessage(message).queue();
        }
    }
}
