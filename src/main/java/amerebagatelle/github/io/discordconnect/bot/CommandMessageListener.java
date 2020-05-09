package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;

public class CommandMessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();

        if (message.startsWith(SettingsManager.loadSetting("commandPrefix"))) {
            if (event.getChannel() == DiscordConnect.bot.linkChannel) return;

            if (message.contains("online")) {
                MinecraftServer mcs = DiscordConnect.bot.minecraftServerInstance;
                String[] players = mcs.getPlayerManager().getPlayerNames();
                String finalMessage = "Currently online players:\n";
                for (String player : players) {
                    finalMessage = finalMessage + player + "\n";
                }
                DiscordConnect.bot.sendDiscordMessage(finalMessage, event.getChannel());
            }
        }
    }
}
