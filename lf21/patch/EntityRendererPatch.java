/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import com.mojang.blaze3d.vertex.PoseStack;
import lf21.LF21Client;
import lf21.modules.impl.render.NameTags;
import lf21.patch.CallbackInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

@Patch(value=EntityRenderer.class)
public class EntityRendererPatch {
    @Inject(method="renderNameTag", desc="(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at=@At(value=At.Type.HEAD))
    public static void onRenderNameTag(EntityRenderer<?> entityRenderer, Entity entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, CallbackInfo callbackInfo) {
        if (LF21Client.isReady() && NameTags.INSTANCE != null && NameTags.INSTANCE.isEnabled() && NameTags.INSTANCE.handlesEntity(entity)) {
            callbackInfo.cancel();
        }
    }
}

