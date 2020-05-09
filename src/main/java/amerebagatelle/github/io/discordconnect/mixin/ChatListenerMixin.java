package amerebagatelle.github.io.discordconnect.mixin;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class ChatListenerMixin {
    @Inject(method = "broadcastChatMessage", at = @At("RETURN"))
    public void onChatMessage(Text text, boolean system, CallbackInfo cbi) {
        String chatMessage = text.asFormattedString();
        if (!chatMessage.startsWith(DiscordConnect.bot.minecraftMessagePrefix) && DiscordConnect.bot.isBotActive && DiscordConnect.bot.isChatLinkActive && !system) {
            DiscordConnect.bot.sendMessageToChatlink(DiscordConnect.bot.minecraftMessagePrefix + " " + chatMessage);
        }
    }
}