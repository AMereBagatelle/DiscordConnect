package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ChatlinkMessageListener extends ListenerAdapter {

    /**
     * Processes message from discord for chat bridge
     */
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (message.getChannel() == DiscordConnect.bot.linkChannel && DiscordConnect.bot.isChatLinkActive && !content.startsWith("[" + SettingsManager.loadSettingOrDefault("serverType", "SMP") + "]")) {
            DiscordConnect.bot.sendMessageToMinecraft("<" + event.getAuthor().getName() + "> " + content);
        }
    }
}
