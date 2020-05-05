package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.bot.Bot;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiscordConnect implements ModInitializer {
    public static Bot bot;

    @Override
    public void onInitialize() {
        bot = new Bot();
    }
}
