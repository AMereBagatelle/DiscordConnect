package amerebagatelle.github.io.discordconnect;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

import javax.security.auth.login.LoginException;

public class DiscordConnect implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs on initialization, place for commands, configuration init, etc.
        Bot bot = new Bot();
    }
}
