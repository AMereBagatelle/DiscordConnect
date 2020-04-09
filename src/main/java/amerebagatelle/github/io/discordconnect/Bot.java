package amerebagatelle.github.io.discordconnect;

import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

public class Bot extends ListenerAdapter {
    private MinecraftServer mcs;
    private static JDA builder;
    private static Guild chatLinkGuild;
    private static TextChannel chatLinkChannel;
    private int ticksSinceUpdate;
    private String serverStartTime;

    public Bot(String serverStartTime) {
        if(SettingsManager.channelLinkId != null && SettingsManager.guildLinkId != null && SettingsManager.token != null && SettingsManager.channelLinkId.length() > 0 && SettingsManager.guildLinkId.length() > 0 && SettingsManager.token.length() > 0) {
            this.mcs = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
            try {
                builder = new JDABuilder(AccountType.BOT).setToken(SettingsManager.token).addEventListeners(this).build();
                builder.awaitReady();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Don't have a token!  Did you set it in discordconnect.properties?");
        }
        this.serverStartTime = serverStartTime;
    }

    public void tick() {
        ticksSinceUpdate++;
        if(ticksSinceUpdate > 1200) {
            ticksSinceUpdate = 0;
            chatLinkChannel.getManager().setTopic("Players Online: " + mcs.getPlayerNames().length + " Up since: " + serverStartTime).queue(); // TODO: Better number than getTicks for uptime
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            chatLinkGuild = builder.getGuildById(SettingsManager.guildLinkId);
            chatLinkChannel = chatLinkGuild.getTextChannelById(SettingsManager.channelLinkId);
        } catch (NullPointerException e) {
            System.out.println("Was not able to get chatlink channel");
        }
        ticksSinceUpdate = 1100;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            return;
        }

        String eventMessage = event.getMessage().getContentRaw();
        // Command Processing
        if(SettingsManager.onlineCommand) {
            if (eventMessage.contains("!")) {
                // * !online command
                if (eventMessage.contains("!online")) {
                    String[] playerList = mcs.getPlayerManager().getPlayerNames();
                    StringBuffer sb;
                    sb = new StringBuffer();
                    for (String s : playerList) {
                        sb.append(s).append("\n");
                    }
                    System.out.println(sb.toString());
                    TextChannel sendChannel = (TextChannel) event.getChannel();
                    if (sb.length() != 0) {
                        sendChannel.sendMessage(sb.toString()).queue();
                    } else {
                        sendChannel.sendMessage("No players currently on.").queue();
                    }
                }
            }
        }
        // Discord-Minecraft Link
        if(SettingsManager.sendToMinecraft) {
            if (chatLinkChannel != null && event.getChannel() == chatLinkChannel) { //TODO: Move to configuration
                mcs.getPlayerManager().sendToAll(new LiteralText("[§3Discord§r] <" + event.getAuthor().getName() + "> " + eventMessage));
            } else if (chatLinkChannel == null) {
                chatLinkGuild = builder.getGuildById(SettingsManager.guildLinkId);
                chatLinkChannel = chatLinkGuild.getTextChannelById(SettingsManager.channelLinkId);
                mcs.getPlayerManager().sendToAll(new LiteralText("[§3Discord§r] <" + event.getAuthor().getName() + "> " + eventMessage));
            }
        }
    }

    public static void sendMessageToChatLink(String message) {
        if (chatLinkChannel == null) {
            chatLinkGuild = builder.getGuildById(SettingsManager.guildLinkId);
            chatLinkChannel = chatLinkGuild.getTextChannelById(SettingsManager.channelLinkId);
        }
        System.out.println(message);
        System.out.println(chatLinkChannel);
        chatLinkChannel.sendMessage(message).queue();
    }
}
