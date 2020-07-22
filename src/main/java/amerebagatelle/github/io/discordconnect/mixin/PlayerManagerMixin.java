package amerebagatelle.github.io.discordconnect.mixin;

import amerebagatelle.github.io.discordconnect.DiscordConnect;
import amerebagatelle.github.io.discordconnect.MessageFormatting;
import amerebagatelle.github.io.discordconnect.settings.SettingsManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Shadow
    @Final
    private MinecraftServer server;

    /**
     * Sends player join message to chat bridge.
     */
    @Inject(method = "onPlayerConnect", at = @At("TAIL"))
    public void sendPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        DiscordConnect.bot.sendMessageToChatlink(MessageFormatting.removeFormattingFromString(String.format("[%s] %s joined the server", SettingsManager.loadSettingOrDefault("serverType", "SMP"), player.getDisplayName().asFormattedString())));
    }

    /**
     * Sends player disconnect message to chat bridge.
     */
    @Inject(method = "remove", at = @At("TAIL"))
    public void sendPlayerDisconnect(ServerPlayerEntity player, CallbackInfo ci) {
        DiscordConnect.bot.sendMessageToChatlink(MessageFormatting.removeFormattingFromString(String.format("[%s] %s left the server", SettingsManager.loadSettingOrDefault("serverType", "SMP"), player.getDisplayName().asFormattedString())));
    }
}
