/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import lf21.modules.impl.exploit.Raytrace;
import lf21.patch.CallbackInfo;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;

@Patch(value=ClientPacketListener.class)
public final class ClientPacketListenerPatch {
    @Inject(method="handleMovePlayer", desc="(Lnet/minecraft/network/protocol/game/ClientboundPlayerPositionPacket;)V", at=@At(value=At.Type.HEAD))
    public static void onHandleMovePlayer(ClientPacketListener clientPacketListener, ClientboundPlayerPositionPacket clientboundPlayerPositionPacket, CallbackInfo callbackInfo) {
        if (Raytrace.shouldCancelIncoming(clientboundPlayerPositionPacket)) {
            callbackInfo.cancel();
        }
    }
}

