package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bot {
    public String linkChannelId = SettingsManager.loadSettingOrDefault("chatLinkChannelId", "");
    public boolean isChatLinkActive = Boolean.parseBoolean(SettingsManager.loadSettingOrDefault("chatLinkActive", "true"));
    public boolean isBotActive;
    public static JDA bot;
    public TextChannel linkChannel;
    public final MinecraftServer minecraftServerInstance;

    public Bot() {
        try {
            bot = JDABuilder.createDefault(SettingsManager.loadSettingOrDefault("botToken", "")).build();
            bot.addEventListener(new ChatlinkMessageListener());
            bot.addEventListener(new CommandMessageListener());
            bot.awaitReady();
            linkChannel = bot.getTextChannelById(linkChannelId);
            LocalDateTime now = LocalDateTime.now();
            linkChannel.getManager().setTopic("Server last started: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).queue();
            isBotActive = true;
        } catch (LoginException | InterruptedException e) {
            bot = null;
            isBotActive = false;
        }
        minecraftServerInstance = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
    }

    public void sendMessageToMinecraft(String message) {
        minecraftServerInstance.getPlayerManager().broadcastChatMessage(new LiteralText("[DISCORD] " + message), false);
    }

    public void sendMessageToChatlink(String message) {
        if (linkChannel != null) {
            linkChannel.sendMessage(message).queue();
        }
    }

    public void sendDiscordMessage(String message, MessageChannel channel) {
        channel.sendMessage(message).queue();
    }
}
