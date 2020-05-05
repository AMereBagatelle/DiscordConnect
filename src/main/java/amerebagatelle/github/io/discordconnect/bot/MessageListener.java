package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if(message.getChannel() == DiscordConnect.bot.linkChannel) {
            if(!content.startsWith(DiscordConnect.bot.minecraftMessagePrefix)) {
                DiscordConnect.bot.sendMessageToMinecraft(DiscordConnect.bot.minecraftMessagePrefix + " " + content);
            }
        }
    }
}