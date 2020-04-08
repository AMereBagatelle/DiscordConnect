package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiscordConnect implements ModInitializer {
    public static Bot bot;
    public static String serverStartTime;

    @Override
    public void onInitialize() {
        serverStartTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        // This code runs on initialization, place for commands, configuration init, etc.
        SettingsManager.init();
        Bot bot = new Bot(serverStartTime);

        ServerTickCallback.EVENT.register(e -> {
            bot.tick();
        });

        ServerStartCallback.EVENT.register(e -> {
            Bot.sendMessageToChatLink("Server started!");
        });

        ServerStopCallback.EVENT.register(e -> {
           Bot.sendMessageToChatLink("Server shutting down...");
        });
    }
}
