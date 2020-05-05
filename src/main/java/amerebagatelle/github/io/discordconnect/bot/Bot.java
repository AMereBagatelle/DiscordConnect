package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

import javax.security.auth.login.LoginException;

public class Bot {
    // TODO: Move this to settings
    public String linkChannelId = "707009754723123253";
    public boolean isBotActive;
    public static JDA bot;
    public TextChannel linkChannel;

    public Bot() {
        try {
            bot = JDABuilder.createDefault("NjU3OTIwNzg4MDk1MTcyNjA4.XrCl_g.T-FjANXJlo2ct_PI0InJaBVZ0zA").build();
            bot.addEventListener(new MessageListener());
            bot.awaitReady();
            linkChannel = bot.getTextChannelById(linkChannelId);
            isBotActive = true;
        } catch (LoginException | InterruptedException e) {
            bot = null;
            isBotActive = false;
        }
    }

    public void sendMessageToMinecraft(String message) {

    }
}
