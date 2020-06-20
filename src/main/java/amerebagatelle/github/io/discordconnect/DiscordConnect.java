package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.bot.Bot;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;

public class DiscordConnect implements DedicatedServerModInitializer {
    public static Bot bot;

    @Override
    public void onInitializeServer() {
        SettingsManager.initSettings();
        bot = new Bot();

        ServerStartCallback.EVENT.register(e -> {
            bot.sendMessageToChatlink("Server started!");
        });

        ServerStopCallback.EVENT.register(e -> {
            bot.sendMessageToChatlink("Server shutting down...");
        });
    }
}
