/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import asm.patchify.annotation.Slice;
import asm.patchify.annotation.WrapInvoke;
import com.mojang.blaze3d.vertex.PoseStack;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.asm.Invocation;
import lf21.event.impl.RenderEntityEvent$Post;
import lf21.event.impl.RenderEntityEvent$Pre;
import lf21.event.impl.RotationAnimationEvent;
import lf21.patch.CallbackInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@Patch(value=LivingEntityRenderer.class)
public class LivingEntityRendererPatch {
    @Inject(method="render", desc="(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at=@At(value=At.Type.HEAD))
    public static void onRenderPre(LivingEntityRenderer<?, ?> livingEntityRenderer, LivingEntity livingEntity, float f2, float f3, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        RenderEntityEvent$Post renderEntityEvent$Post = new RenderEntityEvent$Post((EntityRenderer<?>)livingEntityRenderer, (Entity)livingEntity, poseStack, multiBufferSource, f3, n2);
        LF21Client.getInstance().getEventBus().call(renderEntityEvent$Post);
        if (renderEntityEvent$Post.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method="render", desc="(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at=@At(value=At.Type.TAIL))
    public static void onRenderPost(LivingEntityRenderer<?, ?> livingEntityRenderer, LivingEntity livingEntity, float f2, float f3, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, CallbackInfo callbackInfo) {
        if (!LF21Client.isReady()) {
            return;
        }
        RenderEntityEvent$Pre renderEntityEvent$Pre = new RenderEntityEvent$Pre((EntityRenderer<?>)livingEntityRenderer, (Entity)livingEntity, poseStack, multiBufferSource, f3, n2);
        LF21Client.getInstance().getEventBus().call(renderEntityEvent$Pre);
    }

    @WrapInvoke(method="render", desc="(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", target="net/minecraft/util/Mth/rotLerp", targetDesc="(FFF)F", slice=@Slice(startIndex=2, endIndex=2))
    public static float onRenderHeadYawLerp(LivingEntityRenderer<?, ?> livingEntityRenderer, LivingEntity livingEntity, float f2, float f3, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, Invocation<Object, Float> invocation) throws Exception {
        float f4 = ((Float)invocation.args().get(0)).floatValue();
        float f5 = ((Float)invocation.args().get(1)).floatValue();
        float f6 = ((Float)invocation.args().get(2)).floatValue();
        RotationAnimationEvent rotationAnimationEvent = new RotationAnimationEvent(f6, f5, 0.0f, 0.0f);
        if (LF21Client.isReady() && livingEntity == ClientBase.mc.player) {
            LF21Client.getInstance().getEventBus().call(rotationAnimationEvent);
        }
        return Mth.rotLerp((float)f4, (float)rotationAnimationEvent.getLastYaw(), (float)rotationAnimationEvent.getYaw());
    }

    @WrapInvoke(method="render", desc="(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", target="net/minecraft/util/Mth/lerp", targetDesc="(FFF)F", slice=@Slice(startIndex=1, endIndex=1))
    public static float onRenderPitchLerp(LivingEntityRenderer<?, ?> livingEntityRenderer, LivingEntity livingEntity, float f2, float f3, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, Invocation<Object, Float> invocation) throws Exception {
        float f4 = ((Float)invocation.args().get(0)).floatValue();
        float f5 = ((Float)invocation.args().get(1)).floatValue();
        float f6 = ((Float)invocation.args().get(2)).floatValue();
        RotationAnimationEvent rotationAnimationEvent = new RotationAnimationEvent(0.0f, 0.0f, f6, f5);
        if (LF21Client.isReady() && livingEntity == ClientBase.mc.player) {
            LF21Client.getInstance().getEventBus().call(rotationAnimationEvent);
        }
        return Mth.lerp((float)f4, (float)rotationAnimationEvent.getLastPitch(), (float)rotationAnimationEvent.getPitch());
    }
}

