package amerebagatelle.github.io.discordconnect.mixin;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatListenerMixin {
    @Inject(method = "onChatMessage", at = @At("RETURN"))
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo cbi) {
        String chatMessage = packet.getChatMessage();
        if(!chatMessage.startsWith(DiscordConnect.bot.discordMessagePrefix)) {
            DiscordConnect.bot.sendMessageToDiscord(DiscordConnect.bot.minecraftMessagePrefix + " " + chatMessage);
        }
    }
}