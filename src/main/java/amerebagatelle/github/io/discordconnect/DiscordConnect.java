package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.bot.Bot;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;

public class DiscordConnect implements ModInitializer {
    public static Bot bot;

    @Override
    public void onInitialize() {
        SettingsManager.initSettings();
        bot = new Bot();
    }
}
