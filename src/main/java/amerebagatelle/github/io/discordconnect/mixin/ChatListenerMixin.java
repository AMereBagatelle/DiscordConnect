package amerebagatelle.github.io.discordconnect.mixin;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatListenerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastChatMessage(Lnet/minecraft/text/Text;Z)V"))
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        DiscordConnect.bot.sendMessageToChatlink(String.format("[%s] <%s> %s", SettingsManager.loadSettingOrDefault("serverType", "SMP"), player.getName().asFormattedString(), packet.getChatMessage()));
    }
}