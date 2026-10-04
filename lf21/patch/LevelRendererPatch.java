/*
 * Decompiled with CFR 0.152.
 */
package lf21.patch;

import asm.patchify.annotation.At;
import asm.patchify.annotation.Inject;
import asm.patchify.annotation.Patch;
import com.mojang.blaze3d.vertex.PoseStack;
import lf21.LF21Client;
import lf21.event.impl.RenderEvent;
import lf21.patch.CallbackInfo;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix4f;

@Patch(value=LevelRenderer.class)
public class LevelRendererPatch {
    @Inject(method="renderLevel", desc="(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lorg/joml/Matrix4f;)V", at=@At(value=At.Type.TAIL))
    public static void onRenderLevel(LevelRenderer levelRenderer, PoseStack poseStack, float f2, long l2, boolean bl, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo callbackInfo) {
        if (LF21Client.isReady()) {
            LF21Client.getInstance().getEventBus().call(new RenderEvent(poseStack, f2));
        }
    }
}

