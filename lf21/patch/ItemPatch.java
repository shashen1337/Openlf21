/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.UseItemRayTraceEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

@Patch(value=Item.class)
public class ItemPatch {
    private static final String DEBUG_PREFIX = "YRot: ";

    @WrapInvoke(method="getPlayerPOVHitResult", desc="(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/ClipContext$Fluid;)Lnet/minecraft/world/phys/BlockHitResult;", target="net/minecraft/world/entity/Entity/getXRot", targetDesc="()F")
    public static float onGetPOVHitXRot(Level level, Player player, ClipContext.Fluid fluid, Invocation<Player, Float> invocation) throws Exception {
        UseItemRayTraceEvent useItemRayTraceEvent = new UseItemRayTraceEvent(player.getYRot(), player.getXRot());
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(useItemRayTraceEvent);
        }
        return useItemRayTraceEvent.getPitch();
    }

    @WrapInvoke(method="getPlayerPOVHitResult", desc="(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/ClipContext$Fluid;)Lnet/minecraft/world/phys/BlockHitResult;", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onGetPOVHitYRot(Level level, Player player, ClipContext.Fluid fluid, Invocation<Player, Float> invocation) throws Exception {
        UseItemRayTraceEvent useItemRayTraceEvent = new UseItemRayTraceEvent(player.getYRot(), player.getXRot());
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(useItemRayTraceEvent);
        }
        return useItemRayTraceEvent.getYaw();
    }
}

