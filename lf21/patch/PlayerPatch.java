/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.EntityRemoveEvent;
import lf21.modules.impl.combat.KeepSprint;
import lf21.patch.CallbackInfo;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Patch(value=Player.class)
public class PlayerPatch {
    @WrapInvoke(method="die", desc="(Lnet/minecraft/world/damagesource/DamageSource;)V", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onDieGetYRot(Player player, DamageSource damageSource, Invocation<Player, Float> invocation) throws Exception {
        return ClientBase.yaw;
    }

    @WrapInvoke(method="drop", desc="(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onDropGetYRot(Player player, ItemStack itemStack, boolean bl, boolean bl2, Invocation<Player, Float> invocation) throws Exception {
        return ClientBase.yaw;
    }

    @WrapInvoke(method="attack", desc="(Lnet/minecraft/world/entity/Entity;)V", target="net/minecraft/world/entity/Entity/getYRot", targetDesc="()F")
    public static float onAttackGetYRot(Player player, Entity entity, Invocation<Player, Float> invocation) throws Exception {
        return ClientBase.yaw;
    }

    @Inject(method="attack", desc="(Lnet/minecraft/world/entity/Entity;)V", at=@At(value=At.Type.HEAD))
    public static void onAttackPre(Player player, Entity entity, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            KeepSprint.beforeAttack(player);
            LF21Client.getInstance().getEventBus().call(new EntityRemoveEvent(false, entity));
        }
    }

    @Inject(method="attack", desc="(Lnet/minecraft/world/entity/Entity;)V", at=@At(value=At.Type.TAIL))
    public static void onAttackPost(Player player, Entity entity, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            KeepSprint.afterAttack(player);
            LF21Client.getInstance().getEventBus().call(new EntityRemoveEvent(true, entity));
        }
    }
}

