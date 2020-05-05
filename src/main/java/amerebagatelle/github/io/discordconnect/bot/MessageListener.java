package amerebagatelle.github.io.discordconnect.bot;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {
    // TODO: Move this to settings
    public String messagePrefix = "[SMP]";

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        // Makes sure that we don't catch our own message
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if(message.getChannel() == DiscordConnect.bot.linkChannel) {

        }
    }
}
