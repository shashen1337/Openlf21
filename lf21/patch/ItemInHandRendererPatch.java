/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.WrapInvoke;
import com.mojang.blaze3d.vertex.PoseStack;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.UpdateHeldItemEvent;
import lf21.modules.impl.render.Freecam;
import lf21.modules.impl.render.NoAttackCooldown;
import lf21.modules.impl.render.OldHitting;
import lf21.patch.CallbackInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

@Patch(value=ItemInHandRenderer.class)
public class ItemInHandRendererPatch {
    @WrapInvoke(method="tick", desc="()V", target="net/minecraft/client/player/LocalPlayer/getAttackStrengthScale", targetDesc="(F)F")
    public static float onAttackStrengthForHandHeight(ItemInHandRenderer itemInHandRenderer, Invocation<ItemInHandRenderer, Float> invocation) throws Exception {
        if (LF21Client.isReady() && NoAttackCooldown.INSTANCE != null && NoAttackCooldown.INSTANCE.isEnabled()) {
            return 1.0f;
        }
        return ((Float)invocation.call()).floatValue();
    }

    @WrapInvoke(method="tick", desc="()V", target="net/minecraft/world/entity/LivingEntity/getMainHandItem", targetDesc="()Lnet/minecraft/world/item/ItemStack;")
    public static ItemStack onGetMainHandItem(ItemInHandRenderer itemInHandRenderer, Invocation<ItemInHandRenderer, ItemStack> invocation) {
        UpdateHeldItemEvent updateHeldItemEvent = new UpdateHeldItemEvent(InteractionHand.MAIN_HAND, ClientBase.mc.player.getMainHandItem());
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(updateHeldItemEvent);
        }
        return updateHeldItemEvent.getItemStack();
    }

    @Inject(method="renderArmWithItem", desc="(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at=@At(value=At.Type.HEAD))
    public static void onRenderArmWithItem(ItemInHandRenderer itemInHandRenderer, AbstractClientPlayer abstractClientPlayer, float f2, float f3, InteractionHand interactionHand, float f4, ItemStack itemStack, float f5, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && !Freecam.shouldRenderHand()) {
            callbackInfo.cancel();
            return;
        }
        if (!LF21Client.isReady() || OldHitting.INSTANCE == null || !OldHitting.INSTANCE.isEnabled()) {
            return;
        }
        boolean bl = ClientBase.mc.options.keyUse.isDown() && ClientBase.mc.player.getOffhandItem().isEmpty();
        boolean bl2 = OldHitting.INSTANCE.isKillAuraAttacking();
        if (interactionHand != InteractionHand.MAIN_HAND || !(itemStack.getItem() instanceof SwordItem) || !bl && !bl2) {
            return;
        }
        callbackInfo.cancel();
        OldHitting.INSTANCE.applyHitAnimation(poseStack, f4, abstractClientPlayer.getMainArm(), f5);
        boolean bl3 = abstractClientPlayer.getMainArm() == HumanoidArm.RIGHT;
        itemInHandRenderer.renderItem((LivingEntity)abstractClientPlayer, itemStack, bl3 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !bl3, poseStack, multiBufferSource, n2);
    }
}

