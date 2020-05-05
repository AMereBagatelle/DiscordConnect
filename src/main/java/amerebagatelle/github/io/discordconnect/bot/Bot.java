package amerebagatelle.github.io.discordconnect.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

import javax.security.auth.login.LoginException;

public class Bot {
    // TODO: Move this to settings
    public String minecraftMessagePrefix = "[SMP]";
    // TODO: Move this to settings
    public String discordMessagePrefix = "[Discord]";
    // TODO: Move this to settings
    public String linkChannelId = "707009754723123253";
    public boolean isBotActive;
    public static JDA bot;
    public TextChannel linkChannel;
    private final MinecraftServer minecraftServerInstance;

    public Bot() {
        try {
            bot = JDABuilder.createDefault("Njk2NTE0ODkwMzYxMzM5OTM0.XrFC4A.DXeBIIuGsq4w0bY785e3fEM24Lc").build();
            bot.addEventListener(new ServerMessageListener());
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

    public void sendMessageToDiscord(String message) {
        if(linkChannel != null) {
            linkChannel.sendMessage(message).queue();
        }
    }
}
