package amerebagatelle.github.io.discordconnect;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {
    private MinecraftServer mcs;
    private static JDA builder;
    private static Guild chatLinkGuild;
    private static TextChannel chatLinkChannel;
    private static final String token = "Njk2NTE0ODkwMzYxMzM5OTM0.Xop2Mg.eOQ-GRr7G-ojQwxLZa1dhOalECQ"; // TODO: Move to configuration

    public Bot() {
        this.mcs = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
        try {
            builder = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(this).buildAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            chatLinkGuild = builder.getGuildById("696516360074952786");
            chatLinkChannel = (TextChannel)chatLinkGuild.getTextChannelById("696516360074952790");
        } catch(NullPointerException e) {
            System.out.println("Was not able to get chatlink channel");
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            return;
        }

        // Command Processing
        String eventMessage = event.getMessage().getContentRaw();
        if(eventMessage.contains("!")) {
            // * !online command
            if(eventMessage.contains("!online")) {
                String[] playerList = mcs.getPlayerManager().getPlayerNames();
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<playerList.length; i++) {
                    sb.append(playerList[i] + "\n");
                }
                System.out.println(sb.toString());
                TextChannel sendChannel = (TextChannel)event.getChannel();
                if(sb.length() != 0) {
                    sendChannel.sendMessage(sb.toString()).queue();
                } else {
                    sendChannel.sendMessage("No players currently on.").queue();
                }
            }
        }
        // Discord-Minecraft Link
        if(chatLinkChannel != null && event.getChannel() == chatLinkChannel) { //TODO: Move to configuration
            mcs.getPlayerManager().sendToAll(new LiteralText("[§3Discord§r] <" + event.getAuthor().getName() + "> " + eventMessage));
        } else if(chatLinkChannel == null) {
            chatLinkGuild = builder.getGuildById("696516360074952786");
            chatLinkChannel = (TextChannel)chatLinkGuild.getTextChannelById("696516360074952790");
            mcs.getPlayerManager().sendToAll(new LiteralText("[§3Discord§r] <" + event.getAuthor().getName() + "> " + eventMessage));
        }
    }

    public static void sendMessage(String message) {
        if (chatLinkChannel == null) {
            chatLinkGuild = builder.getGuildById("696516360074952786");
            chatLinkChannel = (TextChannel) chatLinkGuild.getTextChannelById("696516360074952790");
        }
        System.out.println(message);
        System.out.println(chatLinkChannel);
        chatLinkChannel.sendMessage(message).queue();
    }
}
