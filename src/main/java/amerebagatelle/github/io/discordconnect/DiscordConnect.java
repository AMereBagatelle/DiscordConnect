package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;

public class DiscordConnect implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs on initialization, place for commands, configuration init, etc.
        SettingsManager.init();
        Bot bot = new Bot();
    }
}
