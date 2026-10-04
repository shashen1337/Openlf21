/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.modules.impl.combat.TeleportAura;
import lf21.modules.impl.render.Freecam;
import lf21.patch.CallbackInfo;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.Entity;

@Patch(value=MouseHandler.class)
public final class MouseHandlerPatch {
    @Inject(method="onPress", desc="(JIII)V", at=@At(value=At.Type.HEAD))
    public static void onPress(MouseHandler mouseHandler, long l2, int n2, int n3, int n4, CallbackInfo callbackInfo) {
        Freecam freecam;
        if (!LF21Client.isReady()) {
            return;
        }
        TeleportAura teleportAura = TeleportAura.INSTANCE;
        if (teleportAura != null) {
            teleportAura.onPhysicalMouse(n2, n3);
        }
        if ((freecam = Freecam.INSTANCE) != null) {
            freecam.onPhysicalMouse(n2, n3);
        }
    }

    @WrapInvoke(method="turnPlayer", desc="()V", target="net/minecraft/world/entity/Entity/turn", targetDesc="(DD)V")
    public static void onTurnPlayer(MouseHandler mouseHandler, Invocation<Entity, Void> invocation) throws Exception {
        if (!LF21Client.isReady() || !Freecam.captureMouseTurn(invocation)) {
            invocation.call();
        }
    }
}

