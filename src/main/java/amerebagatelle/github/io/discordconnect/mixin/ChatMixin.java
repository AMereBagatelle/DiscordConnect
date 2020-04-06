package amerebagatelle.github.io.discordconnect.mixin;

import amerebagatelle.github.io.discordconnect.Bot;
import amerebagatelle.github.io.discordconnect.DiscordConnect;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class ChatMixin {

    @Inject(method="broadcastChatMessage", at=@At("HEAD"))
    public void onChatMessage(Text text, boolean system, CallbackInfo cbi) {
        // Handling regular chat messages
        String message = text.asFormattedString();
        if(!system) {
            Bot.sendMessage(message);
        } else {
            if(message.contains("[Discord]")) {
                Bot.sendMessage(message.replaceAll("§[4c6e2ab319d5f780lmnor]", ""));
            }
        }
    }
}