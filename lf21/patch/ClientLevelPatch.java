/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Slice;
import asm.patchify.annotation.WrapInvoke;
import lf21.ClientBase;
import lf21.asm.Invocation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;

@Patch(value=ClientLevel.class)
public class ClientLevelPatch {
    @WrapInvoke(method="tickNonPassenger", desc="(Lnet/minecraft/world/entity/Entity;)V", target="net/minecraft/world/entity/Entity/tick", targetDesc="()V", slice=@Slice(startIndex=2, endIndex=2))
    public static void onTickEntity(ClientLevel clientLevel, Entity entity, Invocation<Entity, Void> invocation) throws Exception {
        if (!ClientBase.delayPackets.isEmpty() && entity == ClientBase.mc.player) {
            Runnable runnable = ClientBase.delayPackets.poll();
            if (runnable != null) {
                runnable.run();
            }
        } else {
            invocation.call();
        }
    }
}

