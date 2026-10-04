/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.LF21Client;
import lf21.event.impl.AttackEntityEvent;
import lf21.patch.CallbackInfo;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@Patch(value=MultiPlayerGameMode.class)
public class MultiPlayerGameModePatch {
    @Inject(method="attack", desc="(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V", at=@At(value=At.Type.HEAD))
    public static void onAttack(MultiPlayerGameMode multiPlayerGameMode, Player player, Entity entity, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            AttackEntityEvent attackEntityEvent = new AttackEntityEvent(entity);
            LF21Client.getInstance().getEventBus().call(attackEntityEvent);
            if (attackEntityEvent.isCancelled()) {
                callbackInfo.cancel();
            }
        }
    }
}

